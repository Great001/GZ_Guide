package com.lhc.android.gz_guide.util;

import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/3/22.
 */
public class ValidChecker {

    public final static int INVALID_ACCOUNT = 0;
    public final static int PHONE_NUMBER = 1;
    public final static int EMAIL = 2;
    public final static int USERNAME = 3;

    public static int  checkAccount(String account){
        if(checkPhoneNumber(account)){
            return PHONE_NUMBER;
        }
        if(checkEmail(account)){
            return EMAIL;
        }
//        if(checkUserName(account)){
           return USERNAME;
//        }
//        return INVALID_ACCOUNT;
    }


    public static boolean checkPassword(String password){
        int len = password.length();
        if(len<6 || len>16){
            return false;
        }else {
            return true;
        }
    }

    public static boolean checkPhoneNumber(String phoneNumber){
        String phoneRegex = "^1[3458][0-9]{9}$";
        Pattern pattern = Pattern.compile(phoneRegex);
        return pattern.matcher(phoneNumber).matches();
    }

    public static boolean checkEmail(String email){
        String emailRegex = "^([a-z0-9A-Z]+[-|+|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+)(\\.[a-zA-Z]{2,})(\\.[a-zA-Z]{2,})?$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }


    public static  boolean checkUserName(String userName){
        String nameRegex = "^[a-zA-z]([a-z0-9A-z]*_?)*$";
        Pattern pattern = Pattern.compile(nameRegex);
        return pattern.matcher(userName).matches();
    }


}
