package com.example.qsys.yousi.common.widget.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.qsys.yousi.CustomApplication;
import com.example.qsys.yousi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hanshaokai
 * @date 2017/12/8 13:48
 */


public class RecordTrackCustomView extends View {

    /**
     * 今日拜访图标
     */
    private float viewWith;//控件宽度
    private float viewHeight;//控件高度
    private String[] monthText;//横坐标值
    private int textSize = spToPx(15);//字体大小
    private Paint brokenTextPaint;//折线 文字
    private Paint pointPaint; //未选中的点
    private Paint pointSelectePoint;//选中的点
    private Paint straightPaint;//直线画笔

    private Paint textPaint;//字体画笔 月份 和浮动显示值 共用
    private Path brokenPath;//浮动背景path
    private int halfScore;//y中间值
    private int maxScore;//y轴最大值 这里由数字数组 上求整获得
    private int minScore;//y轴最小值这里默认0
    private float xToRightOrLeft = 0.05f;//设置 x轴左边倍距和右边倍距
    private int monthCount;//默认横坐标数目
    private int selectIndexX = -1;//选中的月份
    private List<Point> scorePoints;
    private Long[] score;//坐标点 横坐标是定值 竖坐标整数数组
    private float brokenLineWith = 0.5f; //折线宽度
    private int brokenLineColor = 0xff02bbb7;//折线颜色
    private int straightLineColor = 0xffeaeaea;//0xffeaeaea x轴的线颜色
    private int textNormalColor = 0xff7e7e7e;//字体颜色
    private float xToViewTOp = 0.9f;//x距离 view 顶倍距离
    private float yTopToViewTop = 0.1f;//y轴顶部距view顶倍距离 view 顶倍距离
    private Paint pointFillPaint;// 填充原点画笔
    private Paint textYPaint;//y轴 文字 画笔
    private Paint textXPaint;//x轴 文字画笔

    private Path brokenHalfPath;// 折线路径
    private Path brokenClosePath;// 闭合折线路径
    private Paint brokenClosePaint;//闭合折线 画笔
    private Paint brokenHalfPaint;// 折线画笔
    private float touchX; //点击 坐标x值
    private float touchY;//点击 坐标y值

    private Boolean isFloatWindowShow = false;
    private int monthVisitSum;


    public RecordTrackCustomView(Context context) {
        super(context);
        initConfig(context, null);
        init();
    }

