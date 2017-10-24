package com.example.qsys.yousi.common.widget.recyclerview;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by hanshaokai on 2017/10/20 16:30
 */

public abstract class AbstractOnRecyclerItemClickListener implements RecyclerView.OnItemTouchListener{

    private RecyclerView recyclerView;
    private GestureDetectorCompat mGestureDetector;
    public AbstractOnRecyclerItemClickListener(RecyclerView recyclerView, Context ct){

        this.recyclerView= recyclerView;
        mGestureDetector=new GestureDetectorCompat(ct,new
                ItemTouchHelperGestureListener());
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetector.onTouchEvent(e);

        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    /**
     * 点击
     * @param vh
     */
    public abstract void onItemClick(RecyclerView.ViewHolder vh);

    /**
     * 长点击
     * @param vh
     */
    public abstract void onLongClick(RecyclerView.ViewHolder vh);

    private class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            View child =recyclerView.findChildViewUnder(e.getX(),e.getY());
            if (child!=null){
                RecyclerView.ViewHolder vh=recyclerView.getChildViewHolder(child);
                onItemClick(vh);
            }
            return  true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            View child =recyclerView.findChildViewUnder(e.getX(),e.getY());
            if (child!=null){
                RecyclerView.ViewHolder vh=recyclerView.getChildViewHolder(child);
                onLongClick(vh);

            }
        }
    }


}
