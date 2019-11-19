package com.example.snd;


public class AddWifiModel {
    private String wifi;
    private String wifiDevice;
    private String rightArrow;

    public AddWifiModel(String wifi, String wifiDevice, String rightArrow) {
        this.wifi = wifi;
        this.wifiDevice = wifiDevice;
        this.rightArrow = rightArrow;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public String getWifiDevice() {
        return wifiDevice;
    }

    public void setWifiDevice(String wifiDevice) {
        this.wifiDevice = wifiDevice;
    }

    public String getRightArrow() {
        return rightArrow;
    }

    public void setRightArrow(String rightArrow) {
        this.rightArrow = rightArrow;
    }

}
