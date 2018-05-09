package com.example.gaope.pathbessel;

import android.animation.TypeEvaluator;
import android.graphics.PointF;
import android.util.Log;

/**
 * Created by gaope on 2018/5/9.
 */

public class PlaneEvaluator implements TypeEvaluator {

    private static final String TAG = "PlaneEvaluator";

    private PointF startPoint;
    private PointF endPoint;
    private PointF contorl;

    public PlaneEvaluator(PointF pointF){
        this.contorl = pointF;
    }

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        PointF pointF = new PointF(0,0);
        startPoint = (PointF) startValue;
        endPoint = (PointF) endValue;

        pointF.x = (1 - fraction) * (1 - fraction) * startPoint.x + 2 * fraction * (1 - fraction)
                * contorl.x + fraction * fraction * endPoint.x;
        pointF.y = (1 - fraction) * (1 - fraction) * startPoint.y + 2 * fraction * (1 - fraction)
                * contorl.y + fraction * fraction * endPoint.y;
        Log.d(TAG,"raction:"+fraction);

        return pointF;
    }
}
