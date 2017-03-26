package com.lhc.android.gz_guide.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.util.DimensionUtil;

public class SpotDetailActivity extends AppCompatActivity implements ViewSwitcher.ViewFactory, GestureDetector.OnGestureListener {

    public static final String SPOT_ID = "spotId";

    private ImageSwitcher imageSwitcher;
    private GestureDetector gestureDetector;

    private int[] images = {R.drawable.gztown1, R.drawable.gztown2, R.drawable.gztown3, R.drawable.gztown4, R.drawable.gztown5, R.drawable.gztown6, R.drawable.gztown7};
    private int currentImage = 0;
    private int switchHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_detail);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle(R.string.spot_detail);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        initView();

        switchHeight = DimensionUtil.dp2px(this,180);


    }

    public void initView() {
        imageSwitcher = (ImageSwitcher) findViewById(R.id.is_spot_detail);
        imageSwitcher.setFactory(this);
        imageSwitcher.setLongClickable(true);
        gestureDetector = new GestureDetector(this,this);
//        imageSwitcher.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return gestureDetector.onTouchEvent(event);
//            }
//        });

    }


    @Override
    public View makeView() {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(images[currentImage]);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return imageView;
    }


    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (velocityX > 0) {
            if (currentImage > 0) {
                currentImage--;
                imageSwitcher.setImageResource(images[currentImage]);
            }
        }else{
            if(currentImage < images.length - 1){
                currentImage ++;
                imageSwitcher.setImageResource(images[currentImage]);
            }
        }
    return false;
}

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        float Y = ev.getRawY();
        if(Y < switchHeight){
            gestureDetector.onTouchEvent(ev);
        }
        return super.dispatchTouchEvent(ev);
    }
}
