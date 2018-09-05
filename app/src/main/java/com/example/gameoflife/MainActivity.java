package com.example.gameoflife;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity
    implements ViewTreeObserver.OnGlobalLayoutListener, View.OnTouchListener{
    @BindView(R.id.) ConstraintLayout layout;

    @BindView(R.id.canvas)
    ImageView Canvas;

    private boolean isLaidOut;
    private Grid Grid;

    public void onGlobalLayout(){
        if(isLaidOut){
            return;
        }

        isLaidOut = true;

        initBitMap();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
