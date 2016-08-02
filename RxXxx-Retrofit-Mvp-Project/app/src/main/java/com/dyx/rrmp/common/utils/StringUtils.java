package com.dyx.rrmp.common.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * project name：RxXxx-Retrofit-Mvp-Project
 * class describe：String Handler Class
 * create person：dayongxin
 * create time：16/8/2 下午2:15
 * alter person：dayongxin
 * alter time：16/8/2 下午2:15
 * alter remark：
 */
public class StringUtils {
    /**
     * Email Pattern
     */
    private final static Pattern emailer = Pattern
            .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

    /**
     * Date Pattern
     */
    private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    /**
     * Date Pattern
     */
    private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    /**
     * String To JSONObject
     *
     * @param json
     * @return
     * @throws JSONException
     */
    public static JSONObject toJSONObject(String json) throws JSONException {
        if (!isEmpty(json)) {
            if (json.indexOf("[") == 0) {
                json = json.substring(1, json.length());
            }
            if (json.lastIndexOf("]") == json.length()) {
                json = json.substring(0, json.length() - 1);
            }
            return new JSONObject(json);
        }
        return null;
    }

    /**
     * String To JSONArray
     *
     * @param json
     * @return
     * @throws JSONException
     */
    public static JSONArray toJSONArray(String json) throws JSONException {
        return new JSONArray(json);
    }

    /**
     * String To Date
     *
     * @param sdate
     * @return
     */
    public static Date toDate(String sdate) {
        try {
            return dateFormater.get().parse(sdate);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * TimeStamp To Date
     *
     * @param timestampString
     * @return
     */
    public static String timeStampToDate(String timestampString) {
        Long timestamp = Long.parseLong(timestampString) * 1000;
        return dateFormater.get().format(new java.util.Date(timestamp));
    }

    /**
     * Show Distance Friendly
     *
     * @param distance
     * @return
     */
    public static String friendlyDistance(double distance) {
        String dis = "";
        if (distance >= 1000) {
            if (distance / 1000 >= 1000) {
                dis = ">1000km";
            } else if (distance / 1000 >= 100) {
                dis = ">100km";
            } else {
                dis = String.format("%.1f", (distance / 1000)) + "km";
            }
        } else {
            dis = distance + "m";
        }
        return dis;
    }

    /**
     * Get Friendly Time
     *
     * @param sdate
     * @return
     */
    public static String friendlyTime(String sdate) {
        //TODO 中文字符的处理
        Date time = toDate(sdate);
        if (time == null) {
            return "Unknown";
        }
        String ftime = "";
        Calendar cal = Calendar.getInstance();

        String curDate = dateFormater2.get().format(cal.getTime());
        String paramDate = dateFormater2.get().format(time);
        if (curDate.equals(paramDate)) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0)
                ftime = Math.max(
                        (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                        + "分钟前";
            else
                ftime = hour + "小时前";
            return ftime;
        }

        long lt = time.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 0) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0)
                ftime = Math.max(
                        (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                        + "分钟前";
            else
                ftime = hour + "小时前";
        } else if (days == 1) {
            ftime = "昨天";
        } else if (days == 2) {
            ftime = "前天";
        } else if (days > 2 && days <= 10) {
            ftime = days + "天前";
        } else if (days > 10) {
            ftime = dateFormater2.get().format(time);
        }
        return ftime;
    }

    /**
     * Whether Today
     *
     * @param sdate
     * @return
     */
    public static boolean isToday(String sdate) {
        boolean b = false;
        Date time = toDate(sdate);
        Date today = new Date();
        if (time != null) {
            String nowDate = dateFormater2.get().format(today);
            String timeDate = dateFormater2.get().format(time);
            if (nowDate.equals(timeDate)) {
                b = true;
            }
        }
        return b;
    }

    /**
     * String Whether Is Empty
     *
     * @param input
     * @return
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * String Whether Is Email
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (email == null || email.trim().length() == 0)
            return false;
        return emailer.matcher(email).matches();
    }

    /**
     * String To Int
     *
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    /**
     * Object To Int
     *
     * @param obj
     * @return
     */
    public static int toInt(Object obj) {
        if (obj == null)
            return 0;
        return toInt(obj.toString(), 0);
    }

    /**
     * String To Long
     *
     * @param obj
     * @return
     */
    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * String To Boolean
     *
     * @param b
     * @return
     */
    public static boolean toBool(String b) {
        try {
            return Boolean.parseBoolean(b);
        } catch (Exception e) {
        }
        return false;
    }
}