    public RecordTrackCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initConfig(context, attrs);
        init();
    }

    public RecordTrackCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initConfig(context, attrs);
        init();

    }

    /**
     * 初始化布局配置
     *
     * @param context 上下文
     * @param attrs 参数
     */
    private void initConfig(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BrokenLineGraphView);
       /* maxScore = a.getInt(R.styleable.BrokenLineGraphView_max_score, 700);
        minScore = a.getInt(R.styleable.BrokenLineGraphView_min_score, 650);*/
        brokenLineColor = a.getColor(R.styleable.BrokenLineGraphView_broken_line_color, brokenLineColor);
        a.recycle();

    }

    private void init() {
        brokenPath = new Path();

        brokenClosePath = new Path();
        brokenClosePaint = new Paint();
        brokenClosePaint.setAntiAlias(true);
        brokenClosePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        brokenClosePaint.setStrokeWidth(dipToPx(brokenLineWith));
        brokenClosePaint.setStrokeCap(Paint.Cap.ROUND);
        brokenClosePaint.setColor(brokenLineColor);


        //画折线
        brokenHalfPath = new Path();
        brokenHalfPaint = new Paint();
        brokenHalfPaint.setAntiAlias(true);
        brokenHalfPaint.setColor(Color.argb(255, 255, 140, 0));
        brokenHalfPaint.setStrokeWidth(6f);
        brokenHalfPaint.setStyle(Paint.Style.STROKE);
        brokenHalfPaint.setStrokeCap(Paint.Cap.ROUND);

        //折线文字
        brokenTextPaint = new Paint();
        brokenTextPaint.setAntiAlias(true);
        brokenTextPaint.setStyle(Paint.Style.STROKE);
        brokenTextPaint.setStrokeWidth(dipToPx(brokenLineWith));
        brokenTextPaint.setStrokeCap(Paint.Cap.ROUND);
        brokenTextPaint.setColor(brokenLineColor);

        //未选中的点
        pointPaint = new Paint();
        pointPaint.setAntiAlias(true);
        pointPaint.setStyle(Paint.Style.STROKE);
        pointPaint.setStrokeWidth(brokenLineWith);
        pointPaint.setStrokeCap(Paint.Cap.ROUND);
        pointPaint.setColor(brokenLineColor);

        pointFillPaint = new Paint();
        pointFillPaint.setAntiAlias(true);
        pointFillPaint.setStyle(Paint.Style.FILL);
        pointFillPaint.setStrokeWidth(brokenLineWith);
        pointFillPaint.setStrokeCap(Paint.Cap.ROUND);
        pointFillPaint.setColor(0xFFE38C2E);

        //选中的点
        pointSelectePoint = new Paint();
        pointSelectePoint.setAntiAlias(true);
        pointSelectePoint.setStyle(Paint.Style.FILL);
        pointSelectePoint.setStrokeWidth(brokenLineWith);
        pointSelectePoint.setStrokeCap(Paint.Cap.ROUND);
        pointSelectePoint.setColor(0xFFE38C2E);

        //直线画笔
        straightPaint = new Paint();
        straightPaint.setAntiAlias(true);
        straightPaint.setStyle(Paint.Style.STROKE);
        straightPaint.setStrokeWidth(brokenLineWith);
        straightPaint.setColor((straightLineColor));
        straightPaint.setStrokeCap(Paint.Cap.ROUND);


        //文字画笔 浮动的文字 和 横坐标值 以下文字 设置像素大小值
        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor((textNormalColor));
        textPaint.setTextSize(spToPx(12));
        //x轴文字
        textXPaint = new Paint();
        textXPaint.setAntiAlias(true);
        textXPaint.setTextAlign(Paint.Align.CENTER);
        textXPaint.setStyle(Paint.Style.FILL);
        textXPaint.setColor((textNormalColor));
        textXPaint.setTextSize(spToPx(6));


        //y 轴文字
        textYPaint = new Paint();
        textYPaint.setTextSize(spToPx(6));
        textYPaint.setColor(textNormalColor);
        textYPaint.setAntiAlias(true);
        textYPaint.setStyle(Paint.Style.FILL);
    }

    /**
     * 任务拜访传参数调用方法 调用此方法 显示折线图
     *
     * @param score 竖坐标
     * @param date 横坐标
     */

    public void setScore(List<Long> score, List<String> date) {
        Long[] y = new Long[score.size()];
        for (int i = 0, num = score.size(); i < num; i++) {
            y[i] = score.get(i);
        }
        String[] x = new String[date.size()];
        for (int i = 0, num = date.size(); i < num; i++) {
            x[i] = date.get(i);
        }
        setScore(y, x);
    }

    //赋值 点  触发绘图
    public void setScore(Long[] score, String[] date) {
        if (score.length != date.length) {
            Toast.makeText(CustomApplication.getAppContext(), "数据不一致", Toast.LENGTH_LONG).show();
            return;
        }
        this.score = score;//y轴坐标
        this.maxScore = getIntMaxFormat(getMaxNum(score));//Y轴最大值
        this.halfScore = getIntMaxFormat(getMaxNum(score)) / 2;
        this.monthText = date;//X轴坐标
        monthCount = score.length;//坐标点个数
        selectIndexX = 0;//默认显示第一个
        isFloatWindowShow = false;
        initData();
        invalidate();
    }


    //得到Y轴高度
    public float getYHeight() {
        float maxScoreYCoordinate = viewHeight * yTopToViewTop;// y坐标的最大值所在位置  0点坐标从左上角开始
        float minScoreYCoordinate = viewHeight * xToViewTOp;//y坐标的最小值所在位置
        Log.v("BrokenLineGraphView", "initData: " + maxScoreYCoordinate);
        return minScoreYCoordinate - maxScoreYCoordinate;
    }

    //得到y 值坐标
    private int getCoordinateY(int i) {
        if (maxScore == 0) {
            return (int) (viewHeight * xToViewTOp);
        }
        // 如果y值为负数 取为零
        return (int) (((float) (maxScore - (score[i]<0?0:score[i])) / (maxScore - minScore)) * (getYHeight()) + viewHeight * yTopToViewTop);
    }

    //初始化坐标点
    private void initData() {
        //获得坐标点的坐标
        //得到横坐标的数量
        scorePoints = new ArrayList<>();
        if (score == null) {
            return;
        }
        for (int i = 0; i < monthCount; i++) {
            Log.v("BrokenLineGraphView", "initData: " + score[i]);
            Point point = new Point();
            //这里算出 坐标值 横坐标的总数除第几个坐标 得到占横坐标总长度的份数 乘以总长度 得到横坐标的位置
            point.x = (int) getCoordinateX(i); //横坐标距离 付给点的x值
            point.y = getCoordinateY(i);
            scorePoints.add(point);
        }
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d("BrokenLineGraphView", "测量");
        viewWith = w;
        viewHeight = h;
        initData();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (score == null) {
            return;
        }

        //如果点击触发了 变换 坐标点 走这里
        //这里画虚线 注释掉了虚线
        // drawDottedLine(canvas, viewWith * 0.13f, viewHeight *xToViewTOp, viewWith*0.13f, viewHeight * yTopToViewTop);
        //画y轴
        //canvas.drawLine(viewWith * xToRightOrLeft, viewHeight * xToViewTOp, viewWith * xToRightOrLeft, viewHeight * yTopToViewTop +
        // (4), straightPaint);
        //画中间x轴虚线
        drawDottedLine(canvas, viewWith * xToRightOrLeft, viewHeight * 0.4f, viewWith, viewHeight * 0.4f, Color.LTGRAY);
        //画月份 和 y值
        drawText(canvas);
        //画x轴 和刻度
        drawMonthLine(canvas);
        //画折线
        drawBrokenLine(canvas);
        //画折线穿过的点
        drawPoint(canvas);

        //绘制悬浮框 如果点击触发了 显示悬浮窗 走这里
//        if (selectIndexX != -1)
       /* if (isFloatWindowShow) {
            drawFloatWidow(canvas);
        }*/


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.getParent().requestDisallowInterceptTouchEvent(true);//一旦底层View收到touch的action后调用这个方法那么父层View就不会再调用onInterceptTouchEvent了，也无法截获以后的action
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                onActionUpEvent(event);
                this.getParent().requestDisallowInterceptTouchEvent(false);
                break;
            case MotionEvent.ACTION_CANCEL:
                this.getParent().requestDisallowInterceptTouchEvent(false);
                break;
            default:
        }
        return true;
    }

    private void onActionUpEvent(MotionEvent event) {
        boolean isValidTouch = validateTouch(event.getX(), event.getY());
        if (isValidTouch) {
            invalidate();
        }

    }

    //是否是有效的触摸范围
    private boolean validateTouch(float x, float y) {
        if (scorePoints == null || scorePoints.size() == 0) {
            return true;
        }
        //曲线触摸区域
        for (int i = 0; i < scorePoints.size(); i++) {
            // dipToPx(8)乘以2为了适当增大触摸面积
            if (x > (scorePoints.get(i).x - dipToPx(5) * 2) && x < (scorePoints.get(i).x + dipToPx(5) * 2)) {
                /*if (y > (scorePoints.get(i).y - dipToPx(8) * 2) && y < (scorePoints.get(i).y + dipToPx(8) * 2)) {

                }*/
                //点击x 线 就重绘
                selectIndexX = i;
                touchX = x;
                touchY = y;
                isFloatWindowShow = true;
                return true;
            }
        }

        //月份触摸区域
        //计算每个月份X坐标的中心点
        float monthTouchY = viewHeight * xToViewTOp - dipToPx(3);//减去dipToPx(3)增大触摸面积
        float[] validTouchX = new float[monthText.length];
        for (int i = 0; i < monthText.length; i++) {
            validTouchX[i] = getCoordinateX(i);
        }
        if (y > monthTouchY) {
            for (int i = 0; i < validTouchX.length; i++) {
                Log.v("BrokenLineGraphView", "validateTouch: validTouchX:" + validTouchX[i]);
                if (x < validTouchX[i] + dipToPx(8) && x > validTouchX[i] - dipToPx(8)) {
                    Log.v("BrokenLineGraphView", "validateTouch: " + (i + 1));
                    selectIndexX = i;
                    touchX = x;
                    touchY = y;
                    isFloatWindowShow = true;
                    return true;
                }
            }
        }
        // 重绘悬浮窗
        if (xToRightOrLeft * viewWith < x && x < getXWidth() - xToRightOrLeft * viewWith
                && y > yTopToViewTop * viewHeight && y < xToViewTOp * viewHeight) {
            touchX = x;
            touchY = y;
            invalidate();
            return true;
        }

        if (!(xToRightOrLeft * viewWith < x && x < getXWidth() - xToRightOrLeft * viewWith
                && y > yTopToViewTop * viewHeight && y < xToViewTOp * viewHeight)) {
            selectIndexX = 0;
            isFloatWindowShow = false;
            invalidate();
            return true;
        }

        return false;
    }
    //将负数置未0
    public  float signToZero(float f){
        if (f<0f){
            return  0f;
        }else {
            return  f;
        }
    }
    //绘制折线
    private void drawBrokenLine(Canvas canvas) {
        if (score.length == 0) {
            return;
        }
        Log.v("BrokenLineGraphView", "drawBrokenLine: " + scorePoints.get(0));
        brokenClosePath.reset();
        //渐变不能 提取成 成员变量 否则没渐变效果？
        int[] colors = {0xEEE38C2E, 0xDDE38C2E, 0xCCE38C2E, 0xBBE38C2E,
                0xAAE38C2E, 0x99E38C2E, 0x88E38C2E, 0x77E38C2E, 0x66E38C2E, 0x55E38C2E, 0x44E38C2E, 0x33E38C2E, 0x22E38C2E, 0x11E38C2E};
        Shader mShader = new LinearGradient(xToRightOrLeft * viewWith, yTopToViewTop * viewHeight,
                xToRightOrLeft * viewWith, xToViewTOp * viewHeight, colors, null, Shader.TileMode.CLAMP);
        /*SweepGradient mSweepGradient = new SweepGradient(scorePoints.get(0).x
                , scorePoints.get(0).y, colors, null);*/
        // 设置画笔渐变色 画闭合折线
        brokenClosePaint.setShader(mShader);
        //这里画线竖坐标有负数的情况 将负数置为零
        brokenClosePath.moveTo(scorePoints.get(0).x, signToZero(scorePoints.get(0).y));//从第一个坐标开始画
        for (int i = 0, num = scorePoints.size(); i < num; i++) {
            brokenClosePath.lineTo(scorePoints.get(i).x, signToZero(scorePoints.get(i).y));
        }
        brokenClosePath.lineTo(scorePoints.get(scorePoints.size() - 1).x, xToViewTOp * viewHeight);
        brokenClosePath.lineTo(scorePoints.get(0).x, xToViewTOp * viewHeight);
        //    brokenClosePath.lineTo(scorePoints.get(0).x, scorePoints.get(0).y);
        brokenClosePath.close();//形成封闭曲线
        canvas.drawPath(brokenClosePath, brokenClosePaint);//画闭合折线
        //画折线
        brokenHalfPath.reset();
        brokenHalfPath.moveTo(scorePoints.get(0).x, scorePoints.get(0).y);//从第一个坐标开始画
        for (int i = 0, num = scorePoints.size(); i < num; i++) {
            brokenHalfPath.lineTo(scorePoints.get(i).x, scorePoints.get(i).y);
        }
        canvas.drawPath(brokenHalfPath, brokenHalfPaint);//画折线
    }

    //绘制折线穿过的点
    protected void drawPoint(Canvas canvas) {
        if (scorePoints == null) {
            return;
        }
        for (int i = 0; i < scorePoints.size(); i++) {
            // 画点中前的颜色 先描边 再填充白色
            canvas.drawCircle(scorePoints.get(i).x, scorePoints.get(i).y, dipToPx(3), pointPaint);
            canvas.drawCircle(scorePoints.get(i).x, scorePoints.get(i).y, dipToPx(3), pointFillPaint);
            if (i == selectIndexX) {
                //有选中的点 后边加功能

                //画虚线 过坐标点 平行y轴
                drawDottedLine(canvas, scorePoints.get(i).x, xToViewTOp * viewHeight, scorePoints.get(i).x, viewHeight * yTopToViewTop, Color.LTGRAY);

                //画大圆点
                canvas.drawCircle(scorePoints.get(i).x, scorePoints.get(i).y, dipToPx(5), pointSelectePoint);
               /* //绘制浮动文本背景框
                drawFloatTextBackground(canvas, scorePoints.get(i).x, scorePoints.get(i).y - dipToPx(8f));
                textPaint.setColor(0xffffffff);
                //绘制浮动文字
                canvas.drawText(String.valueOf(score[i]), scorePoints.get(i).x, scorePoints.get(i).y - dipToPx(5f) - textSize, textPaint);*/
            }
        }
    }

    //绘制月份的直线(包括刻度)
    private void drawMonthLine(Canvas canvas) {
        straightPaint.setStrokeWidth(dipToPx(1));
        canvas.drawLine(xToRightOrLeft * viewWith, viewHeight * xToViewTOp, viewWith - xToRightOrLeft * viewWith, viewHeight * xToViewTOp, straightPaint); //前俩参数x坐标起点到后俩参数中点
//分隔线距离最左边和最右边的距离是0.15倍的viewWith 横坐标的长度
        float newWith = getXWidth();
        float coordinateX;//分隔线X坐标
        for (int i = 0; i < monthCount; i++) {
            coordinateX = getCoordinateX(i);
            canvas.drawLine(coordinateX, viewHeight * xToViewTOp, coordinateX, viewHeight * xToViewTOp - dipToPx(2), straightPaint);//在每个 横坐标上画刻度线
        }
    }

    //得到第几个x坐标值
    private float getCoordinateX(int index) {
        float coordinateX;
        if (monthCount == 1) {
            coordinateX = viewWith * xToRightOrLeft;
        } else {
            coordinateX = getXWidth() * ((float) (index) / (monthCount - 1)) + (viewWith * xToRightOrLeft);//刻度的高度  0点坐标从view 的左上角开始}

        }
        return coordinateX;


    }

    //得到X轴宽度
    private float getXWidth() {
        return viewWith - (viewWith * xToRightOrLeft) * 2;
    }


    //绘制文本  绘制X坐标
    private void drawText(Canvas canvas) {

        //放置最大y值
        //放置中间值
        canvas.drawText(String.valueOf(maxScore), viewWith * xToRightOrLeft - textSize, viewHeight * yTopToViewTop + textSize * 0.25f, textYPaint);
        canvas.drawText(String.valueOf(halfScore), viewWith * xToRightOrLeft - textSize, viewHeight * (yTopToViewTop + xToViewTOp) / 2 + textSize * 0.25f, textYPaint);
        // canvas.drawText(String.valueOf(minScore), viewWith * xToRightOrLeft , viewHeigh t *xToViewTOp + textSize * 0.25f, textYPaint);
        float coordinateX;//分隔线X坐标
        textSize = (int) textYPaint.getTextSize();
        //这里画 月份背景 monthCount横坐标个数
        for (int i = 0; i < monthCount; i++) {
            coordinateX = getCoordinateX(i);
            if (i == selectIndexX) {
                textXPaint.setStyle(Paint.Style.STROKE);
                textXPaint.setColor(brokenLineColor);//选中的月份这里变颜色
                RectF r2 = new RectF();
                r2.left = coordinateX - textSize - dipToPx(4);
                r2.top = viewHeight * xToViewTOp + dipToPx(4) + textSize / 2;
                r2.right = coordinateX + textSize + dipToPx(4);
                r2.bottom = viewHeight * xToViewTOp + dipToPx(4) + textSize + dipToPx(8);
                //canvas.drawRoundRect(r2, 10, 10, textPaint);
            }
            //绘制月份
            canvas.drawText(monthText[i], coordinateX, viewHeight * xToViewTOp + dipToPx(4) + textSize + dipToPx(5), textXPaint);
            textXPaint.setColor(textNormalColor);
        }
    }


    // 绘制悬浮框
    public void drawFloatText(Canvas canvas, Point pointCircle, float w, float h) {
        //画原点
        canvas.drawCircle(pointCircle.x + 2 * dipToPx(5), pointCircle.y + 2 * dipToPx(5), dipToPx(5), pointFillPaint);
        Paint floatWindowTextPaint;//悬浮窗内文字文字画笔
        floatWindowTextPaint = new Paint();
        floatWindowTextPaint.setAntiAlias(true);
        floatWindowTextPaint.setTextAlign(Paint.Align.LEFT);
        floatWindowTextPaint.setStyle(Paint.Style.FILL);
        floatWindowTextPaint.setColor(Color.WHITE);
        floatWindowTextPaint.setTextSize(dipToPx(13));
        //画虚线 过坐标点 平行x轴
        drawDottedLine(canvas
                , pointCircle.x + dipToPx(7)
                , pointCircle.y + floatWindowTextPaint.getTextSize() + 2 * dipToPx(5)
                , pointCircle.x + w - dipToPx(7)
                , pointCircle.y + floatWindowTextPaint.getTextSize() + 2 * dipToPx(5), Color.WHITE);


    }


    public int getMaxNum(int nums[]) {
        int num = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > num) {
                num = nums[i];
            }
        }
        return num;
    }
    /**
     * 绘制悬浮框
     *
     * @param canvas 画布
     */
    private void drawFloatWidow(Canvas canvas) {
        brokenPath.reset();
        // touchX
        //根据类型设置不一样的宽度
        //-----------------------------------------默认长宽------------------------------------
        float w = getXWidth() * 4/ 7;
        float h = getYHeight() * 4/ 7;

        Log.d("标", "第一个坐标" + touchX + "点击坐标" + (touchX - w / 2));
        Point point1;
        Point point2;
        Point point3;
        Point point4;
        Point pointCircle;
        if (touchX - xToRightOrLeft * viewWith < w || touchX == xToRightOrLeft * viewWith) {
            float point1x = touchX - w / 2;//防止弹窗超出界面
            if (point1x < xToRightOrLeft || point1x == xToRightOrLeft) {
                point1x = touchX;
            }
            point1 = new Point((int) (point1x), (int) touchY);
            brokenPath.moveTo(point1.x, point1.y);
            //第一象限
            if (getYHeight() - touchY < h || getYHeight() - touchY == h) {
                point2 = new Point((int) (point1x + w), (int) touchY);
                brokenPath.lineTo(point2.x, point2.y);
                point3 = new Point((int) (point1x + w), (int) (touchY - h));
                brokenPath.lineTo(point3.x, point3.y);
                point4 = new Point((int) point1x, (int) (touchY - h));
                brokenPath.lineTo(point4.x, point4.y);
                brokenPath.close();
                pointCircle = point4;
            } else {
                //第四象限
                point2 = new Point((int) (point1x + w), (int) touchY);
                brokenPath.lineTo(point2.x, point2.y);
                point3 = new Point((int) (point1x + w), (int) (touchY + h));
                brokenPath.lineTo(point3.x, point3.y);
                point4 = new Point((int) point1x, (int) (touchY + h));
                brokenPath.lineTo(point4.x, point4.y);
                brokenPath.close();
                pointCircle = point1;
            }
        } else {
            //防止弹窗超出界面
            float point1x = touchX + w / 2;
            if (point1x > (1 - xToRightOrLeft) * viewWith || point1x == (1 - xToRightOrLeft) * viewWith) {
                point1x = touchX;
            }
            point1 = new Point((int) (point1x), (int) touchY);
            brokenPath.moveTo(point1.x, point1.y);
            if (getYHeight() - touchY < h || getYHeight() - touchY == h) {
                //第二象限
                point2 = new Point((int) (point1x - w), (int) touchY);
                brokenPath.lineTo(point2.x, point2.y);
                point3 = new Point((int) (point1x - w), (int) (touchY - h));
                brokenPath.lineTo(point3.x, point3.y);
                point4 = new Point((int) (point1x), (int) (touchY - h));
                brokenPath.lineTo(point4.x, point4.y);
                brokenPath.close();
                pointCircle = point3;
            } else {
                // 第三象限
                point2 = new Point((int) (point1x - w), (int) touchY);
                brokenPath.lineTo(point2.x, point2.y);
                point3 = new Point((int) (point1x - w), (int) (touchY + h));
                brokenPath.lineTo(point3.x, point3.y);
                point4 = new Point((int) (point1x), (int) (touchY + h));
                brokenPath.lineTo(point4.x, point4.y);
                brokenPath.close();
                pointCircle = point2;
            }
        }

        brokenTextPaint.setAntiAlias(true);
        brokenTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        brokenTextPaint.setStrokeWidth(dipToPx(brokenLineWith));
        brokenTextPaint.setStrokeCap(Paint.Cap.ROUND);
        brokenTextPaint.setColor(0x00ffffff);
//画圆角矩形
        drawRoundRectView(canvas, pointCircle, w, h);
        //画 悬浮窗内 内容  不画矩形路径 改画 圆角矩形
        canvas.drawPath(brokenPath, brokenTextPaint);
        drawFloatText(canvas, pointCircle, w, h);

//


    }

    private void drawRoundRectView(Canvas canvas, Point point, float w, float h) {
        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL);//充满
        p.setColor(0x88696969);//透明灰
        p.setColor(0x88696969);
        p.setAntiAlias(true);// 设置画笔的锯齿效果
        RectF oval3 = new RectF(point.x, point.y, point.x + w, point.y + h);// 设置个新的长方形
        canvas.drawRoundRect(oval3, 20, 15, p);//第二个参数是x半径，第三个参数是y半径
    }

    //绘制显示浮动文字的背景
    private void drawFloatTextBackground(Canvas canvas, int x, int y) {
        brokenPath.reset();
        brokenTextPaint.setColor(brokenLineColor);
        brokenTextPaint.setStyle(Paint.Style.FILL);

        //P1
        Point point = new Point(x, y);
        brokenPath.moveTo(point.x, point.y);

        //P2
        point.x = point.x + dipToPx(5);
        point.y = point.y - dipToPx(5);
        brokenPath.lineTo(point.x, point.y);

        //P3
        point.x = point.x + dipToPx(12);
        brokenPath.lineTo(point.x, point.y);

        //P4
        point.y = point.y - dipToPx(17);
        brokenPath.lineTo(point.x, point.y);

        //P5
        point.x = point.x - dipToPx(34);
        brokenPath.lineTo(point.x, point.y);

        //P6
        point.y = point.y + dipToPx(17);
        brokenPath.lineTo(point.x, point.y);

        //P7
        point.x = point.x + dipToPx(12);
        brokenPath.lineTo(point.x, point.y);

        //最后一个点连接到第一个点
        brokenPath.lineTo(x, y);

        canvas.drawPath(brokenPath, brokenTextPaint);
    }

    /**
     * 画虚线
     *
     * @param canvas 画布
     * @param startX 起始点X坐标
     * @param startY 起始点Y坐标
     * @param stopX  终点X坐标
     * @param stopY  终点Y坐标
     */
    private void drawDottedLine(Canvas canvas, float startX, float startY, float stopX, float stopY, int colorInt) {
        //虚线画笔
        Paint dottedPaint;//虚线画笔
        dottedPaint = new Paint();
        dottedPaint.setAntiAlias(true);
        dottedPaint.setStyle(Paint.Style.STROKE);
        dottedPaint.setStrokeWidth(brokenLineWith);
        dottedPaint.setColor(colorInt);
        dottedPaint.setStrokeCap(Paint.Cap.ROUND);
        dottedPaint.setPathEffect(new DashPathEffect(new float[]{20, 10}, 4));
        dottedPaint.setStrokeWidth(1);
        // 实例化路径
        Path mPath = new Path();
        mPath.reset();
        // 定义路径的起点
        mPath.moveTo(startX, startY);
        mPath.lineTo(stopX, stopY);
        canvas.drawPath(mPath, dottedPaint);

    }


    public static Long getMaxNum(Long args[]) {
        int max = 0;
        for (int i = 0; i < args.length; i++) {
            if (args[i] > args[max]) {
                max = i;
            }
        }
        return args[max];
    }

    //得到Y轴最小值
    public static int getMinNum(int args[]) {
        int min = 0;
        for (int i = 0; i < args.length; i++) {
            if (args[i] < args[min]) {
                min = i;
            }
        }
        return args[min];
    }

    //求Y轴 向上求到整数
    public static int getIntMaxFormat(Long num) {
        Double format = (num / 10.0);
        return (int) (Math.ceil(format) * 10);
    }
    /**
     * dip 转换成px
     *dp表示大小
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

    /**
     * 得到文字宽度
     *
     * @param paint
     * @param str
     * @return
     */

    public static int getTextWidth(Paint paint, String str) {
        int w = 0;
        if (str != null && str.length() > 0) {
            int len = str.length();
            float[] widths = new float[len];
            paint.getTextWidths(str, widths);
            for (int j = 0; j < len; j++) {
                w += (int) Math.ceil(widths[j]);
            }
        }
        return w;
    }




















}
