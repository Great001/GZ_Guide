package com.lhc.android.gz_guide.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.Button;

import com.lhc.android.gz_guide.util.DimensionUtil;

/**
 * Created by Administrator on 2017/3/28.
 */
public class FloatingButton extends Button {

    private float fromX;
    private float toX;
    private float fromY;
    private float toY;
    private int disX;
    private int disY;

    private int newRightMargin;
    private int newBottomMargin;
    private int preRightMargin;
    private int preBottomMargin;
    private int screenWidth;
    private int screenHeight;
    private int maxRightMargin;
    private int maxBottomMargin;

    private final int touchSlop = 10;
    private long preClickTime = 0;

    private ViewGroup.MarginLayoutParams params;

    private OnButtonClickListener listener;

    public FloatingButton(Context context) {
        super(context, null);
        screenHeight = DimensionUtil.getScreentHeight(context);
        screenWidth = DimensionUtil.getScreenWidth(context);
    }

    public FloatingButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        screenHeight = DimensionUtil.getScreentHeight(context);
        screenWidth = DimensionUtil.getScreenWidth(context);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        maxBottomMargin = screenHeight - getHeight();
        maxRightMargin = screenWidth - getWidth();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                fromX = event.getRawX();
                fromY = event.getRawY();
                params = (ViewGroup.MarginLayoutParams) getLayoutParams();
                preRightMargin = params.rightMargin;
                preBottomMargin = params.bottomMargin;
                break;
            case MotionEvent.ACTION_MOVE:
                toX = event.getRawX();
                toY = event.getRawY();
                disX = (int) (toX - fromX);
                disY = (int) (toY - fromY);
                newRightMargin = preRightMargin - disX;
                newBottomMargin = preBottomMargin - disY;
                if(newRightMargin > 0) {
                    if (newRightMargin >= maxRightMargin) {
                        newRightMargin = maxRightMargin;
                    }
                    params.rightMargin = newRightMargin;
                }
                if(newBottomMargin > 0) {
                    if (newBottomMargin >= maxBottomMargin) {
                        newBottomMargin = maxBottomMargin;
                    }
                    params.bottomMargin = newBottomMargin;
                }
                setLayoutParams(params);
                break;
            case MotionEvent.ACTION_UP:
                toX = event.getRawX();
                toY = event.getRawY();
                disX = (int) Math.abs(toX - fromX);
                disY = (int) Math.abs(toY - fromY);
                if(disX  < touchSlop && disY < touchSlop){
                    long currentTime = System.currentTimeMillis();
                    if(currentTime - preClickTime > 2000){
                        preClickTime = currentTime;
                        if(listener != null){
                            listener.onButtonClick();
                        }
                    }
                }
                break;

            default:
                break;
        }
        return super.onTouchEvent(event);
    }


    public void setOnButtonClickListener(OnButtonClickListener listener) {
        this.listener = listener;
    }

    public interface OnButtonClickListener {
        void onButtonClick();
    }

}
