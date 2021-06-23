package com.example.ap2_3.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import com.example.ap2_3.R;

public class Joystick extends View {
    private int halfWidth;
    private int halfHeight;
    private float x;
    private float y;
    private int radius;
    private Paint circlePaint;

    public Joystick(Context context, AttributeSet attrs){
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Joystick, 0, 0);
        circlePaint = new Paint();

        try {
            // get the text and colors specified using the names in attrs.xml
            x = a.getFloat(R.styleable.Joystick_x, 0);
            y = a.getFloat(R.styleable.Joystick_y, 0);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        halfWidth = this.getMeasuredWidth()/2;
        halfHeight = this.getMeasuredHeight()/2;

        if(halfWidth > halfHeight)
            radius = (halfHeight - 25) / 2;
        else
            radius = (halfWidth - 25) / 2;

        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.WHITE);
        canvas.drawCircle(halfWidth * (1 + x), halfHeight * (1 + y), radius, circlePaint);
    }

    public void setX(float x){
        this.x = x;
        invalidate();
        requestLayout();
    }

    public void setY(float y){
        this.y = y;
        invalidate();
        requestLayout();
    }

    boolean isInRange(float x, float y) {
        return x <= 1 && x >= -1 && y <= 1 && y >= -1;
    }

    boolean enabled = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                enabled = true;
                break;
            case MotionEvent.ACTION_MOVE:
                if (enabled) {
                    float currX = event.getX(), currY = event.getY();
                    float newX = (currX - halfWidth) / halfWidth, newY = (currY - halfHeight) / halfHeight;
                    if (isInRange(newX,newY)) {
                        setX(newX);
                        setY(newY);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                enabled = false;
                setX(0);
                setY(0);
                break;
        }

        return true;
    }
}
