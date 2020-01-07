package jrh.library.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtil {

    private StringUtil() {}

    /**
     * 判断是否为空串（会对字符串进行trim()）
     *
     * @param str 需要判断的 字符串
     */
    public static boolean isBlank(String str) {
        return str == null || "".equals(str.trim());
    }

    /**
     * 判断是否为空串（会对字符串进行trim()）
     *
     * @param str 需要判断的 字符串
     * @return true不为空；false为""或者null
     */
    public static boolean isNotBlank(String str) {
        return str != null && !"".equals(str.trim());
    }

    /**
     * 判断是否为空串（不会对字符串进行trim()）
     *
     * @param str 需要判断的 字符串
     * @return true为""或者null
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    /**
     * 判断是否为空串（不会对字符串进行trim()）
     *
     * @param str 需要判断的 字符串
     * @return true不为空；false为""或者null
     */
    public static boolean isNotEmpty(String str) {
        return str != null && !"".equals(str);
    }

    public static boolean isAllBlank(String... str) {
        return checkBlank(true, str);
    }

    public static boolean isAllNotBlank(String... str) {
        return !checkBlank(true, str);
    }

    public static boolean isAllEmpty(String... str) {
        return checkBlank(false, str);
    }

    public static boolean isAllNotEmpty(String... str) {
        return !checkBlank(false, str);
    }

    private static boolean checkBlank(boolean trim, String... str) {
        if (str == null || str.length == 0) {
            return true;
        }
        for (String s : str) {
            if (s == null || "".equals(trim ? s.trim() : s)) {
                return true;
            }
        }
        return false;
    }

    public static boolean equals(CharSequence a, CharSequence b) {
        if (a == b) return true;
        int length;
        if (a != null && b != null && (length = a.length()) == b.length()) {
            if (a instanceof String && b instanceof String) {
                return a.equals(b);
            } else {
                for (int i = 0; i < length; i++) {
                    if (a.charAt(i) != b.charAt(i)) return false;
                }
                return true;
            }
        }
        return false;
    }

    public static boolean startWith(String str, String prefix) {
        return !(str == null || prefix == null) && str.startsWith(prefix);
    }

    public static boolean endsWith(String str, String suffix) {
        return !(str == null || suffix == null) && str.endsWith(suffix);
    }

    public static String join(Object[] arr, String separator) {
        if (arr != null && isNotBlank(separator)) {
            StringBuilder sb = new StringBuilder();
            for (Object obj : arr) {
                if (obj != null) {
                    sb.append(separator);
                    sb.append(obj);
                }
            }
            if (sb.length() > 0) {
                return sb.substring(separator.length());
            }
        }
        return "";
    }

    public static String join(int[] arr, String separator) {
        if (arr != null && isNotBlank(separator)) {
            StringBuilder sb = new StringBuilder();
            for (int num : arr) {
                sb.append(separator);
                sb.append(num);
            }
            if (sb.length() > 0) {
                return sb.substring(separator.length());
            }
        }
        return "";
    }

    public static String join(Collection<?> list, String separator) {
        if (list != null && !list.isEmpty() && isNotBlank(separator)) {
            StringBuilder sb = new StringBuilder();
            for (Object obj : list) {
                if (obj != null) {
                    sb.append(separator);
                    sb.append(obj);
                }
            }
            if (sb.length() > 0) {
                return sb.substring(separator.length());
            }
        }
        return "";
    }


    public static String decode(String str) {
        try {
            str = str.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
            str = str.replaceAll("\\+", "%2B");
            return URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String encode(String str) {
        try {
            if (isNotBlank(str)) {
                return URLEncoder.encode(str, "utf-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 字符串转换成十六进制字符串
     *
     * @param String str 待转换的ASCII字符串
     * @return String 每个Byte之间空格分隔，如: [61 6C 6B]
     */
    public static String str2HexStr(String str) {

        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;

        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
        }
        return sb.toString().trim();
    }

    /**
     * 十进制字符串转换成十六进制字符串
     *
     * @param String decimalStr 待转换的十进制字符串
     * @return String 十六进制字符串
     */
    public static String decimalStr2HexStr(String decimalStr) {
        String hexStr = "";
        try{
            long num = Long.parseLong(decimalStr);
            hexStr = Long.toHexString(num);

        }catch (Exception e){

        }finally {
            return hexStr;
        }
    }

    /***
     *
     * @return String 中间四位****的手机号
     */
    public static String showPhone(String phone){
        if (StringUtil.isEmpty(phone)){
            return "";
        }
        String pattern = "(\\d{3})\\d{4}(\\d{4})";
        String result = phone.replaceAll(pattern, "$1****$2");
        return result ;
    }

    /**
     * 过滤表情符号
     * @create by ranxianghui
     * @param str
     * @return str(去掉表情符号的字符串)
     * */
    public static  String filterEmoji(String str) {
        if (str.trim().isEmpty()) {
            return str;
        }
        String pattern = "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]";
        String reStr = "";
        Pattern emoji = Pattern.compile(pattern);
        Matcher emojiMatcher = emoji.matcher(str);
        str = emojiMatcher.replaceAll(reStr);
        return str;
    }

    public static boolean isURL(String url) {
        return url.startsWith("http");
    }
}
