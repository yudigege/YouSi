package com.example.qsys.yousi.net.rx.manager;

import com.example.qsys.yousi.common.Constant;
import com.example.qsys.yousi.db.GreenDaoManager;
import com.example.qsys.yousi.net.rx.api.ApiService;
import com.example.qsys.yousi.net.rx.api.HttpDownService;
import com.example.qsys.yousi.net.rx.base.NetProvider;
import com.example.qsys.yousi.net.rx.base.RequestHandler;
import com.example.qsys.yousi.net.rx.download.DownInfo;
import com.example.qsys.yousi.net.rx.download.DownLoadInterceptor;
import com.example.qsys.yousi.net.rx.download.DownState;
import com.example.qsys.yousi.net.rx.exception.HttpTimeException;
import com.example.qsys.yousi.net.rx.exception.RetryWhenNetWorkException;
import com.example.qsys.yousi.net.rx.subscribers.ProgressDownSubscriber;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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

    // 记录下载数据
    private Set<DownInfo> downInfos;
    //回调sub 队列
    private HashMap<String, ProgressDownSubscriber> subMap;
    //数据库类
    private GreenDaoManager db;

    private NetManager() {
        downInfos = new HashSet<>();
        subMap = new HashMap<>();
        db = GreenDaoManager.getInstance();
    }

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

    public static HttpDownService getDownLoadService() {
        return NetManager.retrofitClient().create(HttpDownService.class);
    }


    /**
     * 开始下载
     */
    public void startDown(final DownInfo info) {
        //正在下载不处理
        if (info == null || subMap.get(info.getUrl()) != null){
            subMap.get(info.getUrl()).setDownInfo(info);
            return;
        }
        //添加回调处理类
        ProgressDownSubscriber subscriber = new ProgressDownSubscriber(info);
        //记录回调sub
        subMap.put(info.getUrl(), subscriber);
        //获取service 多次请求公用一个service
        HttpDownService httpDownService;
        if (downInfos.contains(info)) {
            httpDownService = info.getService();
        } else {
            DownLoadInterceptor interceptor = new DownLoadInterceptor(subscriber);
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            //手动创建一个OKHttpClient 并设置超时时间
            builder.connectTimeout(info.getConnectonTime(), TimeUnit.SECONDS);
            builder.addInterceptor(interceptor);

            Retrofit retrofit = new Retrofit.Builder()
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(Constant.BASE_URL)
                    .build();
            httpDownService = retrofit.create(HttpDownService.class);
            info.setService(httpDownService);
            downInfos.add(info);
        }
//得到rx对象 上一次下载的位置开始下载
        //getUrl 得到书名 带 文件扩展名  后台暂不支持断点下载
        httpDownService.downLoadFile("bytes=" + info.getReadLength() + "-", info.getUrl())
        /*指定线程*/
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
        /*失败后的retry 配置*/
                .retryWhen(new RetryWhenNetWorkException())
        /*读取下载写入文件*/
                .map(new Func1<ResponseBody, DownInfo>() {
                    @Override
                    public DownInfo call(ResponseBody responseBody) {

                        writeCaches(responseBody, new File(info.getSavePath()), info);
                        return info;
                    }
                })
                /*回调线程*/
                .observeOn(AndroidSchedulers.mainThread())
                 /*数据回调*/
                .subscribe(subscriber);
    }

    /**
     * 停止下载
     */
    public void stopDown(DownInfo info) {
        if (info == null) {
            return;
        }
        info.setState(DownState.STOP);
        info.getListener().onStop();
        if (subMap.containsKey(info.getUrl())) {
            ProgressDownSubscriber subscriber = subMap.get(info.getUrl());
            subscriber.unsubscribe();
            subMap.remove(info.getUrl());
        }
        /*保存数据库信息和本地文件*/
        db.save(info);
    }

    /*暂停下载*/
    public void pause(DownInfo info) {
        if (info == null) {
            return;
        }
        info.setState(DownState.PAUSE);
        info.getListener().onPuase();
        if (subMap.containsKey(info.getUrl())) {
            ProgressDownSubscriber subscriber = subMap.get(info.getUrl());
            subscriber.unsubscribe();
            subMap.remove(info.getUrl());
        }
/*这里需要讲info 信息写入到数据中可自由扩展 用自己项目的数据库*/
        db.update(info);
    }

    /*停止全部下载*/
    public void stopAllDown() {
        for (DownInfo downInfo : downInfos) {
            startDown(downInfo);
        }
        subMap.clear();
        downInfos.clear();
    }

    /*暂停全部下载*/

    public void pauseAll() {
        for (DownInfo downInfo : downInfos) {
            pause(downInfo);
        }
        subMap.clear();
        downInfos.clear();
    }

    /*返回全部正在下载的数据*/
    public Set<DownInfo> getDownInfos() {
        return downInfos;
    }

    /*移除下载数据*/
    public void remove(DownInfo info) {
        subMap.remove(info.getUrl());
        downInfos.remove(info);
    }

    public void writeCaches(ResponseBody responseBody, File file, DownInfo info) {
        try {
            RandomAccessFile randomAccessFile = null;
            FileChannel channelOut = null;
            InputStream inputStream = null;

            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();   }
                long allLength = 0 == info.getCountLength() ? responseBody.contentLength()
                        : info.getReadLength() + responseBody.contentLength();
                inputStream = responseBody.byteStream();
                randomAccessFile = new RandomAccessFile(file, "rwd");
                channelOut = randomAccessFile.getChannel();
                MappedByteBuffer mappedByteBuffer = channelOut.map(FileChannel.MapMode.READ_WRITE
                        , info.getReadLength(), allLength - info.getReadLength());
                byte[] buffer = new byte[1024 * 4];
                int len;
                while ((len = inputStream.read(buffer)) != -1) {
                    mappedByteBuffer.put(buffer, 0, len);
                }

        } catch (IOException e) {
            throw new HttpTimeException(e.getMessage());
        }
    }


}



