package com.example.ap2_3.model;

import java.net.*;
import java.io.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FlightControlModel implements Model {
    public static class Data {
        public String type;
        public float value;

        public Data(String type, float value) {
            this.type = type;
            this.value = value;
        }
    }

    private boolean connected;
    private BlockingQueue<Data> changes;

    public FlightControlModel(String ip, int port) {
        Socket fg;
        PrintWriter out;
        this.connected = true;
        this.changes = new LinkedBlockingQueue<Data>();
        try {
            fg = new Socket(ip,port);
            out = new PrintWriter(fg.getOutputStream(),true);
            if (out != null && fg != null) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(connected) {
                            try {
                                Data data = changes.remove();
                                out.print(data.type + data.value + "\r\n");
                                out.flush();
                            }   catch (Exception e) {

                            }
                        }
                    }
                }).start();
            }
            out.close();
            fg.close();
        } catch (Exception e) {
            System.out.println("connection failed");
        }
    }

    public void disconnect() {
        connected = false;
    }

    @Override
    public void setAileron(float val) {
        try {
            changes.add(new Data("set /controls/flight/aileron ", val));
        } catch (Exception e) {
            System.out.println("Could not set Aileron" + val);
        }
    }

    @Override
    public void setElevator(float val) {
        try {
            changes.add(new Data("set /controls/flight/elevator ", val));
        } catch (Exception e) {
            System.out.println("Could not set Elevator" + val);
        }
    }

    @Override
    public void setRudder(float val) {
        try {
            changes.add(new Data("set /controls/flight/rudder ",val));
        } catch (Exception e) {
            System.out.println("Could not set Rudder" + val);
        }
    }

    @Override
    public void setThrottle(float val) {
        try {
            changes.add(new Data("set /controls/engines/current-engine/throttle ", val));
        } catch (Exception e) {
            System.out.println("Could not set Throttle" + val);
        }
    }
}
