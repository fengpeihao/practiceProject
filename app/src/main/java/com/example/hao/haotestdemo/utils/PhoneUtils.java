package com.example.hao.haotestdemo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Hao on 2016/12/12.
 */

public class PhoneUtils {
    public static boolean isPhone(String phone){
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(phone);
        return m.matches();
    }
}
