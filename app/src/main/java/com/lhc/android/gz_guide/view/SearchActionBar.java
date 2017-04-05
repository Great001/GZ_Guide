package com.lhc.android.gz_guide.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.util.NavigationUtil;
import com.lhc.android.gz_guide.util.ToastUtil;

/**
 * Created by Administrator on 2017/3/23.
 */
public class SearchActionBar extends LinearLayout implements View.OnClickListener {

    private ImageView mIvSearch;
    private ImageButton mIbtnLocation;
    private ImageView mIvCancel;
    private EditText mEtSearchPlace;

    private OnArrowLeftClickListener arrowLeftClickListener;

    private PoiSearch poiSearch;

    private Context context;

    public SearchActionBar(Context context) {
        super(context, null);
        this.context = context;
    }

    public SearchActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.search_actionbar, null);
        mIbtnLocation = (ImageButton) view.findViewById(R.id.ibtn_location);
        mIvCancel = (ImageView) view.findViewById(R.id.iv_searchbar_cancel);
        mIvSearch = (ImageView) view.findViewById(R.id.iv_search);
        mEtSearchPlace = (EditText) view.findViewById(R.id.et_search_places);

        poiSearch = PoiSearch.newInstance();
        poiSearch.setOnGetPoiSearchResultListener(searchResultListener);

        mIbtnLocation.setOnClickListener(this);
        mIvCancel.setOnClickListener(this);
        mIvSearch.setOnClickListener(this);

        mEtSearchPlace.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count > 0){
                    mIvCancel.setVisibility(VISIBLE);
                }else{
                    mIvCancel.setVisibility(GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        addView(view);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search:
                onAddrSearchClick();
                break;
            case R.id.iv_searchbar_cancel:
                mEtSearchPlace.setText("");
                break;
            case R.id.ibtn_location:
                onLocateClick();
                break;
            default:
                break;
        }
    }

    public void show() {
        if (getVisibility() != VISIBLE) {
            setVisibility(VISIBLE);
        }
    }

    public void hide() {
        if (getVisibility() == VISIBLE) {
            setVisibility(GONE);
        }
    }


    //关键字检索
    public void onAddrSearchClick() {
        String addr = mEtSearchPlace.getText().toString().trim();
        if (!addr.isEmpty()) {
            poiSearch.searchInCity(new PoiCitySearchOption()
                    .city("广州")
                    .keyword(addr)
                    .pageNum(0));
        }else{
            ToastUtil.show(context,"搜索关键字不能为空");
        }
    }

    public void onLocateClick(){
        NavigationUtil.navigateToLocationResultActivity(context);
    }

    public void release(){
        poiSearch.destroy();
    }



    public void setOnArrowLeftClickListener(OnArrowLeftClickListener listener) {
        arrowLeftClickListener = listener;
    }

    public interface OnArrowLeftClickListener {
        void onArrowLeftClick();
    }

    OnGetPoiSearchResultListener searchResultListener = new OnGetPoiSearchResultListener() {
        @Override
        public void onGetPoiResult(PoiResult poiResult) {
            NavigationUtil.navigateToSearchResultActivity(context, poiResult);
        }

        @Override
        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

        }

        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

        }
    };

}
