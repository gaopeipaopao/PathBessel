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
import android.view.View;

/**
 * 圆形变化为心形
 * 控制点的变化
 * Created by gaope on 2018/4/30.
 */



public class ExampleView extends View {

    private static final String TAG = "ExampleView";
    private static float C = 0.551915024494f;

    /**
     * 半径
     */
    private float radius = 300;

    /**
     * 控制点距数据点的距离
     */
    private float length = radius * C;

    /**
     * 数据点
     */
    private float[] data = new float[8];

    /**
     *控制点
     */
    private float[] control = new float[16];

    /**
     * 中心点
     */
    private PointF center;

    private Paint paint;

    private int aa = 1;

    public ExampleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8);
        paint.setColor(Color.BLACK);

        center = new PointF(0,0);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        center.x = w/2;
        center.y = h/2;


        data[0] = center.x + radius;
        data[1] = center.y;
        data[2] = center.x;
        data[3] = center.y + radius;
        data[4] = center.x - radius;
        data[5] = center.y;
        data[6] = center.x;
        data[7] = center.y - radius;

        control[0] = data[0];
        control[1] = data[1] - length;
        control[2] = data[0];
        control[3] = data[1] + length;
        control[4] = data[2] + length;
        control[5] = data[3];
        control[6] = data[2] - length;
        control[7] = data[3];
        control[8] = data[4];
        control[9] = data[5] + length;
        control[10] = data[4];
        control[11] = data[5] - length;
        control[12] = data[6] - length;
        control[13] = data[7];
        control[14] = data[6] + length;
        control[15] = data[7];

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.d(TAG,"centerX:"+center.x+"     centerY:"+center.y);

        Log.d(TAG,"data1:"+data[0] +"   "+data[1]);
        Log.d(TAG,"control1:"+control[0] +"   "+control[1]);
        Log.d(TAG,"control2:"+control[2] +"   "+control[3]);


        Log.d(TAG,"data2:"+data[2] +"   "+data[3]);
        Log.d(TAG,"control3:"+control[4] +"   "+control[5]);
        Log.d(TAG,"control4:"+control[6] +"   "+control[7]);

        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(data[0],data[1],10,paint);
        canvas.drawCircle(data[2],data[3],10,paint);
        canvas.drawCircle(data[4],data[5],10,paint);
        canvas.drawCircle(data[6],data[7],10,paint);

        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        Path path = new Path();

        path.moveTo(data[0],data[1]);
        path.cubicTo(control[2],control[3],control[4],control[5],data[2],data[3]);
        path.cubicTo(control[6],control[7],control[8],control[9],data[4],data[5]);
        path.cubicTo(control[10],control[11],control[12],control[13],data[6],data[7]);
        path.cubicTo(control[14],control[15],control[0],control[1],data[0],data[1]);
        canvas.drawPath(path,paint);

        if (aa == 1)
        reDraw(canvas);
        aa=2;
    }

    private void reDraw(Canvas canvas) {
        data[7] = data[6] + 50;
        control[2] = control[2] - 10;
        control[3] = control[3] - 10;
        control[4] = control[4] - 80;
        control[5] = control[5] - 80;
        control[6] = control[6] + 80;
        control[7] = control[7] - 80;
        control[8] = control[8] + 10;
        control[9] = control[9] - 10;
        invalidate();
    }
}
