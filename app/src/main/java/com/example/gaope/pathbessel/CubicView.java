package com.example.gaope.pathbessel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * 三阶贝塞尔曲线
 * Created by gaope on 2018/4/30.
 */

public class CubicView extends View {

    private static final String TAG = "CubicView";

    /**
     * 中心点
     */
    private PointF center ;

    /**
     * 控制点1
     */
    private PointF control1;

    /**
     * 控制点2
     */
    private PointF control2 ;

    /**
     * 起点
     */
    private PointF start ;

    /**
     * 终点
     */
    private PointF end ;

    /**
     * 画笔
     */
    private Paint paint;

    /**
     * 控制是哪个触摸点
     */
    private  int mode ;


    public CubicView(Context context)  {
        super(context);
    }

    public CubicView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        center = new PointF(0,0);
        control1 = new PointF(0,0);
        control2 = new PointF(0,0);
        start = new PointF(0,0);
        end  = new PointF(0,0);

        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        center.x = w/2;
        center.y = h/2;
//        Log.d(TAG,"getWidth:"+getWidth());
//        Log.d(TAG,"centerX:"+center.x+"   centerY:"+center.y);

        start.x = center.x - 200;
        start.y = center.y;
//        Log.d(TAG,"startX:"+start.x+"   startY:"+start.y);

        end.x = center.x + 200;
        end.y = center.y;

        control1.x = center.x - 100;
        control1.y = center.y - 200;

        control2.x = center.x + 100;
        control2.y = center.y - 200;

    }

    public void setMode(int mode1){
        mode = mode1;
        Log.d(TAG,"aaa");
        Log.d(TAG,"mode1:"+mode1);
        Log.d(TAG,"mode:"+mode);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG,"bbb");
        Log.d(TAG,"mode:"+mode);
        if (mode > 1){
            control2.x = event.getX();
            control2.y = event.getY();
            invalidate();
            return true;
        }else {
            control1.x = event.getX();
            control1.y = event.getY();
            invalidate();
            return true;
        }


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //不要对画布进行操作
        //canvas.translate(center.x,center.y);

        Log.d(TAG,"modeDraw:"+mode);

        paint.setStyle(Paint.Style.FILL);
        //画点
        canvas.drawPoint(start.x,start.y,paint);
        canvas.drawPoint(end.x,end.y,paint);
        canvas.drawPoint(control1.x,control1.y,paint);
        canvas.drawPoint(control2.x,control2.y,paint);

        paint.setStyle(Paint.Style.STROKE);

        Path path = new Path();
        //连线
        path.moveTo(start.x,start.y);
        path.lineTo(control1.x,control1.y);
        path.lineTo(control2.x,control2.y);
        path.lineTo(end.x,end.y);
        paint.setColor(Color.RED);
        canvas.drawPath(path,paint);

        path.reset();


        paint.setColor(Color.BLACK);
        path.moveTo(start.x,start.y);
        path.cubicTo(control1.x, control1.y,control2.x,control2.y,end.x,end.y);
        canvas.drawPath(path,paint);
    }


}
