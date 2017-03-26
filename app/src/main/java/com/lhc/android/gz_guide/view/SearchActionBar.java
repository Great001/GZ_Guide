package com.lhc.android.gz_guide.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.util.ToastUtil;

/**
 * Created by Administrator on 2017/3/23.
 */
public class SearchActionBar extends LinearLayout implements View.OnClickListener{

    private ImageView mIvNavigateIcon;
    private ImageButton mIbtnLocation;
    private EditText mEtSearchPlace;
    private OnArrowLeftClickListener arrowLeftClickListener;

    private Context context;
    public SearchActionBar(Context context){
        super(context,null);
        this.context = context;
    }

    public SearchActionBar(Context context,AttributeSet attrs){
        super(context,attrs);
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.search_actionbar,null);
        mIbtnLocation = (ImageButton) view.findViewById(R.id.ibtn_location);
        mIvNavigateIcon = (ImageView) view.findViewById(R.id.iv_navigate_icon);
        mEtSearchPlace = (EditText) view.findViewById(R.id.et_search_places);

        mIbtnLocation.setOnClickListener(this);
        mIvNavigateIcon.setOnClickListener(this);

        addView(view);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_navigate_icon:
                if(arrowLeftClickListener != null){
                    arrowLeftClickListener.onArrowLeftClick();
                }
                break;
            case R.id.ibtn_location:
                ToastUtil.show(context,"菜单");
                break;
            default:
                break;
        }
    }

    public void show(){
        if(getVisibility() != VISIBLE){
            setVisibility(VISIBLE);
        }
    }

    public void hide(){
        if(getVisibility()== VISIBLE) {
            setVisibility(GONE);
        }
    }




    public void setOnArrowLeftClickListener(OnArrowLeftClickListener listener){
        arrowLeftClickListener = listener;
    }

    public interface  OnArrowLeftClickListener{
        void onArrowLeftClick();
    }

}
