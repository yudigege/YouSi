package com.example.qsys.yousi.fragment.idea.search;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.qsys.yousi.R;
import com.example.qsys.yousi.adapter.daily.DailySearchRsultItemAdapter;
import com.example.qsys.yousi.bean.BaseResponse;
import com.example.qsys.yousi.bean.DaysResportResponse;
import com.example.qsys.yousi.bean.SearchKeyWordsItemResponse;
import com.example.qsys.yousi.bean.db.SearchRecordBean;
import com.example.qsys.yousi.common.util.TimeUtils;
import com.example.qsys.yousi.db.GreenDaoManager;
import com.example.qsys.yousi.fragment.BaseFragment;
import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import yousi.SearchRecordBeanDao;

/**
 * @author hanshaokai
 * @date 2017/11/15 10:38
 */
public class SearchDailyFragment extends BaseFragment implements SearchDailyView {
    public SearchDailyPresenterExtend searchDailyPresenterExtend;
    @BindView(R.id.et_search_key_words)
    EditText etSearchKeyWords;
    @BindView(R.id.tv_cancel_daily)
    TextView tvCancelDaily;
    @BindView(R.id.recycler_result_item)
    RecyclerView recycler_result_item;
    @BindView(R.id.ll_search_cache_container)
    LinearLayout ll_search_cache_container;
    @BindView(R.id.iv_search_delete_key_words)
    ImageView iv_search_delete_key_words;
    @BindView(R.id.flexbox_layout_record_frequently)
    FlexboxLayout flexbox_layout_record_frequently;
    public DailySearchRsultItemAdapter dailySearchRsultItemAdapter;
    public List searchResultItemList = new ArrayList();



    @Override
    public void showResponseData(BaseResponse response) {
        List<DaysResportResponse.ResultsBean> results = ((DaysResportResponse) response).getResults();
        searchResultItemList.clear();
        searchResultItemList.addAll(results);
        recycler_result_item.setVisibility(View.VISIBLE);
        recycler_result_item.setLayoutManager(new LinearLayoutManager(baseFragmentActivity));
        dailySearchRsultItemAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String smg) {

    }

    @Override
    public void showEmptyViewByCode(int code, String msg) {

    }

    public void showRecordSearchFormDB() {
        ll_search_cache_container.removeAllViews();
        List<SearchRecordBean> searchRecordBeanList = GreenDaoManager.getInstance().getSession().getSearchRecordBeanDao().queryBuilder()
                .limit(10)
                .build().list();
        for (SearchRecordBean searchRecordBean : searchRecordBeanList) {
            final View inflate = LayoutInflater.from(baseFragmentActivity).inflate(R.layout.search_record_daily_item, null);
            TextView textView = inflate.findViewById(R.id.tv_record_words);
            ImageView imageView = inflate.findViewById(R.id.iv_delete);
            textView.setText(searchRecordBean.getKeyWords());
            inflate.setTag(searchRecordBean.getId());
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ll_search_cache_container.removeView(inflate);
                    GreenDaoManager.getInstance().getSession().getSearchRecordBeanDao().deleteByKey((Long) inflate.getTag());
                }
            });
            ll_search_cache_container.addView(inflate);
        }
    }

    @Override
    public void showProgressView(Boolean b) {

    }

    @Override
    public Boolean isActive() {
        return isAdded();
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View inflate = inflater.inflate(R.layout.fragment_daily_search, container, false);
        return inflate;
    }
    @Override
    public void doViewLogic(Bundle savedInstanceState) {
        searchDailyPresenterExtend = new SearchDailyPresenterExtend();
        searchDailyPresenterExtend.setPresenterView(this);
        initListener();
        initRecyclerView();
        searchDailyPresenterExtend.getSearchItemMostFrequently();
        showRecordSearchFormDB();

    }

    /**
     * 展示前十项查询最多
     */
    private void showFlexBoxSearchTop(List<SearchKeyWordsItemResponse.ResultsBean> results) {
// 通过代码向FlexboxLayout添加View
        for (SearchKeyWordsItemResponse.ResultsBean resultsBean : results) {
            TextView textView = new TextView(baseFragmentActivity);
            textView.setText(resultsBean.getKey_words());
            textView.setBackground(ContextCompat.getDrawable(baseFragmentActivity, R.drawable.search_bg_shape));
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(30, 20, 30, 20);
            flexbox_layout_record_frequently.addView(textView);
            //通过FlexboxLayout.LayoutParams 设置子元素支持的属性

            ViewGroup.LayoutParams params = textView.getLayoutParams();

            if (params instanceof FlexboxLayout.LayoutParams) {
                FlexboxLayout.LayoutParams layoutParams = (FlexboxLayout.LayoutParams) params;
                layoutParams.setMargins(20, 20, 20, 20);
            }
        }
    }

    private void initRecyclerView() {
        dailySearchRsultItemAdapter = new DailySearchRsultItemAdapter(baseFragmentActivity, searchResultItemList);
        recycler_result_item.setAdapter(dailySearchRsultItemAdapter);
    }
    private void initListener() {
        tvCancelDaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFragment();
            }
        });
        iv_search_delete_key_words.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSearchKeyWords.setText("");
                iv_search_delete_key_words.setVisibility(View.GONE);
                recycler_result_item.setVisibility(View.GONE);
                showRecordSearchFormDB();
            }
        });
        etSearchKeyWords.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                iv_search_delete_key_words.setVisibility(View.GONE);
                recycler_result_item.setVisibility(View.GONE);
                String keyWords = s.toString();
                if (!keyWords.trim().equals("")) {
                    iv_search_delete_key_words.setVisibility(View.VISIBLE);
                    //获得相关的日志
                    searchDailyPresenterExtend.getContainKeyWordsDaily(keyWords);
                    SearchRecordBean searchRecordBean1 = GreenDaoManager.getInstance().getSession().getSearchRecordBeanDao().queryBuilder().
                            where(SearchRecordBeanDao.Properties.KeyWords.eq(keyWords)).build().unique();

                    if (searchRecordBean1 == null) {

                        SearchRecordBean searchRecordBean = new SearchRecordBean(null, keyWords, TimeUtils.getNowString());
                        GreenDaoManager.getInstance().getSession().getSearchRecordBeanDao().insert(searchRecordBean);

                    }
                }
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        searchDailyPresenterExtend.detacheView();
    }

    public static SearchDailyFragment newInstance() {
        return new SearchDailyFragment();

    }

    @Override
    public void showLimitToIndexSearchItem(BaseResponse searchItem) {
        List<SearchKeyWordsItemResponse.ResultsBean> results = ((SearchKeyWordsItemResponse) searchItem).getResults();
        showFlexBoxSearchTop(results);

    }

}
