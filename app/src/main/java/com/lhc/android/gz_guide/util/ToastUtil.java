package com.lhc.android.gz_guide.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/3/22.
 */
public class ToastUtil {

    public static void show(Context context, String str){
        Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
    }

    public static void show(Context context,int resId){
        Toast.makeText(context,resId,Toast.LENGTH_SHORT).show();
    }


}
