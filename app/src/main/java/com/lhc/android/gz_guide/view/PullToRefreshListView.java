package com.lhc.android.gz_guide.view;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lhc.android.gz_guide.R;

/**
 * Created by Administrator on 2017/4/12.
 */
public class PullToRefreshListView extends LinearLayout implements View.OnTouchListener {

    //该PullToRefreshView包含一个headView和一个ListView
    private View headView;
    private ListView listView;


    //刷新动作的状态
    private final static int STATUS_PULL_TO_REFRESH = 0;
    private final static int STATUS_RELEASE_TO_REFRESH = 1;
    private final static int STATUS_REFRESHING = 2;
    private final static int STATUS_REFRESH_FINISHED = 3;


    //记录当前刷新的动作状态
    private int currentStatus = STATUS_REFRESH_FINISHED;
    private int lastStatus;

    private float Ydown;

    //判断是否可以刷新
    private boolean isAbleToPULL;

    //通过设置marginTop为负值将headView隐藏掉
    private int hideHeaderHeight;

    //通过headViewd的marginLayoutParams来实现下拉刷新
    private MarginLayoutParams headerParams;

    private ProgressBar progressBar;
    private TextView mTvRefreshStatus, mTvRefreshAct;

    private boolean loadOnce = false;

    public PullToRefreshListView(Context context) {
        super(context);
    }

    public PullToRefreshListView(Context context, AttributeSet attr) {
        super(context, attr);

        headView = LayoutInflater.from(context).inflate(R.layout.listview_header_view, null);
        progressBar = (ProgressBar) headView.findViewById(R.id.progressbar);
        mTvRefreshAct = (TextView) headView.findViewById(R.id.tv_refresh_action);
        mTvRefreshStatus = (TextView) headView.findViewById(R.id.tv_refresh_status);

        setOrientation(VERTICAL);

        //动态加入headView
        addView(headView, 0);

    }

    public PullToRefreshListView(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed && !loadOnce) {
            //通过设置marginTop为负值隐藏掉headView
            hideHeaderHeight = -headView.getHeight();
            headerParams = (MarginLayoutParams) headView.getLayoutParams();
            headerParams.topMargin = hideHeaderHeight;
            headView.setLayoutParams(headerParams);
            listView = (ListView) getChildAt(1);
            listView.setOnTouchListener(this);
            loadOnce = true;
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float distance;
        float Ymove;
        isAbleToPull(event);
        if (isAbleToPULL) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Ydown = event.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    Ymove = event.getRawY();
                    distance = Ymove - Ydown;
                    if (distance < 10) {
                        return false;
                    }
                    if (currentStatus != STATUS_REFRESHING) {
                        if (headerParams.topMargin > 0) {
                            currentStatus = STATUS_RELEASE_TO_REFRESH;
                        } else {
                            currentStatus = STATUS_PULL_TO_REFRESH;
                        }
                        headerParams.topMargin = (int) distance / 2 + hideHeaderHeight;
                        headView.setLayoutParams(headerParams);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                default:
                    if (currentStatus == STATUS_RELEASE_TO_REFRESH) {
                        Refresh();
                    } else if (currentStatus == STATUS_PULL_TO_REFRESH) {
                        HideHeadView();
                    }
                    break;
            }

            if (currentStatus == STATUS_RELEASE_TO_REFRESH || currentStatus == STATUS_PULL_TO_REFRESH) {
                UpdateHeaderView();
                listView.setPressed(false);
                listView.setFocusable(false);
                listView.setFocusableInTouchMode(false);
                lastStatus = currentStatus;
            }
        }
        return false;
    }

    public void Refresh() {
        currentStatus = STATUS_REFRESHING;
        UpdateHeaderView();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                listView.setSelection(0);
                headerParams.topMargin = hideHeaderHeight;
                headView.setLayoutParams(headerParams);
                currentStatus = STATUS_REFRESH_FINISHED;
            }
        }, 5000);
    }

    public void HideHeadView() {
        headerParams.topMargin = hideHeaderHeight;
        headView.setLayoutParams(headerParams);
        currentStatus = STATUS_REFRESH_FINISHED;
    }


    public void UpdateHeaderView() {
        if (currentStatus == STATUS_PULL_TO_REFRESH) {
            mTvRefreshAct.setText("下拉刷新");
            progressBar.setVisibility(GONE);
        } else if (currentStatus == STATUS_RELEASE_TO_REFRESH) {
            mTvRefreshAct.setText("释放立即刷新");
            progressBar.setVisibility(GONE);
        } else {
            mTvRefreshAct.setText("正在刷新");
            progressBar.setVisibility(VISIBLE);
        }
    }

    public void isAbleToPull(MotionEvent event) {
        if (headerParams.topMargin < hideHeaderHeight) {
            isAbleToPULL = false;
        } else {
            isAbleToPULL = true;
        }
    }


}

