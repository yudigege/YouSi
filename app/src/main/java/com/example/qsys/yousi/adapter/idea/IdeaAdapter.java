package com.example.qsys.yousi.adapter.idea;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qsys.yousi.R;
import com.example.qsys.yousi.bean.DaysResportResponse;

import java.util.List;

/**
 * @author hanshaokai
 * @date 2017/10/24
 */


public class IdeaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<DaysResportResponse.ResultsBean> mDaysReportList;
    public final LayoutInflater inflater;

    public IdeaAdapter(Context context, List<DaysResportResponse.ResultsBean> mDaysReportList) {
        this.context = context;
        this.mDaysReportList = mDaysReportList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = inflater.inflate(R.layout.adapter_idea_item, null, false);
        IdeaViewHolder ideaViewHolder=new IdeaViewHolder(inflate);
        return ideaViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {




    }

    @Override
    public int getItemCount() {
        return mDaysReportList == null ? 0 : mDaysReportList.size();
    }


    public class IdeaViewHolder extends RecyclerView.ViewHolder {

        public IdeaViewHolder(View itemView) {
            super(itemView);
        }

    }
}
