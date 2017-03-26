package com.lhc.android.gz_guide.util;

import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/3/22.
 */
public class ValidChecker {

    public final static int VALID = 0;
    public final static int PHONE_NUMBER_INVALID = 1;
    public final static int EMAIL_INVALID = 2;

    public static int  checkAccount(String account){
        int checkResult = -1;
        if(!checkPhoneNumber(account)){
            checkResult = PHONE_NUMBER_INVALID;
        }else if(!checkEmail(account)){
            checkResult = EMAIL_INVALID;
        }else{
            checkResult = VALID;
        }
        return checkResult;
    }


    public static boolean checkPassword(String password){
        int len = password.length();
        if(len<8 || len>16){
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



}
