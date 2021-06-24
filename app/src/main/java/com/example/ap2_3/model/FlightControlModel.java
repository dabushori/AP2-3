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

    private static Thread currThread;

    public FlightControlModel(String ip, int port) {
        if (currThread != null && currThread.isAlive()) {
            currThread.interrupt();
        }
        this.connected = true;
        this.changes = new LinkedBlockingQueue<Data>();
//        System.out.println("connecting to ip " + ip + " and port " + port);
        currThread = new Thread(() -> {
            try {
                Socket fg = new Socket(ip, port);
                PrintWriter out = new PrintWriter(fg.getOutputStream(), true);
                while (connected) {
                    try {
                        Data data = changes.take();
//                        System.out.print(data.type + data.value + "\r\n");
                        out.print(data.type + data.value + "\r\n");
                        out.flush();
                    } catch (Exception e) {
                        System.out.println("sending failed");
                    }
                }
                out.close();
                fg.close();
            } catch (Exception e) {
                System.out.println("connection failed");
            }
        });
        currThread.start();
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
            changes.add(new Data("set /controls/flight/rudder ", val));
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
