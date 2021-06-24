//package com.example.ap2_3.view_model;
//
//import androidx.databinding.*;
//import com.example.ap2_3.BR;
//
//public class FlightConnection extends BaseObservable {
//    private String IP;
//    private int port;
//
//    public FlightConnection() {
//        IP = "127.0.0.1";
//        port = 8000;
//    }
//
//    public void setIP(String IP) {
//        if (IP != this.IP) {
//            this.IP = IP;
//            notifyPropertyChanged(BR.conn);
//        }
//    }
//
//    @Bindable
//    public String getIP() {
//        System.out.println("GET IP");
//        return this.IP;
//    }
//
//    public void setPort(String port) {
//        int val;
//        try {
//            val = Integer.parseInt(port);
//        } catch (Exception e) {
//            return;
//        }
//        if (val != this.port) {
//            System.out.println("notifying port has changed from " + this.port + " to " + val + ".");
//            this.port = val;
//            notifyPropertyChanged(BR.conn);
//        }
//    }
//
//    @Bindable
//    public String getPort() {
//        System.out.println("GET PORT");
//        return String.valueOf(port);
//    }
//}
