package com.singal.zy.normal_libs.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.singal.zy.normal_libs.R;

import java.util.Calendar;

/**
 *
 * 继承自 SurfaceView 的时钟控件
 *
 * Created by li on 2017/4/19.
 */

public class ClockView extends SurfaceView implements SurfaceHolder.Callback{

    private SurfaceHolder mSurfaceHolder;
    private RenderThread mRenderThread;
    //绘制开关
    private boolean isDraw = false;

    private Canvas canvas = null;
    private Canvas canvas2 = null;
    private Bitmap bitmapCache = null;

    //画笔
    private Paint mPaint;
    //时分秒
    private int h ,m , s;
    //每个刻度见的值 固定为6
    private float fixAngle = 6;
    //指针漏出的一部分长度
    private int outLen = 30;
    private Rect mTextBound;
    //大刻度长度
    private int bigDegreeLength = 50;
    //小刻度长度
    private int smallDegreeLength = 30;
    //刻度盘半径
    private int radius = 200;
    //默认表盘的背景颜色
    private int clock_bg_color = R.color.clock_default_bg_color;
    //刻度数值默认颜色
    private int degreeTextColor = Color.BLACK;
    //刻度数值默认字体大小
    private int degreeTextSize = 40;
    //默认大刻度的颜色
    private int bigDegreeColor = Color.RED;
    //默认小刻度的颜色
    private int smallDegreeColor = Color.GRAY;
    //原点的默认颜色
    private int centerPointColor = Color.WHITE;
    //时分秒的刻度颜色
    private int hourPointColor = Color.BLACK;
    private int minutePointColor = Color.GRAY;
    private int secondPointColor = Color.GREEN;


    public ClockView(Context context) {
        this(context,null);
    }

