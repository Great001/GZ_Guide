package com.lhc.android.gz_guide.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.fragment.MainPageFragment;
import com.lhc.android.gz_guide.fragment.UserProfileFragment;
import com.lhc.android.gz_guide.fragment.RecommendFragment;
import com.lhc.android.gz_guide.model.UserModel;
import com.lhc.android.gz_guide.util.NavigationUtil;
import com.lhc.android.gz_guide.util.ToastUtil;
import com.lhc.android.gz_guide.view.SearchActionBar;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private SearchActionBar searchActionBar;
    private RadioGroup rgBottomTabs;

    private FragmentManager mFm;
    private long exitTime;

    private boolean isUserLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mFm = getSupportFragmentManager();
        exitTime = 0;
        initView();
        autoLogin();   //自动登录
    }

    public void initView() {
        viewPager = (ViewPager) findViewById(R.id.vp_tabs);
        rgBottomTabs = (RadioGroup) findViewById(R.id.rg_tabs);
        searchActionBar = (SearchActionBar) findViewById(R.id.search_actionbar);

        viewPager.setAdapter(new FragmentAdapter(mFm));
        viewPager.setCurrentItem(0);
        showActionBar();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                int checkId = R.id.rbtn_tab_one;
                switch (position) {
                    case 0:
                        checkId = R.id.rbtn_tab_one;
                        break;
                    case 1:
                        checkId = R.id.rbtn_tab_two;
                        break;
                    case 2:
                        checkId = R.id.rbtn_tab_three;
                        break;
                    default:
                        break;
                }
                rgBottomTabs.check(checkId);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        rgBottomTabs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbtn_tab_one:
                        viewPager.setCurrentItem(0);
                        showActionBar();
                        break;
                    case R.id.rbtn_tab_two:
                        viewPager.setCurrentItem(1);
                        hideActionBar();
                        break;
                    case R.id.rbtn_tab_three:
                        viewPager.setCurrentItem(2);
                        hideActionBar();
                        break;
                    default:
                        break;
                }
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    static class FragmentAdapter extends FragmentPagerAdapter {

        FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new MainPageFragment();
                    break;
                case 1:
                    fragment = new RecommendFragment();
                    break;
                case 2:
                    fragment = new UserProfileFragment();
                    break;
                default:
                    break;

            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    @Override
    public void onBackPressed() {
        if (!canPopBack()) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                ToastUtil.show(this, R.string.exit_remind);
                exitTime = System.currentTimeMillis();
            } else {
                System.exit(0);
            }
        }
    }


    public boolean canPopBack() {
        int count = mFm.getBackStackEntryCount();
        if (count > 1) {
            mFm.popBackStack();
            return true;
        } else {
            return false;
        }
    }


    public void showActionBar() {
        searchActionBar.show();
    }

    public void hideActionBar() {
        searchActionBar.hide();
    }


    //实现自动登录
    public void autoLogin() {
        SharedPreferences sp = getSharedPreferences(UserModel.SP_USER_PROFILE, MODE_PRIVATE);
        String account = sp.getString("username", "null");
        final String password = sp.getString("password", "null");

        if (account.equals("null") || password.equals("null")) {
            NavigationUtil.navigateToLoginActivity(this);
            return;
        }

        UserModel.login(this, account, password, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                ToastUtil.show(MainActivity.this,"登录成功");
                UserModel.setLoginState(MainActivity.this,true);
                UserModel.saveUserProfile(MainActivity.this,jsonObject);
                isUserLogin = true;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
    }

    public boolean getLoginState(){
        return isUserLogin;
    }

    public void setLoginState(boolean loginState){
        isUserLogin = loginState;
    }


}



