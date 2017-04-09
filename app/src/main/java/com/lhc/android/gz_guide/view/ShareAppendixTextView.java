package com.lhc.android.gz_guide.view;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lhc.android.gz_guide.util.DimensionUtil;

public class ShareAppendixTextView extends TextView {

    private Context context;

    public ShareAppendixTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }


    public ShareAppendixTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public ShareAppendixTextView(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Layout layout = getLayout();
        if (layout != null) {
            int height = (int) Math.ceil(getMaxLineHeight(this.getText().toString()))
                    + getCompoundPaddingTop() + getCompoundPaddingBottom();
            int width = getMeasuredWidth();
            setMeasuredDimension(width, height);
        }
    }

    private float getMaxLineHeight(String str) {
        float height;
        float screenW = DimensionUtil.getScreenWidth(context);
        float paddingLeft = ((RelativeLayout) this.getParent()).getPaddingLeft();
        float paddingReft = ((RelativeLayout) this.getParent()).getPaddingRight();
        //这里具体this.getPaint()要注意使用，要看你的TextView在什么位置，这个是拿TextView父控件的Padding的，为了更准确的算出换行
        int line = (int) Math.ceil((this.getPaint().measureText(str) / (screenW - paddingLeft - paddingReft)));
        height = (this.getPaint().getFontMetrics().descent - this.getPaint().getFontMetrics().ascent) * line;
        return height;
    }
}  