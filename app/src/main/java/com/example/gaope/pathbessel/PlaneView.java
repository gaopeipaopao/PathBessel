package com.example.gaope.pathbessel;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by gaope on 2018/5/9.
 */

public class PlaneView extends View {

    private static final String TAG = "PlaneView";

    private Paint paint;
    private Bitmap planeBitmap;
    private Drawable drawable;
    private PointF pointF;
    private PointF endPoint;
    private int a = 1;
    private PointF contorl;

    public PlaneView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        pointF = new PointF(0,0);
        endPoint = new PointF(0,0);
        contorl = new PointF(200,500);
        drawable =  getContext().getResources().getDrawable(R.drawable.plane_photo);
        paint = new Paint();
        paint.setAlpha(800);
        paint.setColor(Color.RED);
   //     paint.setStyle(Paint.Style.FILL);
        planeBitmap = BitmapFactory.decodeResource(getContext().getResources(),
                R.drawable.plane_photo);
        planeBitmap = Matrix.matrixPhoto(planeBitmap,
                drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        endPoint.x = w - 200;
        endPoint.y = h - 200;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        contorl.x = event.getX();
        contorl.y = event.getY();
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(contorl.x,contorl.y,5,paint);

        paint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.moveTo(0,0);
        path.quadTo(contorl.x,contorl.y,endPoint.x,endPoint.y);
        canvas.drawPath(path,paint);
        canvas.drawBitmap(planeBitmap,pointF.x,pointF.y,paint);
//        Log.d(TAG,"width："+drawable.getIntrinsicWidth()
//                +"    height:"+drawable.getIntrinsicHeight());
        Log.d(TAG,"endX:" + endPoint.x + "   endY:"+endPoint.y);
        if (a == 1){
            aa();
        }
        a++;

    }

    private void aa() {
        ValueAnimator valueAnima = ValueAnimator.ofObject(new PlaneEvaluator(contorl),
                pointF,endPoint);
        valueAnima.setDuration(8000);
        valueAnima.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pp = (PointF) animation.getAnimatedValue();
                pointF.x = pp.x;
                pointF.y = pp.y;
                invalidate();
            }
        });
        valueAnima.start();

    }

}
