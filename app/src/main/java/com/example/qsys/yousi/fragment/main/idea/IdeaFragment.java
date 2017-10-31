package com.example.qsys.yousi.fragment.main.idea;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.qsys.yousi.R;
import com.example.qsys.yousi.adapter.idea.IdeaAdapter;
import com.example.qsys.yousi.bean.BaseResponse;
import com.example.qsys.yousi.bean.DaysResportResponse;
import com.example.qsys.yousi.common.Constant;
import com.example.qsys.yousi.common.util.SizeUtils;
import com.example.qsys.yousi.common.util.ToastUtils;
import com.example.qsys.yousi.common.widget.dialog.IdeaSelectDialog;
import com.example.qsys.yousi.common.widget.recyclerview.SpacesItemDecoration;
import com.example.qsys.yousi.fragment.BaseFragment;
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

    @Override
    public void showResponseData(BaseResponse response) {
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

    @Override
    public void showMessage(String smg) {
        ToastUtils.showShort(smg);
    }

    @Override
    public void showEmptyViewByCode(int code) {
        rlvIdeaMain.setVisibility(View.GONE);
        switch (code) {
            case Constant.SERVER_ERROR:
                llRoot.setVisibility(View.VISIBLE);
                errorTextView.setText(getResources().getString(R.string.error_server_msg));
                break;
            case Constant.NET_UNABLE:
                llRoot.setVisibility(View.VISIBLE);
                errorTextView.setText(getResources().getString(R.string.net_error));
                break;
            case Constant.NO_CONTENT:
                llRoot.setVisibility(View.VISIBLE);
                errorTextView.setText(getResources().getString(R.string.no_content));
                break;
            default:
        }
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_idea_main, container, false);
        return view;
    }

    private void initListener() {
        errorTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llRoot.setVisibility(View.GONE);
                mPresenter.getDasyReportData();
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
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                dialogFragment.show(transaction, "IdeaSelectDialog");
               // dialogFragment.show(getChildFragmentManager(), "DialogFragment");
            }
        });
    }
    @Override
    public void doViewLogic(Bundle savedInstanceState) {
        mPresenter = new IdeaPresenterExtend();
        mPresenter.setPresenterView(this);
        initToolBar(toolbarInclude, false, getResources().getString(R.string.idea), -1, false);
        initAdapter();
        initListener();
        mPresenter.getDasyReportData();
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
