package com.konovus.autotap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout constraintLayout;
    TextView hello;
    Button do_click;
    Button btn_first;
    private float x = 500;
    private float y = 500;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constraintLayout = findViewById(R.id.generalLayout);
        hello = findViewById(R.id.hello);
        do_click = findViewById(R.id.do_click);
        btn_first = findViewById(R.id.btn_first);


        constraintLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                x = event.getRawX();
                y = event.getRawY();

                if(event.getAction() == MotionEvent.ACTION_MOVE){
                    hello.setX(x - hello.getWidth()/2);
                    hello.setY(y - hello.getHeight());
                }
                return true;
            }
        });


        btn_first.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                    Toast.makeText(MainActivity.this, "Btn first tapped", Toast.LENGTH_LONG).show();

                return true;
            }
        });
        do_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hello.setVisibility(View.GONE);
                long downTime = SystemClock.uptimeMillis();
                long eventTime = SystemClock.uptimeMillis() + 100;
// List of meta states found here: developer.android.com/reference/android/view/KeyEvent.html#getMetaState()
                int metaState = 0;
                System.out.println("X, Y -- " + x + ", " + y);
                MotionEvent motionEvent = MotionEvent.obtain(
                        downTime,
                        eventTime,
                        MotionEvent.ACTION_DOWN,
                        x,
                        y,
                        metaState
                );
                ViewGroup viewGroup = constraintLayout;
                viewGroup.dispatchTouchEvent(motionEvent);
            }
        });

    }

}
