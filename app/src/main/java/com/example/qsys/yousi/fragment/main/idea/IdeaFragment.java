package com.example.qsys.yousi.fragment.main.idea;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.qsys.yousi.R;
import com.example.qsys.yousi.activity.IdeaActivity;
import com.example.qsys.yousi.activity.RecordTrackActivity;
import com.example.qsys.yousi.adapter.idea.IdeaAdapter;
import com.example.qsys.yousi.bean.BaseResponse;
import com.example.qsys.yousi.bean.DaysResportResponse;
import com.example.qsys.yousi.common.Constant;
import com.example.qsys.yousi.common.util.ActivityUtils;
import com.example.qsys.yousi.common.util.LogUtils;
import com.example.qsys.yousi.common.util.SizeUtils;
import com.example.qsys.yousi.common.util.ToastUtils;
import com.example.qsys.yousi.common.widget.dialog.IdeaSelectDialog;
import com.example.qsys.yousi.common.widget.recyclerview.OnItemViewClickLisener;
import com.example.qsys.yousi.common.widget.recyclerview.SpacesItemDecoration;
import com.example.qsys.yousi.fragment.BaseFragment;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
/**
 * @author hanshaokai
 * @date 2017/10/17 15:19
 */
public class IdeaFragment extends BaseFragment implements IdeaView {
    @BindView(R.id.tv_title_include)
    TextView tvTitleInclude;
    @BindView(R.id.img_btn_action_include)
    ImageButton imgBtnActionInclude;
    @BindView(R.id.toolbar_include)
    Toolbar toolbarInclude;
    @BindView(R.id.appbar_clude)
    AppBarLayout appbarClude;
    @BindView(R.id.rlv_idea_main)
    XRecyclerView rlvIdeaMain;
    @BindView(R.id.fab_add_idea)
    FloatingActionButton fabAddIdea;
    private IdeaPresenterExtend mPresenter = null;
    public IdeaAdapter ideaAdapter;
    public List<DaysResportResponse.ResultsBean> mDaysReportList = new ArrayList<>();

    private int dailyNum = 0;
    private int readPressionNum = 0;
    public TextView head;
    public DialogFragment dialogFragment;

    public int page = 1;
    public int pageFresh = 1;
    public int pageSize = 6;
    public int lastPage;

    @Override
    public void showResponseData(BaseResponse response) {
        page++;
        rlvIdeaMain.refreshComplete();
        mDaysReportList.clear();
        readPressionNum = 0;
        dailyNum = 0;
        List<DaysResportResponse.ResultsBean> results = ((DaysResportResponse) response).getResults();
        mDaysReportList.addAll(results);
        for (DaysResportResponse.ResultsBean resultsBean : mDaysReportList) {
            if (resultsBean.getType() == Constant.DAYLIE) {
                ++dailyNum;
            }
            else if (resultsBean.getType() == Constant.READPRESSION) {
                ++readPressionNum;
            }
        }
        ideaAdapter.notifyDataSetChanged();
        rlvIdeaMain.setVisibility(View.VISIBLE);
        head.setText(getResources().getString(R.string.daily_after_read_pression, dailyNum, readPressionNum));
    }

    /**
     * 加载更多 直接添加
     *
     * @param dateReport
     */
    @Override
    public void showResponseMoreData(BaseResponse dateReport) {

        rlvIdeaMain.loadMoreComplete();
        readPressionNum = 0;
        dailyNum = 0;
        List<DaysResportResponse.ResultsBean> results = ((DaysResportResponse) dateReport).getResults();
        if (results.size() == 0) {
            ToastUtils.showShort("没有更多了");
            lastPage = page;
        } else {
            lastPage = page;
            page++;
        }
        mDaysReportList.addAll(results);
        for (DaysResportResponse.ResultsBean resultsBean : mDaysReportList) {
            if (resultsBean.getType() == Constant.DAYLIE) {
                ++dailyNum;
            } else if (resultsBean.getType() == Constant.READPRESSION) {
                ++readPressionNum;
            }
        }
        ideaAdapter.notifyDataSetChanged();
        head.setText(getResources().getString(R.string.daily_after_read_pression, dailyNum, readPressionNum));
    }
    @Override
    public void showMessage(String smg) {
        ToastUtils.showShort(smg);
    }

