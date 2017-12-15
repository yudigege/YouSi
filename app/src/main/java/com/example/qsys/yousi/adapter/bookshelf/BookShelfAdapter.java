package com.example.qsys.yousi.adapter.bookshelf;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.qsys.yousi.CustomApplication;
import com.example.qsys.yousi.R;
import com.example.qsys.yousi.bean.BookResponse;

import java.util.List;


/**
 * @author hanshaokai
 * @date 2017/10/20 17:22
 */
public class BookShelfAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<BookResponse.ResultsBean> mList;
    public final LayoutInflater inflater;

    public BookShelfAdapter(Context context, List<BookResponse.ResultsBean> list) {

        this.mContext = context;
        this.mList = list;
        inflater = LayoutInflater.from(mContext);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflateView = inflater.inflate(R.layout.adapter_book_shelf_item, parent, false);
        ItemBookViewHolder itemBookViewHolder = new ItemBookViewHolder(inflateView);
        return itemBookViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
       /* ((ItemBookViewHolder) holder).author.setText(mList.get(position).getAuthor());*/
        ((ItemBookViewHolder) holder).bookName.setText(mList.get(position).getBookname());

        if (mList.get(position).getVIsiblePercent()) {
            ((ItemBookViewHolder) holder).barCustomVIew.setVisibility(View.VISIBLE);
            ((ItemBookViewHolder) holder).barCustomVIew.refrshPercentView(mList.get(position).getPercent());
            ((ItemBookViewHolder) holder).card_book_container.setCardBackgroundColor(ContextCompat.getColor(CustomApplication.getAppContext(), R.color.grey));
        } else {
            ((ItemBookViewHolder) holder).barCustomVIew.setVisibility(View.GONE);
            ((ItemBookViewHolder) holder).card_book_container.setCardBackgroundColor(ContextCompat.getColor(CustomApplication.getAppContext(), R.color.white));
        }

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class ItemBookViewHolder extends RecyclerView.ViewHolder {


        public TextView bookName;
        public CardView card_book_container;
        public com.example.qsys.yousi.common.widget.customView.ProgressBarCustomVIew barCustomVIew;
        public ItemBookViewHolder(View itemView) {
            super(itemView);
            bookName = (TextView) itemView.findViewById(R.id.tv_book_shelf_item_book_name);
            card_book_container = (CardView) itemView.findViewById(R.id.card_book_container);
            barCustomVIew = (com.example.qsys.yousi.common.widget.customView.ProgressBarCustomVIew) itemView.findViewById(R.id.progress_bar);
        }

    }


}
