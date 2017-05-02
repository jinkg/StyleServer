package com.style.server.entity;

/**
 * jinyalin
 * on 2017/4/25.
 */
public class DeviceInfo {

    private int sdkVersion;
    private String androidId;
    private String manufacturer;
    private String model;
    private String type;
    private String versionName;
    private int versionCode;

    public int getSdkVersion() {
        return sdkVersion;
    }

    public String getAndroidId() {
        return androidId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVersionName() {
        return versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }
}