    @Override
    public void showEmptyViewByCode(int code, String msg) {
        rlvIdeaMain.setVisibility(View.GONE);
        switch (code) {
            case Constant.SERVER_ERROR:
                llRoot.setVisibility(View.VISIBLE);
                errorTextView.setText(getResources().getString(R.string.error_server_msg, msg));
                rlvIdeaMain.refreshComplete();
                rlvIdeaMain.loadMoreComplete();
                break;
            case Constant.SERVER_UNREACH:
                llRoot.setVisibility(View.VISIBLE);
                errorTextView.setText(getResources().getString(R.string.error_server_msg2));
                rlvIdeaMain.refreshComplete();
                rlvIdeaMain.loadMoreComplete();
                break;
            case Constant.NET_UNABLE:
                llRoot.setVisibility(View.VISIBLE);
                errorTextView.setText(getResources().getString(R.string.no_net));
                rlvIdeaMain.refreshComplete();
                rlvIdeaMain.loadMoreComplete();
                break;
            case Constant.NO_CONTENT:
                //加载更多时不显示 空界面
                if (mDaysReportList.size() != 0) {
                    rlvIdeaMain.setVisibility(View.VISIBLE);
                    rlvIdeaMain.refreshComplete();
                    rlvIdeaMain.loadMoreComplete();
                    break;
                }
                llRoot.setVisibility(View.VISIBLE);
                errorTextView.setText(getResources().getString(R.string.no_content));
                rlvIdeaMain.refreshComplete();
                rlvIdeaMain.loadMoreComplete();
                break;
            default:
        }
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_idea_main, container, false);
        page = 1;
        pageFresh = 1;
        pageSize = 6;
        return view;
    }

    private void initListener() {
        errorTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llRoot.setVisibility(View.GONE);
                mPresenter.getDasyReportData(1, pageSize);
            }
        });
        fabAddIdea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialogFragment == null) {
                    dialogFragment = IdeaSelectDialog.newInstance();
                }
                FragmentTransaction transaction = getFragmentManager()
                        .beginTransaction();
                //过渡动画
                transaction
                        .setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
                dialogFragment.show(transaction, "IdeaSelectDialog");
                // dialogFragment.show(getChildFragmentManager(), "DialogFragment");
            }
        });
        ideaAdapter.setOnItemViewClicLisener(new OnItemViewClickLisener() {
                                                 @Override
                                                 public void itemViewClick(int position, int type) {
                                                     if (type == Constant.READPRESSION) {
                                                         Bundle bundle = new Bundle();
                                                         bundle.putInt(Constant.IDEA_STYPE, Constant.READPRESSION_READ);
                                                         bundle.putParcelable(Constant.READPRESSION_OBJECT, mDaysReportList.get(position));
                                                         ActivityUtils.startActivity(bundle, baseFragmentActivity, IdeaActivity.class);
                                                     }
                                                     if (type == Constant.DAYLIE) {
                                                         Bundle bundle = new Bundle();
                                                         bundle.putInt(Constant.IDEA_STYPE, Constant.READPRESSION_DETAIL);
                                                         bundle.putParcelable(Constant.READPRESSION_OBJECT, mDaysReportList.get(position));
                                                         ActivityUtils.startActivity(bundle, baseFragmentActivity, IdeaActivity.class);
                                                     }
                                                 }
                                             }
        );
        rlvIdeaMain.setLoadingMoreProgressStyle(ProgressStyle.BallPulseRise);
        rlvIdeaMain.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = pageFresh;
                rlvIdeaMain.setNoMore(false);
                mPresenter.getDasyReportData(pageFresh, pageSize);
            }

            @Override
            public void onLoadMore() {
                LogUtils.d("加载更多");
                /**
                 * XrecyclerView 没到底部就触发了 加载更多  框架bug
                 */
                if (lastPage != page) {
                    mPresenter.getDasyReportMoreData(page, pageSize);
                } else {
                    rlvIdeaMain.setNoMore(true);
                }
            }
        });
    }
    @Override
    public void doViewLogic(Bundle savedInstanceState) {
        mPresenter = new IdeaPresenterExtend();
        mPresenter.setPresenterView(this);
        initToolBar();
        initAdapter();
        initListener();
        mPresenter.getDasyReportData(page, pageSize);
    }

    private void initToolBar() {

        //不设置 图标不显示
        setHasOptionsMenu(true);
        tvTitleInclude.setText(getResources().getString(R.string.idea));
        baseFragmentActivity.setSupportActionBar(toolbarInclude);
        ActionBar actionBar = baseFragmentActivity.getSupportActionBar();
        actionBar.setTitle("");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.write_daily:
                Bundle bundle = new Bundle();
                bundle.putInt(Constant.IDEA_STYPE, Constant.DAYLIE);
                ActivityUtils.startActivity(bundle, getActivity(), IdeaActivity.class);
                break;
            case R.id.write_read_pression:
                Bundle bundle2 = new Bundle();
                bundle2.putInt(Constant.IDEA_STYPE, Constant.READPRESSION);
                ActivityUtils.startActivity(bundle2, getActivity(), IdeaActivity.class);
                break;
            case R.id.search_daily:
                ToastUtils.showShort(" 搜索");
                Bundle bundle3 = new Bundle();
                bundle3.putInt(Constant.IDEA_STYPE, Constant.SEARCH_DAILY);
                ActivityUtils.startActivity(bundle3, getActivity(), IdeaActivity.class);
                break;
            case R.id.record_track:
                ActivityUtils.startActivity(null, getActivity(), RecordTrackActivity.class);
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.write_daily_actions, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(baseFragmentActivity);
        rlvIdeaMain.setLayoutManager(layoutManager);
        rlvIdeaMain.addItemDecoration(new SpacesItemDecoration(SizeUtils.dp2px(5)));
        ideaAdapter = new IdeaAdapter(baseFragmentActivity, mDaysReportList);
        View view = LayoutInflater.from(baseFragmentActivity).inflate(R.layout.adpter_idea_head, null, false);
        head = (TextView) view.findViewById(R.id.idea_num_head);
        rlvIdeaMain.addHeaderView(view);
        rlvIdeaMain.setAdapter(ideaAdapter);
        rlvIdeaMain.setRefreshProgressStyle(ProgressStyle.BallGridBeat);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mDaysReportList.clear();
        //解除持有的弱引用
        mPresenter.detacheView();
    }


    @Override
    public void showProgressView(Boolean b) {
        if (b) {
            showProgressDialog(getResources().getString(R.string.loading));
        } else {
            dismissProgressDialog();
        }

    }

    @Override
    public Boolean isActive() {
        return isAdded();
    }


    public static Fragment newInstance() {
        IdeaFragment ideaFragment=new IdeaFragment();

        return ideaFragment;
    }


}
