package com.example.qsys.yousi.common.widget.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.example.qsys.yousi.CustomApplication;
import com.example.qsys.yousi.R;

/**
 * @author hanshaokai
 * @date 2017/12/15 9:48
 */


public class ProgressBarCustomVIew extends View {


    public Paint mProgressPaint;
    public Paint mBorderTopPaint;
    public int mWidth;
    public int mHeight;
    public Path path;
    public Paint mBorderBootomPaint;
    public double percent = 0.0;

    public ProgressBarCustomVIew(Context context) {
        super(context);
        initView();
    }

    public ProgressBarCustomVIew(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ProgressBarCustomVIew(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    public void refrshPercentView(Double percent) {
        this.percent = percent;
        invalidate();
    }

    private void initView() {
        mBorderTopPaint = new Paint();
        mBorderTopPaint.setColor(ContextCompat.getColor(CustomApplication.getAppContext(), R.color.brown));
        mBorderTopPaint.setStrokeWidth(1);
        mBorderTopPaint.setStyle(Paint.Style.STROKE);
        mBorderTopPaint.setStrokeCap(Paint.Cap.ROUND);

        mBorderTopPaint.setAntiAlias(true);

        mBorderBootomPaint = new Paint();
        mBorderBootomPaint.setColor(ContextCompat.getColor(CustomApplication.getAppContext(), R.color.brown));
        mBorderBootomPaint.setStrokeWidth(1);
        mBorderBootomPaint.setStyle(Paint.Style.STROKE);
        mBorderBootomPaint.setAntiAlias(true);

        mProgressPaint = new Paint();
        mProgressPaint.setColor(ContextCompat.getColor(CustomApplication.getAppContext(), R.color.gold));
        mProgressPaint.setStrokeWidth(1);
        mProgressPaint.setStyle(Paint.Style.FILL);
        mProgressPaint.setAntiAlias(true);


        path = new Path();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int modeH = MeasureSpec.getMode(heightMeasureSpec);
        int modeW = MeasureSpec.getMode(widthMeasureSpec);
        int sizeW = MeasureSpec.getSize(widthMeasureSpec);
        int sizeH = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(sizeW, sizeH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Point point1 = new Point(0, 0);
        Point point2 = new Point(mWidth, 0);
        Point point3 = new Point(0, mHeight / 2);
        Point point4 = new Point(mWidth, mHeight / 2);
        Point point5 = new Point(0, mHeight);
        Point point6 = new Point(mWidth, mHeight);


        path.reset();
        path.moveTo(point3.x, point3.y);
        path.lineTo(point1.x, point1.y);
        path.lineTo(point2.x, point2.y);
        path.lineTo(point4.x, point4.y);


        canvas.drawPath(path, mBorderTopPaint);

        path.reset();
        path.moveTo(point3.x, point3.y);
        path.lineTo(point5.x, point5.y);
        path.lineTo(point6.x, point6.y);
        path.lineTo(point4.x, point4.y);

        canvas.drawPath(path, mBorderBootomPaint);

        path.reset();
        Point pointMinumTop = new Point((int) (mWidth * percent), 0);
        Point pointMinumBotom = new Point((int) (mWidth * percent), mHeight);
        path.lineTo(point1.x, point1.y);
        path.lineTo(pointMinumTop.x, pointMinumTop.y);
        path.lineTo(pointMinumBotom.x, pointMinumBotom.y);
        path.lineTo(point5.x, point5.y);
        canvas.drawPath(path, mProgressPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        initData();
    }

    private void initData() {


    }

    /**
     * dip 转换成px
     * dp表示大小
     *
     * @param dip 像素
     * @return 返回变换后的适应尺寸
     */
    private int dipToPx(float dip) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f * (dip >= 0 ? 1 : -1));
    }

    /**
     * 字体变化尺寸
     */
    private int spToPx(float dip) {
        float density = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (dip * density + 0.5f * (dip >= 0 ? 1 : -1));
    }
}
