package com.example.gaope.pathbessel;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Loader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by gaope on 2018/5/7.
 */

public class WaveView extends View {

    private static final String TAG = "WaveView";
    private PointF center;
    private PointF test;
    private Paint paint;
    private float[] point;
    private float[] control;
    private int a = 1;

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        paint.setAlpha(18);
        center = new PointF(0,0);
        test = new PointF(0,0);
        point = new float[10];
        control = new float[8];
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        center.x = 0;
        center.y = h/2 + 200;

        Log.d(TAG,"width:"+getWidth());

        point[0] = center.x - getWidth();
        point[1] = center.y;
        point[2] = center.x - getWidth()/2;
        point[3] = center.y;
        point[4] = center.x;
        point[5] = center.y;
        point[6] = center.x + getWidth()/2;
        point[7] = center.y;
        point[8] = center.x + getWidth();
        point[9] = center.y;

        control[0] = (point[0] + point[2]) / 2;
        control[1] = center.y - 50;
        control[2] = (point[2] + point[4]) / 2;
        control[3] = center.y + 50;
        control[4] = (point[4] + point[6]) / 2;
        control[5] = center.y - 50;
        control[6] = (point[6] + point[8]) / 2;
        control[7] = center.y + 50;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(test.x,test.y,10,paint);


        Path path = new Path();
        path.reset();
        path.moveTo(point[0],point[1]);
        path.quadTo(control[0],control[1],point[2],point[3]);
        path.quadTo(control[2],control[3],point[4],point[5]);
        path.quadTo(control[4],control[5],point[6],point[7]);
        path.quadTo(control[6],control[7],point[8],point[9]);
        path.lineTo(point[8],getHeight());
        path.lineTo(point[0],getHeight());
        path.close();
        canvas.drawPath(path,paint);


        if(a == 1){
            aa();

        }

        a ++;

    }



    private void aa() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat( point[0],point[4]);
      //  valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                point[0] = (float) animation.getAnimatedValue();
                Log.d(TAG,"point0:"+point[0]);
                point[2] = point[0] + getWidth()/2;
                point[4] = point[2] + getWidth()/2;
                point[6] = point[4] + getWidth()/2;
                point[8] = point[6] + getWidth()/2;
                Log.d(TAG,"point2:"+point[2]);
                Log.d(TAG,"point4:"+point[4]);
                Log.d(TAG,"point6:"+point[6]);
                Log.d(TAG,"point8:"+point[8]);

                control[0] = (point[0] + point[2]) / 2;
                control[2] = (point[2] + point[4]) / 2;
                control[4] = (point[4] + point[6]) / 2;
                control[6] = (point[6] + point[8]) / 2;

                invalidate();
            }
        });

        valueAnimator.start();
    }



}
