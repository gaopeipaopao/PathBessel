package com.example.gaope.pathbessel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 二阶贝塞尔
 * Created by gaope on 2018/4/30.
 */

public class QuadView extends View {

    private static final String TAG = "QuadView";
    private PointF start,end,control;
    private Paint paint;
    private float centerX ;
    private float centerY;

    public QuadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        start = new PointF(0,0);
        end = new PointF(0,0);
        control = new PointF(0,0);


        paint = new Paint();
        paint.setStrokeWidth(8);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w/2;
        centerY = h/2;

        start.x = centerX - 200;
        start.y = centerY;
        end.x = centerX + 200;
        end.y = centerY;
        control.x = centerX;
        control.y = centerY - 100;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        control.x = event.getX();
        control.y = event.getY();
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
      //  canvas.translate(getWidth()/2,getHeight()/2);

        canvas.drawPoint(start.x,start.y,paint);
        canvas.drawPoint(end.x,end.y,paint);
        canvas.drawPoint(control.x,control.y,paint);

        paint.setColor(Color.RED);
        canvas.drawLine(start.x,start.y,control.x,control.y,paint);
        canvas.drawLine(end.x,end.y,control.x,control.y,paint);

        Path path = new Path();
        paint.setColor(Color.BLACK);

        path.moveTo(start.x,start.y);
        path.quadTo(control.x,control.y,end.x,end.y);
        canvas.drawPath(path,paint);


    }
}
