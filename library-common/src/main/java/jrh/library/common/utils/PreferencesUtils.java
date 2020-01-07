package jrh.library.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;
import java.util.Set;

import jrh.library.common.app.AppConfig;

public class PreferencesUtils {

    /**
     * SharedPreferences--针对单个用户，退出登录时会清空
     */
    public static final String SP_NAME = "jrjy_user_config";
    /**
     * SharedPreferences--针对所有用户
     */
    public static final String SP_CONFIG = "jrjy_app_config";


    public static boolean putString(String key, String value) {
        return putString(SP_NAME, AppConfig.getContext(), key, value);
    }

    public static boolean putString(Context context, String key, String value) {
        return putString(SP_NAME, context, key, value);
    }

    public static boolean putString(String name, Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public static String getString(String key) {
        return getString(AppConfig.getContext(), key, null);
    }

    public static String getString(Context context, String key) {
        return getString(context, key, null);
    }

    public static String getString(Context context, String key, String defaultValue) {
        return getString(SP_NAME, context, key, defaultValue);
    }

    public static String getString(String name, Context context, String key, String defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return settings.getString(key, defaultValue);
    }

    public static void putStringSet(String key, Set<String> set) {
       putStringSet(AppConfig.getContext(),key,set);
    }
    public static void putStringSet(Context context, String key, Set<String> set) {
        SharedPreferences settings = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putStringSet(key, set);
        editor.apply();
    }

    public static Set<String> getStringSet( String key) {
        return getStringSet(AppConfig.getContext(),key);
    }
    public static Set<String> getStringSet(Context context, String key) {
        return context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).getStringSet(key, null);
    }


    public static void putStringSet(String name, Context context, String key, Set<String> set) {
        SharedPreferences settings = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putStringSet(key, set);
        editor.apply();
    }

    public static Set<String> getStringSet(String name, Context context, String key) {
        return context.getSharedPreferences(name, Context.MODE_PRIVATE).getStringSet(key, null);
    }

    public static boolean putInt(Context context, String key, int value) {
        SharedPreferences settings = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        return editor.commit();
    }
    public static boolean putInt(String key, int value) {
        return putInt(AppConfig.getContext(),key,value);
    }
    public static int getInt(Context context, String key) {
        return getInt(context, key, -1);
    }
    public static int getInt(String key) {
        return getInt(AppConfig.getContext(), key, -1);
    }

    public static int getInt(Context context, String key, int defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return settings.getInt(key, defaultValue);
    }
    public static int getInt(String key, int defaultValue) {
        return getInt(AppConfig.getContext(),key, defaultValue);
    }

    public static boolean putLong( String key, long value) {
        return putLong(AppConfig.getContext(),key,value);
    }

    public static boolean putLong(Context context, String key, long value) {
        SharedPreferences settings = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    public static long getLong( String key) {
        return getLong(AppConfig.getContext(), key, -1);
    }
    public static long getLong(Context context, String key) {
        return getLong(context, key, -1);
    }

    public static long getLong(String key, long defaultValue) {
        return getLong(AppConfig.getContext(),key,defaultValue);
    }

    public static long getLong(Context context, String key, long defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return settings.getLong(key, defaultValue);
    }

    public static boolean putFloat(String key, float value) {

        return putFloat(AppConfig.getContext(),key,value);
    }
    public static boolean putFloat(Context context, String key, float value) {
        SharedPreferences settings = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    public static float getFloat(String key) {
        return getFloat(AppConfig.getContext(), key);
    }

    public static float getFloat(Context context, String key) {
        return getFloat(context, key, -1);
    }

    public static float getFloat(String key, float defaultValue) {
        return getFloat(AppConfig.getContext(),key,defaultValue);
    }
    public static float getFloat(Context context, String key, float defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return settings.getFloat(key, defaultValue);
    }


    public static boolean putBoolean( String key, boolean value) {

        return putBoolean(AppConfig.getContext(), key,value);
    }
    public static boolean putBoolean(Context context, String key, boolean value) {
        SharedPreferences settings = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    public static boolean getBoolean(String key) {
        return getBoolean(AppConfig.getContext(), key, false);
    }
    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, false);
    }

    public static boolean getBoolean( String key, boolean defaultValue) {
        return getBoolean(AppConfig.getContext(),key,defaultValue);
    }
    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return settings.getBoolean(key, defaultValue);
    }



    public static boolean putBoolean(String name, Context context, String key, boolean value) {
        SharedPreferences settings = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    public static boolean getBoolean(String name, Context context, String key) {
        return getBoolean(name, context, key, true);
    }

    public static boolean getBoolean(String name, Context context, String key, boolean defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return settings.getBoolean(key, defaultValue);
    }

    public static boolean putInt(String name, Context context, String key, int value) {
        SharedPreferences settings = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    public static int getInt(String name, Context context, String key) {
        return getInt(name, context, key, -1);
    }

    public static int getInt(String name, Context context, String key, int defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return settings.getInt(key, defaultValue);
    }

    public static boolean putMap(Context context, Map<String, Object> map) {
        SharedPreferences preferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object object = entry.getValue();
            if (object instanceof Boolean) {
                editor.putBoolean(key, (Boolean) object);
            }
            if (object instanceof Float) {
                editor.putFloat(key, (Float) object);
            }
            if (object instanceof Integer) {
                editor.putInt(key, (Integer) object);
            }
            if (object instanceof Long) {
                editor.putLong(key, (Long) object);
            }
            if (object instanceof String) {
                editor.putString(key, (String) object);
            }

        }
        return editor.commit();
    }

    public static boolean clearUserConfig() {
        return clear(SP_NAME);
    }

    public static boolean clearAppConfig() {
        return clear(SP_CONFIG);
    }

    public static boolean clear(String spName) {
        return AppConfig.getContext().getSharedPreferences(spName, Activity.MODE_PRIVATE).edit().clear().commit();
    }

}
