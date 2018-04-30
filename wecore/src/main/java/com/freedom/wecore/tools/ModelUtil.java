package com.freedom.wecore.tools;

import android.os.Build;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

/**
 * @author vurtne on 29-Apr-18.
 * 判断手机是否是小米 或者魅族的工具类
 */

public class ModelUtil {


    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";

    /** 是否是小米 */
    public static boolean isMIUI() {
        try {
            final BuildProperties prop = BuildProperties.newInstance();
            return prop.getProperty(KEY_MIUI_VERSION_CODE, null) != null ||
                    prop.getProperty(KEY_MIUI_VERSION_NAME, null) != null
                    || prop.getProperty(KEY_MIUI_INTERNAL_STORAGE, null) != null;
        } catch (final IOException e) {
            return false;
        }
    }

    public static boolean isMIUI6(){
        try {
            final BuildProperties prop = BuildProperties.newInstance();
            String verStr = prop.getProperty(KEY_MIUI_VERSION_NAME, null);
            if (verStr == null){
                return false;
            }
            int version = Integer.parseInt(verStr.substring(1,2));
            return version >= 6;
        } catch (final IOException e) {
            return false;
        }
    }

    public static boolean isMIUI9(){
        try {
            final BuildProperties prop = BuildProperties.newInstance();
            String verStr = prop.getProperty(KEY_MIUI_VERSION_NAME, null);
            if (verStr == null){
                return false;
            }
            int version = Integer.parseInt(verStr.substring(1,2));
            return version >= 9;
        } catch (final IOException e) {
            return false;
        }
    }

    /** 是否是魅族 */
    public static boolean isFlyme() {
        try {
            final Method method = Build.class.getMethod("hasSmartBar");
            return method != null;
        } catch (final Exception e) {
            return false;
        }
    }

    private static class BuildProperties {
        private final Properties properties;

        private BuildProperties() throws IOException {
            properties = new Properties();
            properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
        }

        private boolean containsKey(final Object key) {
            return properties.containsKey(key);
        }

        private boolean containsValue(final Object value) {
            return properties.containsValue(value);
        }

        private Set<Entry<Object, Object>> entrySet() {
            return properties.entrySet();
        }

        private String getProperty(final String name) {
            return properties.getProperty(name);
        }

        private String getProperty(final String name, final String defaultValue) {
            return properties.getProperty(name, defaultValue);
        }

        private boolean isEmpty() {
            return properties.isEmpty();
        }

        private Enumeration<Object> keys() {
            return properties.keys();
        }

        private Set<Object> keySet() {
            return properties.keySet();
        }

        private int size() {
            return properties.size();
        }

        private Collection<Object> values() {
            return properties.values();
        }

        public static BuildProperties newInstance() throws IOException {
            return new BuildProperties();
        }
    }
}
