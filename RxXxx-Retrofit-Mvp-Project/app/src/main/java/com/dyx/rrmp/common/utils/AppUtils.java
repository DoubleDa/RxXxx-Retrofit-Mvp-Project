package com.dyx.rrmp.common.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.dyx.rrmp.common.app.XAppConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Properties;
import java.util.UUID;

/**
 * project name：RxXxx-Retrofit-Mvp-Project
 * class describe：
 * create person：dayongxin
 * create time：16/8/2 下午3:13
 * alter person：dayongxin
 * alter time：16/8/2 下午3:13
 * alter remark：
 */
public class AppUtils {
    private Context mContext;
    /**
     * Pagination Size
     */
    public static final int PAGE_SIZE = 12;

    /**
     * Cache Timeout
     */
    private static final int CACHE_TIME_OUT = 60 * 60 * 1000;
    /**
     * Memory Cache
     */
    private Hashtable<String, Object> memoryCacheRegion = new Hashtable<String, Object>();

    /**
     * Whether AudioManager Normal
     *
     * @return
     */
    public static boolean isAudioNormal(Context context) {
        AudioManager mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        return mAudioManager.getRingerMode() == AudioManager.RINGER_MODE_NORMAL;
    }

    /**
     * Whether App Sound
     *
     * @return
     */
    public boolean isAppSound(Context context) {
        return isAudioNormal(context) && isVoice();
    }

    /**
     * Whether Voice
     *
     * @return
     */
    private boolean isVoice() {
        String perf_voice = getProperty(XAppConfig.CONF_VOICE);
        if (StringUtils.isEmpty(perf_voice)) {
            return true;
        } else {
            return StringUtils.toBool(perf_voice);
        }
    }

    /**
     * Whether Network Connected
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }

    /**
     * Whether Method Compat
     *
     * @param VersionCode
     * @return
     */
    public static boolean isMethodsCompat(int VersionCode) {
        int currentVersion = android.os.Build.VERSION.SDK_INT;
        return currentVersion >= VersionCode;
    }

