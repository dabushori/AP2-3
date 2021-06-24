package com.example.ap2_3.model;

public interface Model {
    public void disconnect();
    public void setAileron(float val);
    public void setElevator(float val);
    public void setRudder(float val);
    public void setThrottle(float val);
}