    public ClockView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        mSurfaceHolder = this.getHolder();
        mSurfaceHolder.addCallback(this);
        mRenderThread = new RenderThread();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);

        mTextBound = new Rect();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        isDraw = true;
        mRenderThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        isDraw = false;
    }

    private class RenderThread extends Thread{
        @Override
        public void run() {
            while (isDraw){
                drawUI();
            }

            super.run();
        }
    }

    private void drawUI() {
        try {
            canvas = mSurfaceHolder.lockCanvas();
            //缓存一个图层
            if(bitmapCache == null){
                bitmapCache = Bitmap.createBitmap(canvas.getWidth(),canvas.getHeight(), Bitmap.Config.ARGB_4444);
            }
            canvas2 = new Canvas(bitmapCache);
            canvas2.drawColor(getResources().getColor(clock_bg_color));
            drawCanvas(canvas2);
            canvas.drawBitmap(bitmapCache,0,0,mPaint);

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                mSurfaceHolder.unlockCanvasAndPost(canvas);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void drawCanvas(Canvas canvas) {
        if (canvas == null) return;
        //画刻度盘
        drawScale(canvas);
        //画指针
        drawPointer(canvas);
        //画原点
        drawPoint(canvas);
    }

    /**
     * 画刻度盘  通过画直线然后再旋转画布实现
     *
     * @param canvas
     */
    private void drawScale(Canvas canvas) {
        canvas.translate(getMeasuredWidth() / 2, getMeasuredHeight() / 2);
        int currentP = 0;
        String text = "";
        int l;
        mPaint.setStrokeWidth(4);

        for (int i = 0; i < 360 / fixAngle; i++) {
            if (i % 5 == 0) {
                if (currentP == 0) {
                    text = "12";
                } else {
                    text = currentP + "";
                }
                currentP++;
                mPaint.setColor(degreeTextColor);
                mPaint.setTextSize(degreeTextSize);
                mPaint.getTextBounds(text, 0, text.length(), mTextBound);
                //绘制时间刻度值
                canvas.drawText(text, 0, text.length(), -mTextBound.width() / 2, -(radius + bigDegreeLength + 20), mPaint);

                mPaint.setColor(bigDegreeColor);
                l = bigDegreeLength;

            } else {
                mPaint.setColor(smallDegreeColor);
                l = smallDegreeLength;
            }
            canvas.drawLine(0, -radius, 0, -(radius + l), mPaint);
            canvas.rotate(fixAngle);
        }
    }

    /**
     * 画指针
     * @param canvas
     */
    private void drawPointer(Canvas canvas) {
        h = Calendar.getInstance().get(Calendar.HOUR);
        m = Calendar.getInstance().get(Calendar.MINUTE);
        s = Calendar.getInstance().get(Calendar.SECOND);

        //保存画布状态
        canvas.save();

        drawH(canvas);
        drawM(canvas);
        drawS(canvas);
    }

    /**
     * 画原点
     * @param canvas
     */
    private void drawPoint(Canvas canvas) {
        mPaint.setColor(centerPointColor);
        canvas.drawCircle(0, 0, 10, mPaint);
    }


    /**
     * 画秒针
     *
     * @param canvas
     */
    private void drawS(Canvas canvas) {

        double angle = (s * fixAngle - 90) * Math.PI / 180;
        int mSecondLen = 280;
        int startX = -(int) (outLen * Math.cos(angle));
        int startY = -(int) (outLen * Math.sin(angle));

        int endX = (int) ((mSecondLen - outLen) * Math.cos(angle));
        int endY = (int) ((mSecondLen - outLen) * Math.sin(angle));

        mPaint.setStrokeWidth(3);
        mPaint.setColor(secondPointColor);
        canvas.drawLine(startX, startY, endX, endY, mPaint);

    }

    /**
     * 画分针
     *
     * @param canvas
     */
    private void drawM(Canvas canvas) {
        double angle = (m * 6 - 90) * Math.PI / 180;
        int mMinLen = 240;
        int startX = -(int) (outLen * Math.cos(angle));
        int startY = -(int) (outLen * Math.sin(angle));

        int endX = (int) ((mMinLen - outLen) * Math.cos(angle));
        int endY = (int) ((mMinLen - outLen) * Math.sin(angle));
        mPaint.setStrokeWidth(5);
        mPaint.setColor(minutePointColor);
        canvas.drawLine(startX, startY, endX, endY, mPaint);
    }

    /**
     * 画时针
     *
     * @param canvas
     */
    private void drawH(Canvas canvas) {

        double angle = (h * 6 * 5 - 90) * Math.PI / 180 + ((m * 1.0f / 60 * 30) * Math.PI / 180);
        int mHourLen = 200;
        int startX = -(int) (outLen * Math.cos(angle));
        int startY = -(int) (outLen * Math.sin(angle));

        int endX = (int) ((mHourLen - outLen) * Math.cos(angle));
        int endY = (int) ((mHourLen - outLen) * Math.sin(angle));
        mPaint.setColor(hourPointColor);
        mPaint.setStrokeWidth(8);
        canvas.drawLine(startX, startY, endX, endY, mPaint);
    }

    public int getDegreeTextColor() {
        return degreeTextColor;
    }

    /**
     * 设置表盘时间文字的颜色
     *
     * @param degreeTextColor
     */
    public void setDegreeTextColor(int degreeTextColor) {
        this.degreeTextColor = degreeTextColor;
    }

    public int getBigDegreeColor() {
        return bigDegreeColor;
    }

    /**
     * 设置 大刻度 颜色
     * @param bigDegreeColor
     */
    public void setBigDegreeColor(int bigDegreeColor) {
        this.bigDegreeColor = bigDegreeColor;
    }

    public int getSmallDegreeColor() {
        return smallDegreeColor;
    }

    /**
     * 设置表盘小刻度颜色
     *
     * @param smallDegreeColor
     */
    public void setSmallDegreeColor(int smallDegreeColor) {
        this.smallDegreeColor = smallDegreeColor;
    }

    public int getCenterPointColor() {
        return centerPointColor;
    }

    /**
     * 设置表盘中心原点颜色
     *
     * @param centerPointColor
     */
    public void setCenterPointColor(int centerPointColor) {
        this.centerPointColor = centerPointColor;
    }

    public int getHourPointColor() {
        return hourPointColor;
    }

    /**
     * 设置表盘小时刻度颜色
     *
     * @param hourPointColor
     */
    public void setHourPointColor(int hourPointColor) {
        this.hourPointColor = hourPointColor;
    }

    public int getMinutePointColor() {
        return minutePointColor;
    }

    /**
     * 设置表盘分钟刻度颜色
     *
     * @param minutePointColor
     */
    public void setMinutePointColor(int minutePointColor) {
        this.minutePointColor = minutePointColor;
    }

    public int getSecondPointColor() {
        return secondPointColor;
    }

    public void setSecondPointColor(int secondPointColor) {
        this.secondPointColor = secondPointColor;
    }

    public int getClock_bg_color() {
        return clock_bg_color;
    }

    /**
     * 设置表盘背景颜色
     *
     * @param clock_bg_color
     */
    public void setClock_bg_color(int clock_bg_color) {
        this.clock_bg_color = clock_bg_color;
    }

    public int getOutLen() {
        return outLen;
    }

    public void setOutLen(int outLen) {
        this.outLen = outLen;
    }

    public int getBigDegreeLength() {
        return bigDegreeLength;
    }

    public void setBigDegreeLength(int bigDegreeLength) {
        this.bigDegreeLength = bigDegreeLength;
    }

    public int getSmallDegreeLength() {
        return smallDegreeLength;
    }

    public void setSmallDegreeLength(int smallDegreeLength) {
        this.smallDegreeLength = smallDegreeLength;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public boolean isDraw() {
        return isDraw;
    }

    public void setDraw(boolean draw) {
        isDraw = draw;
    }
}
