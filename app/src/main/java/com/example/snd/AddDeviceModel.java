package com.example.snd;

public class AddDeviceModel {
    private String wifi;
    private String deviceName;
    private String pairButton;

    public AddDeviceModel(String wifi, String deviceName, String pairButton) {
        this.wifi = wifi;
        this.deviceName = deviceName;
        this.pairButton = pairButton;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getPairButton() {
        return pairButton;
    }

    public void setPairButton(String pairButton) {
        this.pairButton = pairButton;
    }
}
