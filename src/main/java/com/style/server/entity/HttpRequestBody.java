package com.style.server.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * jinyalin
 * on 2017/4/25.
 */
public class HttpRequestBody {
    public static final List<String> VALID_FACET_ID_LIST = new ArrayList<>(
            Arrays.asList("eY2qu+zyliX8ptVNGmVW81e+tzU=", "0C73mPNvXJ5tL99+xMM62U0Ii5I=",
                    "lAGA4yVl9Tth8P92Wqe5OTPZq2Y="));

    private DeviceInfo deviceInfo;
    private String facetId;

    public HttpRequestBody(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    public String getFacetId() {
        return facetId;
    }

    public boolean isValidBody() {
        return facetId != null && VALID_FACET_ID_LIST.contains(facetId.trim());
    }

    @Override
    public String toString() {
        return "DeviceInfo: [" + " type: " + deviceInfo.getType()
                + ", sdkVersion: " + deviceInfo.getSdkVersion()
                + ", androidId: " + deviceInfo.getAndroidId()
                + ", manufacturer: " + deviceInfo.getManufacturer()
                + ", model: " + deviceInfo.getModel()
                + ", versionName: " + deviceInfo.getVersionName()
                + ", versionCode: " + deviceInfo.getVersionCode();
    }
}
