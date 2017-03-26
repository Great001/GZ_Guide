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
import android.widget.ListView;
import android.widget.ScrollView;

import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.activity.MainActivity;
import com.lhc.android.gz_guide.adapter.OptionsGvAdapter;
import com.lhc.android.gz_guide.adapter.PicsPagerAdapter;
import com.lhc.android.gz_guide.adapter.RecommendGoodsAdapter;
import com.lhc.android.gz_guide.model.RecommendGood;
import com.lhc.android.gz_guide.util.DimensionUtil;
import com.lhc.android.gz_guide.util.NavigationUtil;

import java.security.DigestException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainPageFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final int UPDATE_VIEWPAGER = 1;

    private ViewPager viewPager;
    private ListView listView;
    private GridView gvOptions;
    private ScrollView scrollView;

    private PicsPagerAdapter pagerAdapter;
    private RecommendGoodsAdapter goodsAdapter;


    private Handler handler;
    private int currentItem;

    private int images[] = {R.drawable.sxj, R.drawable.gz_night, R.drawable.cjc, R.drawable.sxj, R.drawable.gz_night};
    private int optionIcons[] = {R.drawable.hotel, R.drawable.eat, R.drawable.play, R.drawable.traffic, R.drawable.stratery, R.drawable.map, R.drawable.guide, R.drawable.partner};
    private int optionTexts[] = {R.string.hotel, R.string.eat, R.string.play, R.string.traffic, R.string.stratery, R.string.map, R.string.get_guide, R.string.get_partner};
    private List<RecommendGood> goods = new ArrayList<>();


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Runnable vpUpdateTask = new Runnable() {
        @Override
        public void run() {
            currentItem = viewPager.getCurrentItem();
            currentItem++;
            handler.sendEmptyMessage(UPDATE_VIEWPAGER);
        }
    };

    public MainPageFragment() {
        // Required empty public constructor
    }


    public static MainPageFragment newInstance(String param1, String param2) {
        MainPageFragment fragment = new MainPageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case UPDATE_VIEWPAGER:
                        viewPager.setCurrentItem(currentItem % images.length);
                        break;
                    default:
                        break;
                }
                super.handleMessage(msg);
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_main_page, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.vp_pics);
        listView = (ListView) view.findViewById(R.id.lv_list);
        gvOptions = (GridView) view.findViewById(R.id.gv_all_options);
        scrollView = (ScrollView)view.findViewById(R.id.sv_main_page);
        setUpScrollView();
        setUpViewPager();
        setUpGridView();
        setUpListView();
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        autoScroll();
    }


    public void setUpViewPager(){
        final int picsCount = images.length;
        pagerAdapter = new PicsPagerAdapter(this.getActivity(), images);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(1);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

                if (position == 0) {
                    viewPager.setCurrentItem(picsCount - 2);
                }

                if (position == picsCount - 1) {
                    viewPager.setCurrentItem(1);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setUpListView(){
        initGoodData();
        goodsAdapter = new RecommendGoodsAdapter(getActivity(), goods);
        listView.setAdapter(goodsAdapter);
        initListViewHeight();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NavigationUtil.navigateToSpotDetailActivity(getActivity(),position);
            }
        });
    }

    public void setUpGridView(){
        gvOptions.setAdapter(new OptionsGvAdapter(getActivity(), optionIcons, optionTexts));
        gvOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Context context= getActivity();
                switch(position){
                    case 0:
                        NavigationUtil.navigateToAboutHotelActivity(context);
                        break;
                    case 1:
                        NavigationUtil.navigateToAboutEatActivity(context);
                        break;
                    case 2:
                        NavigationUtil.navigateToAboutSpotActivity(context);
                        break;
                    case 3:
                        NavigationUtil.navigateToAboutTrafficActivity(context);
                        break;
                    case 4:
                        NavigationUtil.navigateToAboutStrageryActivity(context);
                        break;
                    case 5:
                        NavigationUtil.navigateToAboutMapActivity(context);
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


    public void setUpScrollView(){
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                int limit = DimensionUtil.dp2px(getActivity(),150);
               switch (action){
                   case MotionEvent.ACTION_UP:
                       if(scrollView.getScrollY() < limit){
                           ((MainActivity)getActivity()).showActionBar();
                       }else{
                           ((MainActivity)getActivity()).hideActionBar();
                       }
                       break;
                   default:
                       break;
               }
                    return false;
            }
        });
    }



    //实现轮播效果
    public void autoScroll() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleWithFixedDelay(vpUpdateTask, 8000, 8000, TimeUnit.MILLISECONDS);
    }

    public void initGoodData() {
        RecommendGood good1 = new RecommendGood("广州塔是个好地方，广州塔塔身168米–334.4米处设有“蜘蛛侠栈道”，是世界最高最长的空中漫步云梯[7]  。塔身422.8米处设有旋转餐厅，是世界最高的旋转餐厅[8]  。塔身顶部450~454米处设有摩天轮，是世界最高摩天轮[9] ", R.drawable.gz, "", true, 3, "广州塔");
        RecommendGood good2 = new RecommendGood("陈家祠是个好地方，陈氏书院，俗称陈家祠，位于广州市中山七路。陈家祠是广东现存祠堂中最富有广东特色的艺术建筑群，布局严整，装饰精巧，富丽堂皇，是全国文物重点保护单位。", R.drawable.chenjiaci, "", false, 3, "陈家祠");
        for (int i = 0; i < 5; i++) {
            goods.add(good1);
            goods.add(good2);
        }
    }

    public void initListViewHeight() {
        int itemCount = goodsAdapter.getCount();
        int totalHeight = 0;
        for (int i = 0; i < itemCount; i++) {
            View itemView = goodsAdapter.getView(i, null, listView);
            itemView.measure(0, 0);
            totalHeight += itemView.getMeasuredHeight();
        }
        totalHeight += listView.getDividerHeight() * (itemCount - 1);
//        int height = itemSum * DimensionUtil.dp2px(getActivity(),170);
        listView.getLayoutParams().height = totalHeight;

    }


}
