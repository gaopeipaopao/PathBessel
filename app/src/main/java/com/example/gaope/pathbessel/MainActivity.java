package com.example.gaope.pathbessel;

import android.content.Intent;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button two;
    private Button three;
    private Button example;
    private Button wave;
    private Button plane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        two = (Button)findViewById(R.id.two);
        three = (Button)findViewById(R.id.three);
        example = (Button)findViewById(R.id.example);
        wave = (Button)findViewById(R.id.wave);
        plane = (Button)findViewById(R.id.plane);


        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Quad.class);
                startActivity(intent);
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Cubic.class);
                startActivity(intent);
            }
        });

        example.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Example.class);
                startActivity(intent);
            }
        });
        wave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,WaveActivity.class);
                startActivity(intent);
            }
        });
        plane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,PlaneActivity.class);
                startActivity(intent);
            }
        });
    }
}
