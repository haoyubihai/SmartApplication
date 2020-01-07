package jrh.library.common.utils;

import java.util.Map;

/**
 * desc:
 * Created by jiarh on 2018/8/9 14:38.
 */

public class MapUtil
{

    public static boolean isNotEmpty(final Map<?,?> map) {
        return !isEmpty(map);
    }

    public static boolean isEmpty(final Map<?,?> map) {
        return map == null || map.isEmpty();
    }
}
