package com.example.gaope.pathbessel;

import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.TranslateAnimation;

public class WaveActivity extends AppCompatActivity {

    private WaveView waveView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave);
//        waveView = (WaveView) findViewById(R.id.wave);
//        TranslateAnimation translateAnimation = new TranslateAnimation(0,1000,0,0);
//        translateAnimation.setDuration(8000);
//        waveView.startAnimation(translateAnimation);
    }
}
