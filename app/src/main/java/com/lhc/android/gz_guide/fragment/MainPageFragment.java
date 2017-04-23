package com.lhc.android.gz_guide.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import com.baidu.mapapi.model.LatLng;
import com.lhc.android.gz_guide.Interface.OnGetGzHistoryListener;
import com.lhc.android.gz_guide.Interface.OnGetPagerAdDataListener;
import com.lhc.android.gz_guide.Interface.OnGetRecommendGoodListener;
import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.activity.MainActivity;
import com.lhc.android.gz_guide.adapter.GzHistroyAdapter;
import com.lhc.android.gz_guide.adapter.OptionsGvAdapter;
import com.lhc.android.gz_guide.adapter.PagerAdAdapter;
import com.lhc.android.gz_guide.adapter.RecommendGoodAdapter;
import com.lhc.android.gz_guide.model.GzHistory;
import com.lhc.android.gz_guide.model.HistoryModel;
import com.lhc.android.gz_guide.model.Location;
import com.lhc.android.gz_guide.model.RecommendGood;
import com.lhc.android.gz_guide.model.RecommendModel;
import com.lhc.android.gz_guide.model.RecommendAd;
import com.lhc.android.gz_guide.util.DimensionUtil;
import com.lhc.android.gz_guide.util.NavigationUtil;
import com.lhc.android.gz_guide.view.MyScrollView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainPageFragment extends Fragment implements OnGetGzHistoryListener,
        OnGetPagerAdDataListener {

    public static final int UPDATE_VIEWPAGER = 1000;

    private ViewPager mVpAds;
    private ListView mLvHistory;
    private GridView mGvOptions;
    private MyScrollView mSvMainPage;

    private PagerAdAdapter pagerAdapter;
    private GzHistroyAdapter gzHistoryAdapter;

    private int optionIcons[] = {R.drawable.hotel, R.drawable.tasty, R.drawable.fun, R.drawable.traffic, R.drawable.strategy, R.drawable.map, R.drawable.guide, R.drawable.partner};
    private int optionTexts[] = {R.string.hotel, R.string.tasty, R.string.fun, R.string.traffic, R.string.stratery, R.string.map, R.string.get_guide, R.string.get_partner};

    private List<RecommendAd> pagerAds = new ArrayList<>();
    private List<GzHistory> histories = new ArrayList<>();

    private int currentItem;

    private ScheduledExecutorService executorService;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_VIEWPAGER:
                    mVpAds.setCurrentItem(currentItem % pagerAds.size());
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    //实现轮播图效果
    private Runnable vpUpdateTask = new Runnable() {
        @Override
        public void run() {
            currentItem = mVpAds.getCurrentItem();
            currentItem++;
            handler.sendEmptyMessage(UPDATE_VIEWPAGER);
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_main_page, container, false);
        mVpAds = (ViewPager) view.findViewById(R.id.vp_ads);
        mLvHistory = (ListView) view.findViewById(R.id.lv_gz_history);
        mGvOptions = (GridView) view.findViewById(R.id.gv_all_options);
        mSvMainPage = (MyScrollView) view.findViewById(R.id.sv_main_page);

        setUpScrollView();
        setUpViewPager();
        setUpGridView();
        setUpListView();


        HistoryModel.getInstance().setOnGetGzHistoryListener(this);
        RecommendModel.getInstance().setOnGetPagerDataListener(this);
        RecommendModel.getInstance().getRecommendPagerData(getContext());
        HistoryModel.getInstance().getGzHistory(getContext());

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
//        autoScroll();    //开启轮播
    }


    @Override
    public void onStop() {
        stopScroll();   //停止轮播，释放线程池资源
        super.onStop();
    }

    public void setUpViewPager() {
        final int adCount = pagerAds.size();
        pagerAdapter = new PagerAdAdapter(getContext());
        mVpAds.setAdapter(pagerAdapter);

        mVpAds.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    mVpAds.setCurrentItem(adCount - 2);
                }
                if (position == adCount - 1) {
                    mVpAds.setCurrentItem(1);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setUpListView() {
        gzHistoryAdapter = new GzHistroyAdapter(getContext());
        mLvHistory.setAdapter(gzHistoryAdapter);
        mLvHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String contentLink = histories.get(position).getContentLink();
                String title = histories.get(position).getTitle();
                NavigationUtil.navigateToWebViewActivity(getContext(), contentLink, title);
            }
        });
    }

    public void setUpGridView() {
        mGvOptions.setAdapter(new OptionsGvAdapter(getContext(), optionIcons, optionTexts));
        mGvOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Context context = getContext();
                switch (position) {
                    case 0:
                        NavigationUtil.navigateToAboutHotelActivity(context);
                        break;
                    case 1:
                        NavigationUtil.navigateToAboutTastyActivity(context);
                        break;
                    case 2:
                        NavigationUtil.navigateToAboutSpotActivity(context);
                        break;
                    case 3:
                        NavigationUtil.navigateToAboutTrafficActivity(context);
                        break;
                    case 4:
                        NavigationUtil.navigateToAboutStrategyActivity(context);
                        break;
                    case 5:
                        LatLng location = Location.newInstance(getActivity().getApplicationContext()).getLocation();
                        String address = Location.newInstance(getActivity().getApplicationContext()).getLocalAddress();
                        NavigationUtil.navigateToBmapActivity(context, location, address);
                        break;
                    case 6:
                        NavigationUtil.navigateToAboutLocalGuiderActivity(context);
                        break;
                    case 7:
                        NavigationUtil.navigateToAboutLocalPartnerActivity(context);
                        break;
                    default:
                        break;
                }
            }
        });
    }


    public void setUpScrollView() {
        //监听ScrollView实现自定义的SearchActionBar的显隐
        final int limitHeight = DimensionUtil.dp2px(getContext(), 150);
        mSvMainPage.addMyScrollViewChangeListener(new MyScrollView.OnMyScrollViewChangeListener() {
            @Override
            public void onScrollChanged(int left, int top, int oldl, int oldt) {
                if (top < limitHeight) {
                    ((MainActivity) getActivity()).showActionBar();
                } else {
                    ((MainActivity) getActivity()).hideActionBar();
                }
            }
        });
//        mSvMainPage.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                int action = event.getAction();
//                int limit = DimensionUtil.dp2px(getActivity(), 150);
//                switch (action) {
//                    case MotionEvent.ACTION_DOWN:
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        break;
//                    case MotionEvent.ACTION_UP:
//                    case MotionEvent.ACTION_CANCEL:
//                    case MotionEvent.ACTION_SCROLL:
//                    default:
//                        if (mSvMainPage.getScrollY() < limit) {
//                            ((MainActivity) getActivity()).showActionBar();
//                        } else {
//                            ((MainActivity) getActivity()).hideActionBar();
//                        }
//                        break;
//                }
//                return false;
//            }
//        });
    }


    //实现轮播效果
    public void autoScroll() {
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleWithFixedDelay(vpUpdateTask, 5, 5, TimeUnit.SECONDS);
    }

    public void stopScroll() {
        if (executorService != null) {
            executorService.shutdown();
        }
    }


    //广州历史数据回调
    @Override
    public void onGetHistories(List<GzHistory> historiyList) {
        if (historiyList != null) {
            this.histories = historiyList;
            gzHistoryAdapter.setData(histories);
            gzHistoryAdapter.notifyDataSetChanged();
            initListViewHeight();
        }
    }


    //轮播图数据回调
    @Override
    public void onGetPagerAdData(List<RecommendAd> datas) {
        if (datas != null) {
            pagerAds = datas;
            pagerAds.add(0, pagerAds.get(pagerAds.size() - 1));
            pagerAds.add(pagerAds.get(1));
            pagerAdapter.setData(pagerAds);
            pagerAdapter.notifyDataSetChanged();
            mVpAds.setCurrentItem(1);
            autoScroll();  //开启轮播
        }
    }


    //解决ScrollView嵌套ListView的问题
    public void initListViewHeight() {
        int itemCount = gzHistoryAdapter.getCount();
        int totalHeight = 0;
        for (int i = 0; i < itemCount; i++) {
            View itemView = gzHistoryAdapter.getView(i, null, mLvHistory);
            itemView.measure(0, 0);
            totalHeight += itemView.getMeasuredHeight();
        }
        totalHeight += mLvHistory.getDividerHeight() * (itemCount - 1);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mLvHistory.getLayoutParams();
        params.height = totalHeight;
        mLvHistory.setLayoutParams(params);
    }


}
