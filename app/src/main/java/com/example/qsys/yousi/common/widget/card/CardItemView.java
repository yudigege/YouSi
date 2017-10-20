package com.example.qsys.yousi.common.widget.card;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.qsys.yousi.R;
import com.example.qsys.yousi.bean.BookResponse;
/**
 * Created by wwh on 2016/10/31.
 */
public class CardItemView extends LinearLayout {
    Context mContext;
    View shadeView;
    TextView mContent;
    public TextView detail;
    public CardItemView(Context context) {
        this(context, null);
    }

    public CardItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CardItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        inflate(mContext, R.layout.layout_card_item, this);
        initViews();
    }
    private void initViews() {
        shadeView = findViewById(R.id.card_item_shade);
        mContent = (TextView) findViewById(R.id.card_item_content);
        detail = (TextView) findViewById(R.id.card_item_book_name_author);
    }
    //设置阴影
    public void setShadeLayer(int shaderLayer) {
        shadeView.setBackgroundResource(shaderLayer);
    }
    public void fillData(BookResponse.ResultsBean bean) {
        mContent.setText(bean.getContent());
        detail.setText("《" + bean.getBookname() + "》" + "|" + bean.getAuthor());
    }
}
