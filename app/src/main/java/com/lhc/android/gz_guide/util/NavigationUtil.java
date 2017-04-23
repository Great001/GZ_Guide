package com.lhc.android.gz_guide.util;

import android.content.Context;
import android.content.Intent;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.poi.PoiResult;
import com.lhc.android.gz_guide.activity.AboutAppActivity;
import com.lhc.android.gz_guide.activity.AboutTastyActivity;
import com.lhc.android.gz_guide.activity.AboutHotelActivity;
import com.lhc.android.gz_guide.activity.AboutLocalGuiderActivity;
import com.lhc.android.gz_guide.activity.AboutLocalPartnerActivity;
import com.lhc.android.gz_guide.activity.AboutSpotActivity;
import com.lhc.android.gz_guide.activity.AboutStrategyActivity;
import com.lhc.android.gz_guide.activity.AboutTrafficActivity;
import com.lhc.android.gz_guide.activity.BMapActivity;
import com.lhc.android.gz_guide.activity.EditUserInfoActivity;
import com.lhc.android.gz_guide.activity.ForgetPasswordActivity;
import com.lhc.android.gz_guide.activity.GeneralSettingActivity;
import com.lhc.android.gz_guide.activity.HotelDetailActivity;
import com.lhc.android.gz_guide.activity.LocalGuideDetailActivity;
import com.lhc.android.gz_guide.activity.LocateResultActivity;
import com.lhc.android.gz_guide.activity.LoginActivity;
import com.lhc.android.gz_guide.activity.MainActivity;
import com.lhc.android.gz_guide.activity.PersonalDetailActivity;
import com.lhc.android.gz_guide.activity.PrivicySettingActivity;
import com.lhc.android.gz_guide.activity.RegisterActivity;
import com.lhc.android.gz_guide.activity.SearchResultActivity;
import com.lhc.android.gz_guide.activity.SecureSettingActivity;
import com.lhc.android.gz_guide.activity.SpotDetailActivity;
import com.lhc.android.gz_guide.activity.StrageryDetailActivity;
import com.lhc.android.gz_guide.activity.TastyDetailActivity;
import com.lhc.android.gz_guide.activity.UserDiscoverActivity;
import com.lhc.android.gz_guide.activity.UserFriendActivity;
import com.lhc.android.gz_guide.activity.UserInfoActivity;
import com.lhc.android.gz_guide.activity.UserReportActivity;
import com.lhc.android.gz_guide.activity.UserSettingActivity;
import com.lhc.android.gz_guide.activity.UserStrageryActivity;
import com.lhc.android.gz_guide.activity.WebViewActivity;
import com.lhc.android.gz_guide.model.RecommendAd;

/**
 * Created by Administrator on 2017/3/20.
 */
public class NavigationUtil {

    public static void navigateToMainActivity(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToLoginActivity(Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToForgetPswActivity(Context context){
        Intent intent = new Intent(context, ForgetPasswordActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToRegisterActivity(Context context){
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToSpotDetailActivity(Context context, RecommendAd ad){
        Intent intent = new Intent(context, SpotDetailActivity.class);
        intent.putExtra(SpotDetailActivity.KEY_AD_DATA,ad);
        context.startActivity(intent);
    }

    public static void navigateToAboutHotelActivity(Context context){
        Intent intent = new Intent(context, AboutHotelActivity.class);
        context.startActivity(intent);
    }


    public static void navigateToAboutTastyActivity(Context context){
        Intent intent = new Intent(context, AboutTastyActivity.class);
        context.startActivity(intent);
    }


    public static void navigateToAboutSpotActivity(Context context){
        Intent intent = new Intent(context, AboutSpotActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToAboutMapActivity(Context context){
        Intent intent = new Intent(context, BMapActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToAboutStrategyActivity(Context  context){
        Intent intent = new Intent(context, AboutStrategyActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToAboutLocalGuiderActivity(Context context){
        Intent intent = new Intent(context, AboutLocalGuiderActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToAboutLocalPartnerActivity(Context context){
        Intent intent = new Intent(context, AboutLocalPartnerActivity.class);
        context.startActivity(intent);
    }


    public static void navigateToAboutTrafficActivity(Context context){
        Intent intent = new Intent(context, AboutTrafficActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToHotelDetailActivity(Context context){
        Intent intent = new Intent(context, HotelDetailActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToTastyDetailActivity(Context context){
        Intent intent = new Intent(context, TastyDetailActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToStrageryDetailActivity(Context context){
        Intent intent = new Intent(context, StrageryDetailActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToLocalGuideDetailActivity(Context context){
        Intent intent = new Intent(context, LocalGuideDetailActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToPersonalDetailActivity(Context context){
        Intent intent = new Intent(context, PersonalDetailActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToSearchResultActivity(Context context, PoiResult result){
        Intent intent = new Intent(context, SearchResultActivity.class);
        intent.putExtra(SearchResultActivity.POI_RESULT,result);
        context.startActivity(intent);
    }

    public static void navigateToBmapActivity(Context context, LatLng locatioin,String address){
        Intent intent = new Intent(context, BMapActivity.class);
        intent.putExtra(BMapActivity.LOCATION,locatioin);
        intent.putExtra(BMapActivity.ADDRESS,address);
        context.startActivity(intent);
    }

    public static void navigateToLocationResultActivity(Context context){
        Intent intent = new Intent(context, LocateResultActivity.class);
        context.startActivity(intent);
    }


    public static void navigateToUserFriendActivity(Context context){
        Intent intent = new Intent(context, UserFriendActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToUserStrageryActivity(Context context){
        Intent intent = new Intent (context, UserStrageryActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToUserDiscoverActivity(Context context){
        Intent intent = new Intent(context, UserDiscoverActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToUserSettingActivity(Context context){
        Intent intent = new Intent(context, UserSettingActivity.class);
        context.startActivity(intent);
    }

    public static void navagateToUserInfoActivity(Context context){
        Intent intent = new Intent(context, UserInfoActivity.class);
        context.startActivity(intent);
    }


    public static void navigateToEditUserInfoActivity(Context context,String editItem){
        Intent intent = new Intent(context, EditUserInfoActivity.class);
        intent.putExtra(EditUserInfoActivity.EDIT_ITEM,editItem);
        context.startActivity(intent);
    }


    public static void navigateToWebViewActivity(Context context,String link){
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(WebViewActivity.CONTENT_LINK,link);
        context.startActivity(intent);
    }

    public static void navigateToWebViewActivity(Context context,String link,String title){
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(WebViewActivity.CONTENT_LINK,link);
        intent.putExtra(WebViewActivity.TITLE,title);
        context.startActivity(intent);
    }


    public static void navigateToGenaralSetttingActivity(Context context){
        Intent intent = new Intent(context, GeneralSettingActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToPrivicySettingActivity(Context context){
        Intent intent = new Intent(context, PrivicySettingActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToSecureSettingActivity(Context context){
        Intent intent = new Intent(context, SecureSettingActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToAboutAppActivity(Context  context){
        Intent intent = new Intent(context, AboutAppActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToUserReportActivity(Context context){
        Intent intent = new Intent(context, UserReportActivity.class);
        context.startActivity(intent);
    }
}
