package com.example.qsys.yousi.adapter.daily;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.qsys.yousi.R;
import com.example.qsys.yousi.bean.DaysResportResponse;

import java.util.List;
/**
 * @author hanshaokai
 * @date 2017/11/15 11:57
 */
public class DailySearchRsultItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<DaysResportResponse.ResultsBean> list;
    private final Context context;
    public final LayoutInflater inflater;

    public DailySearchRsultItemAdapter(Context context, List list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = inflater.inflate(R.layout.search_result_daily_item, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(inflate);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ( (ItemViewHolder)holder).tvRecord.setText(list.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        public final TextView tvRecord;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tvRecord = itemView.findViewById(R.id.tv_record_search);
        }


    }

}
