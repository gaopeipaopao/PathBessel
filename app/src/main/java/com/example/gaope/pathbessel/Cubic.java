package com.example.gaope.pathbessel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Cubic extends AppCompatActivity {

    private static final String TAG = "Cubic";
    private Button cubic_one;
    private Button cubic_two;
    public int  mode;
    private CubicView cubicView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cubic);
        cubic_one = (Button)findViewById(R.id.cubic_one);
        cubic_two = (Button)findViewById(R.id.cubic_two);
        cubicView = (CubicView)findViewById(R.id.cubicview);

        cubic_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode = 1;
                cubicView.setMode(mode);
            }
        });

        cubic_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode = 2;
                cubicView.setMode(mode);
            }
        });
//        Log.d(TAG,"mode:"+mode);

    }
}
