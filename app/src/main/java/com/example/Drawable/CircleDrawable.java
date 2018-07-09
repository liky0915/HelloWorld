package com.example.Drawable;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

//Drawable是一个抽象类，是对可绘制物件的抽象，不同于VIEW，Drawable没有事件和交互方法
//Drawable有很多子类，比如BitmapDrawable, ColorDrawable等
public class CircleDrawable extends Drawable {

    private Bitmap mBitmap;
    private Paint mPaint;
    private int mWidth;
    private RectF rectF;

    public CircleDrawable(Bitmap bitmap) {
        this.mBitmap = bitmap;
        BitmapShader shader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);  //为图片创建着色器, 平铺模式：平铺
        mPaint = new Paint();       //创建画笔对象
        mPaint.setAntiAlias(true);  //抗锯齿（平滑）开启
        mPaint.setShader(shader);   //为画笔添加着色器
        mWidth = Math.min(mBitmap.getWidth(), mBitmap.getHeight());     //mWidth取图片长和宽当中的最小值
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        //canvas.drawCircle(mWidth/2,mWidth/2,mWidth/2, mPaint);    //画一个圆形，参数：X坐标，Y坐标，半径，画笔
        canvas.drawRoundRect(rectF,100,100, mPaint);          //画一个圆角矩形， 参数：矩形对象，四个圆角圆心的X坐标，圆角圆心的Y坐标，画笔
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        rectF = new RectF(left, top, right, bottom);                //将图片的四边长度作为矩形的四边，并且实例化这个矩形对象
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha); //为画笔选择透明度
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);     //为画笔选择颜色过滤器
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;     //模糊度 = 半透明
    }

    @Override
    public int getIntrinsicHeight() {
        //return mWidth;                //圆形图片实际高度使用mWidth
        return mBitmap.getHeight();     //圆角图片实际高度使用原尺寸
    }

    @Override
    public int getIntrinsicWidth() {
        //return mWidth;                //圆形图片实际宽度使用mWidth
        return mBitmap.getWidth();      //圆角图片实际宽度使用原尺寸
    }
}
