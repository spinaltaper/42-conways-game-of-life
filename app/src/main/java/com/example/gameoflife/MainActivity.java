package com.example.gameoflife;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
    implements ViewTreeObserver.OnGlobalLayoutListener, View.OnTouchListener{
    @BindView(R.id.layout) ConstraintLayout layout;

    @BindView(R.id.canvas) ImageView canvasView;

    private boolean isLaidOut;
    private Grid Grid;

    private Canvas mCanvas;
    private Bitmap mBitmap;

    private int mWidth;
    private int mHeight;

    private double xdown;
    private double ydown;

    public void onGlobalLayout(){
        if(isLaidOut){
            return;
        }

        isLaidOut = true;

        initBitMap();

        canvasView.setOnTouchListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        isLaidOut = false;

    }

    public void initBitMap() {
        mWidth = canvasView.getWidth();
        mHeight = canvasView.getHeight();

        mBitmap = Bitmap.createBitmap(mWidth,mHeight, Bitmap.Config.ARGB_4444);
        mCanvas = new Canvas(mBitmap);
    }

    public void drawGrid(){
        Paint brush = new Paint(Paint.ANTI_ALIAS_FLAG);
        int black = Color.rgb(0,0,0);
        int unit = mWidth/100;

        brush.setColor(black);
        brush.setStyle(Paint.Style.FILL);

        for(int i = 0; i<Grid.gridData.length;i++){
            for(int j = 0;j<Grid.gridData[i].length;j++){
                if(Grid.gridData[i][j]==true){
                    mCanvas.drawRect(i * unit,j * unit,(i + 1) * unit,(j + 1) * unit,brush);
                }
            }
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent){
        Log.d("ACTION",""+motionEvent.getAction());
        if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
            xdown = motionEvent.getX();
            ydown = motionEvent.getX();
        }
        return true;
    }
//    @Override
//    public boolean onTouch(View canvasView, MotionEvent motionEvent){
//        Log.d("ACTION",""+motionEvent.getAction());
//        if(motionEvent.getAction()==MotionEvent.ACTION_UP){
//            xdown = motionEvent.getX();
//            ydown = motionEvent.getX();
//            int gridValX = (int)Math.round(xdown/canvasView.getHeight()*100);
//            int gridValY = (int)Math.round(ydown/canvasView.getWidth()*100);
//            Grid.toggle(gridValX,gridValY);
//        }
//    }

    public void tick(){
        Grid.tick();
        drawGrid();
    }
}
