package com.example.gaope.pathbessel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
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
    private PointF center = new PointF(0,0);

    /**
     * 控制点1
     */
    private PointF control1 = new PointF(0,0);

    /**
     * 控制点2
     */
    private PointF control2 = new PointF(0,0);

    /**
     * 起点
     */
    private PointF start = new PointF(0,0);

    /**
     * 终点
     */
    private PointF end  = new PointF(0,0);

    /**
     * 画笔
     */
    private Paint paint;

    /**
     * 控制是哪个触摸点
     */
    public boolean mode = true;

    /**
     *
     */

    public CubicView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        center.x = getWidth()/2;
        center.y = getHeight()/2;
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8);
        start.x = center.x - 200;
        start.y = 0;

        end.x = center.x + 200;
        end.y = 0;

        control1.x = center.x - 100;
        control1.y = center.y - 200;

        control2.x = center.x + 100;
        control2.y = center.y - 200;
    }

    public CubicView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(getWidth()/2,getHeight()/2);
        canvas.drawPoint(start.x,start.y,paint);
        canvas.drawPoint(end.x,end.y,paint);
        canvas.drawPoint(control1.x,control1.y,paint);
        canvas.drawPoint(control2.x,control2.y,paint);

        Path path = new Path();
        path.moveTo(start.x,start.y);
        path.cubicTo(control1.x, control1.y,control2.x,control2.y,end.x,end.y);
        canvas.drawPath(path,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(mode){
            control1.x = event.getX();
            control1.y = event.getY();
        }else {
            control2.x = event.getX();
            control2.y = event.getY();
        }

        invalidate();
        return true;
    }

    public void setMode(boolean mode){
        this.mode = mode;
    }
}
