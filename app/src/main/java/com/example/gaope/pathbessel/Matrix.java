package com.example.gaope.pathbessel;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Shader;
import android.util.Log;

/**
 * Created by gaope on 2018/5/9.
 */

public class Matrix {
    private static final String TAG = "Matrix";
    public static Bitmap matrixPhoto (Bitmap bitmap,int width, int height) {
        android.graphics.Matrix matrix = new android.graphics.Matrix();
        float w = (float) (80.0/width);
        float h = (float) (80.0/height);
        matrix.setScale(w,h);
        Log.d(TAG,"wi"+width+"   he:"+height);
        Log.d(TAG,"w"+w+"   h:"+h);
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap,0,0,width,height,matrix,true);
        return bitmap1;
    }
}
