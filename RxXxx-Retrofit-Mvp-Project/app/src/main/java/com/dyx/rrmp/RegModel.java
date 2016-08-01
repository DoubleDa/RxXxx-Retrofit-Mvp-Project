package com.dyx.rrmp;

/**
 * project name：RxXxx-Retrofit-Mvp-Project
 * class describe：
 * create person：dayongxin
 * create time：16/8/1 下午10:38
 * alter person：dayongxin
 * alter time：16/8/1 下午10:38
 * alter remark：
 */
public class RegModel {

    /**
     * access_token : 43498e707578af63fe07el5fde7a59fe
     * id : 1762
     * refresh_token : 4767bf6710b7l26bb96b7caca5c974f5c43d24094835273db2d149c7
     * status : true
     */

    private String access_token;
    private int id;
    private String refresh_token;
    private boolean status;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
