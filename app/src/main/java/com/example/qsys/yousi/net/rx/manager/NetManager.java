package com.example.qsys.yousi.net.rx.manager;

import com.example.qsys.yousi.common.Constant;
import com.example.qsys.yousi.net.rx.api.ApiService;
import com.example.qsys.yousi.net.rx.base.NetProvider;
import com.example.qsys.yousi.net.rx.base.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hanshaokai on 2017/10/11 17:52
 */

public class NetManager {
    private final long connectTimeoutMills = 10 * 1000L;
    private final long readTimeoutMills = 10* 1000L;
    private NetProvider sProvider = null;
    private static NetManager instance;
    private Map<String, NetProvider> providerMap = new HashMap<>();
    private Map<String, Retrofit> retrofitMap = new HashMap<>();
    private Map<String, OkHttpClient> clientMap = new HashMap<>();


    public static NetManager getInstance() {
        if (instance == null) {
            synchronized (NetManager.class) {
                if (instance == null) {
                    instance = new NetManager();
                }
            }
        }
        return instance;
    }


    public <S> S get(String baseUrl, Class<S> service) {
        return getInstance().getRetrofit(baseUrl).create(service);
    }

    /**
     * 在application中注入 在实现类中 实现了 链接 读写 超时 的时间处理 与请求返回数据的请求头部处理
     * @param provider
     */
    public void registerProvider(NetProvider provider) {
        this.sProvider = provider;
    }

    public void registerProvider(String baseUrl, NetProvider provider) {
        getInstance().providerMap.put(baseUrl, provider);
    }

    public NetProvider getCommonProvider() {
        return sProvider;
    }

    public void clearCache() {
        getInstance().retrofitMap.clear();
        getInstance().clientMap.clear();
    }

    public Retrofit getRetrofit(String baseUrl) {
        return getRetrofit(baseUrl, null);
    }

    public Retrofit getRetrofit(String baseUrl, NetProvider provider) {
        if (empty(baseUrl)) {
            throw new IllegalStateException("baseUrl can not be null");
        }
        if (retrofitMap.get(baseUrl) != null) {
            return retrofitMap.get(baseUrl);
        }

        if (provider == null) {
            provider = providerMap.get(baseUrl);
            if (provider == null) {
                provider = sProvider;
            }
        }
        checkProvider(provider);

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getClient(baseUrl, provider))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson));

        Retrofit retrofit = builder.build();
        retrofitMap.put(baseUrl, retrofit);
        providerMap.put(baseUrl, provider);

        return retrofit;
    }

    private boolean empty(String baseUrl) {
        return baseUrl == null || baseUrl.isEmpty();
    }

    private OkHttpClient getClient(String baseUrl, NetProvider provider) {
        if (empty(baseUrl)) {
            throw new IllegalStateException("baseUrl can not be null");
        }
        if (clientMap.get(baseUrl) != null) {
            return clientMap.get(baseUrl);
        }

        checkProvider(provider);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//设置超时时间 读写时间
        builder.connectTimeout(provider.configConnectTimeoutSecs() != 0
                ? provider.configConnectTimeoutSecs()
                : connectTimeoutMills, TimeUnit.SECONDS);
        builder.readTimeout(provider.configReadTimeoutSecs() != 0
                ? provider.configReadTimeoutSecs() : readTimeoutMills, TimeUnit.SECONDS);

        builder.writeTimeout(provider.configWriteTimeoutSecs() != 0
                ? provider.configReadTimeoutSecs() : readTimeoutMills, TimeUnit.SECONDS);
        CookieJar cookieJar = provider.configCookie();
        if (cookieJar != null) {
            builder.cookieJar(cookieJar);
        }
        provider.configHttps(builder);

        RequestHandler handler = provider.configHandler();
        //拦截头部添加header
        if (handler != null) {
            builder.addInterceptor(new NetInterceptor(handler));
        }

        Interceptor[] interceptors = provider.configInterceptors();
        if (!empty(interceptors)) {
            for (Interceptor interceptor : interceptors) {
                builder.addInterceptor(interceptor);
            }
        }

        if (provider.configLogEnable()) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        OkHttpClient client = builder.build();
        clientMap.put(baseUrl, client);
        providerMap.put(baseUrl, provider);

        return client;
    }

    private boolean empty(Interceptor[] interceptors) {
        return interceptors == null || interceptors.length == 0;
    }

    private void checkProvider(NetProvider provider) {
        if (provider == null) {
            throw new IllegalStateException("must register provider first");
        }
    }

    public Map<String, Retrofit> getRetrofitMap() {
        return retrofitMap;
    }

    public Map<String, OkHttpClient> getClientMap() {
        return clientMap;
    }

    /**
     *  得到配置好的retrofit
      */

    public static Retrofit retrofitClient() {
        return NetManager.getInstance().getRetrofit(Constant.
                BASE_URL);
    }

    public static ApiService getApiService() {
        return NetManager.retrofitClient().create(ApiService.class);
    }
}
