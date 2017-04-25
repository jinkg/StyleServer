package com.style.server.entity;

/**
 * jinyalin
 * on 2017/4/25.
 */
public class HttpRequestBody {
    private DeviceInfo deviceInfo;

    public HttpRequestBody(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    @Override
    public String toString() {
        return "DeviceInfo: [" + " type: " + deviceInfo.getType()
                + ", sdkVersion: " + deviceInfo.getSdkVersion()
                + ", androidId: " + deviceInfo.getAndroidId()
                + ", manufacturer: " + deviceInfo.getManufacturer()
                + ", model: " + deviceInfo.getModel();
    }
}
