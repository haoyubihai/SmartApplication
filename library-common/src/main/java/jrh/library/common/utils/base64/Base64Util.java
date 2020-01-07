package jrh.library.common.utils.base64;


import android.util.Base64;

public class Base64Util {
    // 加密
    public static String decode(String str) {
        String s = new String(Base64.decode(str, Base64.DEFAULT));
        return s;
    }

}
