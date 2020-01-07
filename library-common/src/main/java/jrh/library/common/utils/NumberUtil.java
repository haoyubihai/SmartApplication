package jrh.library.common.utils;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jiarh
 * @date 16/11/3
 */
public class NumberUtil {

    public static final Pattern p = Pattern.compile("[0-9]+(\\.[0-9]+)?");
    private NumberUtil() {
    }

    /**
     * 判断一个String是否为数字
     */
    public static boolean isNumber(String str) {
        if (StringUtil.isBlank(str)) {
            return false;
        }
        try {

            Matcher m = p.matcher(str);
            if (m.matches()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 是否为小数/整数，max为小数点后面最多几位
     */
    public static boolean isDecimal(String str, int max) {
        if (StringUtil.isBlank(str)) {
            return false;
        }
        try {
            Pattern p = Pattern.compile("([1-9]\\d*|0)(\\.\\d{1," + max + "})?");
            Matcher m = p.matcher(str);
            if (m.matches()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断一个String是否为int数据
     */
    public static boolean isInteger(String cs) {
        if (StringUtil.isBlank(cs)) {
            return false;
        }
        final int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isDigit(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 把字符串转为int，如果不是数字，则返回-1
     */
    public static int parseInt(String str) {
        return parseInt(str, -1);
    }

    public static int parseInt(String str, int fallback) {
        if (isInteger(str)) {
            return Integer.parseInt(str);
        }
        return fallback;
    }

    /**
     * 把字符串转为long，如果不是数字，则返回-1
     */
    public static long parseLong(String str) {
        return parseLong(str, -1L);
    }

    public static long parseLong(String str, long fallback) {
        if (isInteger(str)) {
            return Long.parseLong(str);
        }
        return fallback;
    }

    /**
     * 把字符串转为float，如果不是数字，则返回-1
     */
    public static float parseFloat(String str) {
        return parseFloat(str, -1);
    }

    public static float parseFloat(String str, float fallback) {
        if (isNumber(str)) {
            return Float.parseFloat(str);
        }
        return fallback;
    }

    /**
     * 把字符串转为double，如果不是数字，则返回-1
     */
    public static double parseDouble(String str) {
        return parseDouble(str, -1);
    }

    public static double parseDouble(String str, double fallback) {
        if (isNumber(str)) {
            return Double.parseDouble(str);
        }
        return fallback;
    }

    /**
     * 去除小数点后多余的0
     */
    public static String stripZeros(double num) {
        return BigDecimal.valueOf(num).stripTrailingZeros().toPlainString();
    }

    /**
     * 截取小数点后两位
     */
    public static String subDecimal(String value) {
        return subDecimal(value, 2);
    }

    public static String subDecimal(String value, int num) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(num, BigDecimal.ROUND_HALF_UP);
        return bd.toString();
    }
}
