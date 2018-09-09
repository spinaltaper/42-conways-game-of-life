package com.example.panos.a42_conways_game_of_life;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements ViewTreeObserver.OnGlobalLayoutListener,
        View.OnTouchListener{

    @BindView(R.id.canvasView) public ImageView imageView;

    float xDown;
    float yDown;

    private int width;
    private int height;

    private Bitmap mBitmap;
    private Canvas mCanvas;

    private Grid gridEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        gridEngine = new Grid();

        ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
        if(viewTreeObserver.isAlive()){
            viewTreeObserver.addOnGlobalLayoutListener(this);
        }
    }

    @Override
    public void onGlobalLayout(){
        initBitmap();
    }

    public void initBitmap(){
        imageView.setOnTouchListener(this);

        int width = imageView.getWidth();
        int height = imageView.getHeight();

        Log.d("Dimensions\n",""+width+"x\n"+height+"y");
        mBitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    @OnClick(R.id.reset)
    public void reset(){
        gridEngine = new Grid();
        clear();
    }


    public void clear(){
        if (mCanvas!=null){
            Log.d("CANVAS","clear");
            mCanvas.drawColor(Color.TRANSPARENT,PorterDuff.Mode.CLEAR);
        }
    }

    public void drawGrid(){
        Paint brush = new Paint(Paint.ANTI_ALIAS_FLAG);
        int black = Color.rgb(0,0,0);
        int unit = width/100;

        brush.setColor(black);
        brush.setStyle(Paint.Style.FILL);

        for(int i = 0; i<gridEngine.gridData.length;i++){
            for(int j = 0;j<gridEngine.gridData[i].length;j++){
                if(gridEngine.gridData[i][j]==true){
                    mCanvas.drawRect(i * unit,j * unit,(i + 1) * unit,(j + 1) * unit,brush);
                }
            }
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent){
        int action = motionEvent.getAction();
        float xx = motionEvent.getX();
        float yy = motionEvent.getY();

        if(action == MotionEvent.ACTION_DOWN){
            Log.d("ACTION","down");
            int xDown = (int)Math.round(xx/mCanvas.getWidth());
            int yDown = (int)Math.round(yy/mCanvas.getHeight());

            gridEngine.toggle(xDown,yDown);
            gridEngine.tick();
            return true;
        }
        return false;
    }


}