    /**
     * Get Package Info
     *
     * @param context
     * @return
     */
    public PackageInfo getPackageInfo(Context context) {
        PackageInfo info = null;
        try {
            info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
        if (info == null)
            info = new PackageInfo();
        return info;
    }

    /**
     * Get App Id
     *
     * @return
     */
    public String getAppId() {
        String uniqueID = getProperty(XAppConfig.CONF_APP_UNIQUEID);
        if (StringUtils.isEmpty(uniqueID)) {
            uniqueID = UUID.randomUUID().toString();
            setProperty(XAppConfig.CONF_APP_UNIQUEID, uniqueID);
        }
        return uniqueID;
    }

    /**
     * Whether Load Image
     *
     * @return
     */
    public boolean isLoadImage() {
        String perf_loadimage = getProperty(XAppConfig.CONF_LOAD_IMAGE);
        if (StringUtils.isEmpty(perf_loadimage))
            return true;
        else
            return StringUtils.toBool(perf_loadimage);
    }

    /**
     * Config Is Load Image
     *
     * @param b
     */
    public void configIsLoadimage(boolean b) {
        setProperty(XAppConfig.CONF_LOAD_IMAGE, String.valueOf(b));
    }


    /**
     * Config Is Voice
     *
     * @param b
     */
    public void configIsVoice(boolean b) {
        setProperty(XAppConfig.CONF_VOICE, String.valueOf(b));
    }


    /**
     * Whether Scroll Left And Right
     *
     * @return
     */
    public boolean isScroll() {
        String perf_scroll = getProperty(XAppConfig.CONF_SCROLL);
        if (StringUtils.isEmpty(perf_scroll))
            return false;
        else
            return StringUtils.toBool(perf_scroll);
    }


    /**
     * Config Is Scroll
     *
     * @param b
     */
    public void configIsScroll(boolean b) {
        setProperty(XAppConfig.CONF_SCROLL, String.valueOf(b));
    }


    /**
     * Whether Https Login
     *
     * @return
     */
    public boolean isHttpsLogin() {
        String perf_httpslogin = getProperty(XAppConfig.CONF_HTTPS_LOGIN);
        if (StringUtils.isEmpty(perf_httpslogin))
            return false;
        else
            return StringUtils.toBool(perf_httpslogin);
    }


    /**
     * Config Https Login
     *
     * @param b
     */
    public void configIsHttpsLogin(boolean b) {
        setProperty(XAppConfig.CONF_HTTPS_LOGIN, String.valueOf(b));
    }

    /**
     * Clear Cookie
     */
    public void clearCookie() {
        removeProperty(XAppConfig.CONF_COOKIE);
    }

    /**
     * Whether Read Data Cache
     *
     * @param cacheFile
     * @return
     */
    private boolean isReadDataCache(String cacheFile) {
        return readObject(cacheFile) != null;
    }

    /**
     * Whether Exist Data Cache
     *
     * @param cacheFile
     * @return
     */
    private boolean isExistDataCache(String cacheFile) {
        boolean exist = false;
        File data = getFileStreamPath(cacheFile);
        if (data.exists())
            exist = true;
        return exist;
    }

    /**
     * Whether Cache Data Timeout
     *
     * @param cachefile
     * @return
     */
    public boolean isCacheDataFailure(String cachefile) {
        boolean failure = false;
        File data = getFileStreamPath(cachefile);
        if (data.exists()
                && (System.currentTimeMillis() - data.lastModified()) > CACHE_TIME_OUT)
            failure = true;
        else if (!data.exists())
            failure = true;
        return failure;
    }

    /**
     * Clear App Cache
     */
    public void clearAppCache() {
        //TODO
        //1.cache
        //2.database
        //3.cache file
        //4.Properties
    }

    /**
     * Clear Cache Folder
     *
     * @param dir
     * @param curTime
     * @return
     */
    private int clearCacheFolder(File dir, long curTime) {
        int deletedFiles = 0;
        if (dir != null && dir.isDirectory()) {
            try {
                for (File child : dir.listFiles()) {
                    if (child.isDirectory()) {
                        deletedFiles += clearCacheFolder(child, curTime);
                    }
                    if (child.lastModified() < curTime) {
                        if (child.delete()) {
                            deletedFiles++;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return deletedFiles;
    }

    /**
     * Set Memory Cache Object
     *
     * @param key
     * @param value
     */
    public void setMemoryCache(String key, Object value) {
        memoryCacheRegion.put(key, value);
    }

    /**
     * Get Memory Cache Object
     *
     * @param key
     * @return
     */
    public Object getMemCache(String key) {
        return memoryCacheRegion.get(key);
    }

    /**
     * Save Disk Cache
     *
     * @param key
     * @param value
     * @throws IOException
     */
    public void setDiskCache(String key, String value) throws IOException {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("cache_" + key + ".data", Context.MODE_PRIVATE);
            fos.write(value.getBytes());
            fos.flush();
        } finally {
            CloseUtils.closeQuietly(fos);
        }
    }

    /**
     * Get Disk Cache
     *
     * @param key
     * @return
     * @throws IOException
     */
    public String getDiskCache(String key) throws IOException {
        FileInputStream fis = null;
        try {
            fis = openFileInput("cache_" + key + ".data");
            byte[] datas = new byte[fis.available()];
            fis.read(datas);
            return new String(datas);
        } finally {
            CloseUtils.closeQuietly(fis);
        }
    }

    /**
     * Save Object
     *
     * @param ser
     * @param file
     * @return
     */
    public boolean saveObject(Serializable ser, String file) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = openFileOutput(file, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(ser);
            oos.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            CloseUtils.closeQuietly(fos);
            CloseUtils.closeQuietly(oos);
        }
    }

    /**
     * Read Object By Key
     *
     * @param file
     * @return
     */
    public Serializable readObject(String file) {
        if (!isExistDataCache(file))
            return null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = openFileInput(file);
            ois = new ObjectInputStream(fis);
            return (Serializable) ois.readObject();
        } catch (FileNotFoundException e) {
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof InvalidClassException) {
                File data = getFileStreamPath(file);
                data.delete();
            }
        } finally {
            CloseUtils.closeQuietly(fis);
            CloseUtils.closeQuietly(ois);
        }
        return null;
    }

    private FileInputStream openFileInput(String s) {
        return null;
    }

    private FileOutputStream openFileOutput(String s, int modePrivate) {
        return null;
    }

    private File getFileStreamPath(String cachefile) {
        return null;
    }


    public boolean containsProperty(String key) {
        Properties props = getProperties();
        return props.containsKey(key);
    }

    public void setProperties(Properties ps) {
        XAppConfig.getInstance(mContext).set(ps);
    }

    public Properties getProperties() {
        return XAppConfig.getInstance(mContext).get();
    }

    public void setProperty(String key, String value) {
        XAppConfig.getInstance(mContext).set(key, value);
    }

    public String getProperty(String key) {
        return XAppConfig.getInstance(mContext).get(key);
    }

    public void removeProperty(String... key) {
        XAppConfig.getInstance(mContext).remove(key);
    }
}
