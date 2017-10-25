package com.example.qsys.yousi.adapter.idea;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.qsys.yousi.R;
import com.example.qsys.yousi.bean.DaysResportResponse;
import com.example.qsys.yousi.common.Constant;

import java.util.List;

/**
 * @author hanshaokai
 * @date 2017/10/24
 */


public class IdeaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<DaysResportResponse.ResultsBean> mDaysReportList;
    public final LayoutInflater inflater;
    private static final int TIMEVIEW = 1;
    private static final int CONTENTVIEW = 2;
    public IdeaAdapter(Context context, List<DaysResportResponse.ResultsBean> mDaysReportList) {
        this.context = context;
        this.mDaysReportList = mDaysReportList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        if (viewType == TIMEVIEW) {
            View inflate = inflater.inflate(R.layout.adapter_idea_item_time, parent, false);
            holder = new IdeaViewHolderTime(inflate);
        } else {
            View inflate = inflater.inflate(R.layout.adapter_idea_item_content, parent, false);
            holder = new IdeaViewHolderContent(inflate);
        }
        return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof IdeaViewHolderContent) {
            IdeaViewHolderContent ideaViewHolderContent = (IdeaViewHolderContent) holder;
            ideaViewHolderContent.content.setText(mDaysReportList.get(position / 2).getContent());
            ideaViewHolderContent.title.setText(mDaysReportList.get(position / 2).getType() == Constant.DAYLIE
                    ? mDaysReportList.get(position / 2).getTitle()
                    : mDaysReportList.get(position / 2).getBookname());
            ideaViewHolderContent.type.setText(mDaysReportList.get(position / 2).getType() == Constant.DAYLIE ? "日志" : "读后感");
        } else if (holder instanceof IdeaViewHolderTime) {
            IdeaViewHolderTime ideaViewHolderTime = (IdeaViewHolderTime) holder;
            ideaViewHolderTime.time.setText(mDaysReportList.get(position / 2).getCreatetime() + "");

        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position % CONTENTVIEW == 0) {
            return TIMEVIEW;
        } else {
            return CONTENTVIEW;
        }
    }

    @Override
    public int getItemCount() {
        return mDaysReportList == null ? 0 : mDaysReportList.size() * 2;
        //    return 20 ;
    }


    public class IdeaViewHolderTime extends RecyclerView.ViewHolder {

        public TextView time;

        public IdeaViewHolderTime(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.idea_item_time);
        }

    }

    public class IdeaViewHolderContent extends RecyclerView.ViewHolder {

        public TextView type;
        public TextView content;
        public TextView title;

        public IdeaViewHolderContent(View itemView) {
            super(itemView);
            type = (TextView) itemView.findViewById(R.id.idea_item_type);
            content = (TextView) itemView.findViewById(R.id.idea_content);
            title = (TextView) itemView.findViewById(R.id.idea_content_title);
        }

    }
}
