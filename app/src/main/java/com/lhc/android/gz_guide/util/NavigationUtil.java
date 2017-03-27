package com.lhc.android.gz_guide.util;

import android.content.Context;
import android.content.Intent;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.poi.PoiResult;
import com.lhc.android.gz_guide.activity.AboutEatActivity;
import com.lhc.android.gz_guide.activity.AboutHotelActivity;
import com.lhc.android.gz_guide.activity.AboutLocalGuiderActivity;
import com.lhc.android.gz_guide.activity.AboutLocalPartnerActivity;
import com.lhc.android.gz_guide.activity.AboutMapActivity;
import com.lhc.android.gz_guide.activity.AboutSpotActivity;
import com.lhc.android.gz_guide.activity.AboutStrageryActivity;
import com.lhc.android.gz_guide.activity.AboutTrafficActivity;
import com.lhc.android.gz_guide.activity.BMapActivity;
import com.lhc.android.gz_guide.activity.ForgetPasswordActivity;
import com.lhc.android.gz_guide.activity.HotelDetailActivity;
import com.lhc.android.gz_guide.activity.LocalGuideDetailActivity;
import com.lhc.android.gz_guide.activity.LocateResultActivity;
import com.lhc.android.gz_guide.activity.LoginActivity;
import com.lhc.android.gz_guide.activity.MainActivity;
import com.lhc.android.gz_guide.activity.PersonalDetailActivity;
import com.lhc.android.gz_guide.activity.RegisterActivity;
import com.lhc.android.gz_guide.activity.SearchResultActivity;
import com.lhc.android.gz_guide.activity.SpotDetailActivity;
import com.lhc.android.gz_guide.activity.StrageryDetailActivity;
import com.lhc.android.gz_guide.activity.TastyDetailActivity;

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

    public static void navigateToSpotDetailActivity(Context context,int spotId){
        Intent intent = new Intent(context, SpotDetailActivity.class);
        intent.putExtra(SpotDetailActivity.SPOT_ID,spotId);
        context.startActivity(intent);
    }

    public static void navigateToAboutHotelActivity(Context context){
        Intent intent = new Intent(context, AboutHotelActivity.class);
        context.startActivity(intent);
    }


    public static void navigateToAboutEatActivity(Context context){
        Intent intent = new Intent(context, AboutEatActivity.class);
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

    public static void navigateToAboutStrageryActivity(Context  context){
        Intent intent = new Intent(context, AboutStrageryActivity.class);
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








}
