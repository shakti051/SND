package com.example.snd;

public class AllDevice {

    private String device;
    private String deviceName;
    private String roomType;
    private String unpair;
    private String push;
    private String symbolType;

    public AllDevice(String device, String deviceName, String roomType, String unpair, String push, String symbolType) {
        this.device = device;
        this.deviceName = deviceName;
        this.roomType = roomType;
        this.unpair = unpair;
        this.push = push;
        this.symbolType = symbolType;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getUnpair() {
        return unpair;
    }

    public void setUnpair(String unpair) {
        this.unpair = unpair;
    }

    public String getPush() {
        return push;
    }

    public void setPush(String push) {
        this.push = push;
    }

    public String getSymbolType() {
        return symbolType;
    }

    public void setSymbolType(String symbolType) {
        this.symbolType = symbolType;
    }
}
