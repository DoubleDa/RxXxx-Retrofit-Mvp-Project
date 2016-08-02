package com.dyx.rrmp.common.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.dyx.rrmp.common.utils.CloseUtils;
import com.dyx.rrmp.common.utils.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * project name：RxXxx-Retrofit-Mvp-Project
 * class describe：App Config Class
 * create person：dayongxin
 * create time：16/8/2 下午1:46
 * alter person：dayongxin
 * alter time：16/8/2 下午1:46
 * alter remark：
 */
public class XAppConfig {
    /**
     * Dialog Whether Finish
     */
    public static boolean isDialogFinish = true;
    private static final String APP_CONFIG = "app_config";

    /**
     * Temp
     */
    public final static String TEMP_TWEET = "temp_tweet";
    public final static String TEMP_TWEET_IMAGE = "temp_tweet_image";
    public final static String TEMP_MESSAGE = "temp_message";
    public final static String TEMP_COMMENT = "temp_comment";
    public final static String TEMP_POST_TITLE = "temp_post_title";
    public final static String TEMP_POST_CATALOG = "temp_post_catalog";
    public final static String TEMP_POST_CONTENT = "temp_post_content";

    /**
     * Config
     */
    public final static String CONF_APP_UNIQUEID = "app_uniqueid";
    public final static String CONF_COOKIE = "cookie";
    public final static String CONF_SESSION = "session";
    public final static String CONF_ACCESSTOKEN = "access_token";
    public final static String CONF_ACCESSSECRET = "access_secret";
    public final static String CONF_EXPIRESIN = "expires_in";
    public final static String CONF_LOAD_IMAGE = "perf_loadimage";
    public final static String CONF_SCROLL = "perf_scroll";
    public final static String CONF_HTTPS_LOGIN = "perf_httpslogin";
    public final static String CONF_VOICE = "perf_voice";

    private Context mContext;
    private static XAppConfig instance;

    /**
     * Private Constructor
     */
    private XAppConfig() {
    }

    /**
     * Get XAppConfig Instance
     *
     * @return
     */
    public static XAppConfig getInstance(Context context) {
        if (instance == null) {
            instance = new XAppConfig();
            instance.mContext = context;
        }
        return instance;
    }

    /**
     * Get SharedPreferences Setting
     *
     * @param context
     * @return
     */
    public static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Get Whether
     *
     * @param context
     * @return
     */
    public static boolean isLoadImage(Context context) {
        return getSharedPreferences(context).getBoolean(CONF_LOAD_IMAGE, true);
    }

    /**
     * Get Cookie
     *
     * @return
     */
    public String getCookie() {
        return get(CONF_COOKIE);
    }

    /**
     * Get Session
     *
     * @return
     */
    public String getSession() {
        return get(CONF_SESSION);
    }

    /**
     * Set AccessToken
     *
     * @param accessToken
     */
    public void setAccessToken(String accessToken) {
        set(CONF_ACCESSTOKEN, accessToken);
    }

    /**
     * Get Access Token
     *
     * @return
     */
    public String getAccessToken() {
        return get(CONF_ACCESSTOKEN);
    }

    /**
     * Set Access Secret
     *
     * @param accessSecret
     */
    public void setAccessSecret(String accessSecret) {
        set(CONF_ACCESSSECRET, accessSecret);
    }

    /**
     * Get Access Secret
     *
     * @return
     */
    public String getAccessSecret() {
        return get(CONF_ACCESSSECRET);
    }

    /**
     * Set Expires In
     *
     * @param expiresIn
     */
    public void setExpiresIn(long expiresIn) {
        set(CONF_EXPIRESIN, String.valueOf(expiresIn));
    }

    /**
     * Get Expires In
     *
     * @return
     */
    public long getExpiresIn() {
        return StringUtils.toLong(get(CONF_EXPIRESIN));
    }


    /**
     * Get Result By Key
     *
     * @param key
     * @return
     */
    public String get(String key) {
        Properties properties = get();
        return (properties != null) ? properties.getProperty(key) : null;
    }

    /**
     * Get Properties
     *
     * @return
     */
    public Properties get() {
        FileInputStream fis = null;
        Properties properties = new Properties();
        try {
            File dirFile = mContext.getDir(APP_CONFIG, Context.MODE_PRIVATE);
            fis = new FileInputStream(dirFile.getPath() + File.separator + APP_CONFIG);
            properties.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseUtils.closeQuietly(fis);
        }
        return properties;
    }


    /**
     * Set Properties
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        Properties properties = get();
        properties.setProperty(key, value);
        setProps(properties);
    }

    /**
     * Set Properties
     *
     * @param properties
     */
    public void setProps(Properties properties) {
        FileOutputStream fos = null;
        File dirFile = mContext.getDir(APP_CONFIG, Context.MODE_PRIVATE);
        File confFile = new File(dirFile, APP_CONFIG);
        try {
            fos = new FileOutputStream(confFile);
            properties.store(fos, null);
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseUtils.closeQuietly(fos);
        }
    }

    /**
     * Remove Properties By Key
     *
     * @param key
     */
    public void remove(String... key) {
        Properties properties = get();
        for (String k : key) {
            properties.remove(k);
        }
        setProps(properties);
    }

    /**
     * Set Properties
     *
     * @param props
     */
    public void set(Properties props) {
        Properties properties = get();
        properties.putAll(props);
        setProps(properties);
    }
}
