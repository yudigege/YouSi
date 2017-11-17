package com.example.qsys.yousi.adapter.daily;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author hanshaokai
 * @date 2017/11/15 11:57
 */


public class SearchDailyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List list;
    private final Context context;
    public final LayoutInflater inflater;

    public SearchDailyAdapter(Context context, List list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }


}
