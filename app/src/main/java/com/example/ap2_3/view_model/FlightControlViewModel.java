package com.example.ap2_3.view_model;

import android.widget.SeekBar;
import androidx.databinding.*;
import com.example.ap2_3.BR;
import com.example.ap2_3.model.FlightControlModel;
import com.example.ap2_3.model.Model;
import com.example.ap2_3.views.Joystick;

public class FlightControlViewModel extends BaseObservable {
    Model model;
    private float x;
    private float y;
    private int rudder;
    private int throttle;

    private String ip;
    private int port;

    public FlightControlViewModel() {
        // starting values
        throttle = 1000;
        rudder = 500;
    }

    public FlightControlViewModel(Model m) {
        model = m;
    }

    public void connect() {
        if (model != null) {
            model.disconnect();
        }
        model = new FlightControlModel(ip, port);
    }

    public void setIp(String ip) {
        if (ip != this.ip) {
            this.ip = ip;
            notifyPropertyChanged(BR.vm);
        }
    }

    @Bindable
    public String getIp() {
        return this.ip;
    }

    public void setPort(String port) {
        int val;
        try {
            val = Integer.parseInt(port);
        } catch (Exception e) {
            return;
        }
        if (val != this.port) {
            this.port = val;
            notifyPropertyChanged(BR.vm);
        }
    }

    @Bindable
    public String getPort() {
        if (port == 0) {
            return "";
        }
        return String.valueOf(port);
    }

    public void setX(float aileron) {
        try {
//            this.aileron = Float.parseFloat(aileron);
            this.x = aileron;
            notifyPropertyChanged(BR.x);
            System.out.println("aileron set to " + this.x);
            model.setAileron(this.x);

        } catch (Exception e) {
            return;
        }
    }

    public void setY(float elevator) {
        try {
//            this.elevator = Float.parseFloat(elevator);
            this.y = elevator;
            notifyPropertyChanged(BR.y);
            System.out.println("elevator set to " + -1 * this.y);
            model.setElevator(-1 * this.y);
        } catch (Exception e) {
            return;
        }
    }

    public void setRudder(int rudder) {
        if (rudder != this.rudder) {
            try {
                this.rudder = rudder;
                System.out.println("notifying rudder changed to " + (((float) this.rudder) / 500 - 1));
                model.setRudder(((float) this.rudder) / 500 - 1);
                notifyPropertyChanged(BR.rudder);
            } catch (Exception e) {
                return;
            }
        }
    }

    public void setThrottle(int throttle) {
        if (throttle != this.throttle) {
            try {
                this.throttle = throttle;
                System.out.println("notifying throttle changed to " + (1 - ((float) this.throttle) / 1000));
                model.setThrottle(1 - ((float) this.throttle) / 1000);
                notifyPropertyChanged(BR.throttle);
            } catch (Exception e) {
                return;
            }
        }
    }

    @Bindable
    public float getX() {
        return this.x;
    }

    @Bindable
    public float getY() {
        return this.y;
    }

    @Bindable
    public int getRudder() {
        return this.rudder;
    }

    @Bindable
    public int getThrottle() {
        return this.throttle;
    }

    @InverseBindingAdapter(attribute = "app:x")
    public static float getX(Joystick j) {
        return j.getX();
    }

    @InverseBindingAdapter(attribute = "app:y")
    public static float getY(Joystick j) {
        return j.getY();
    }

    @BindingAdapter("app:xAttrChanged")
    public static void setXListeners(Joystick j, final InverseBindingListener attrChange) {
        // Set a listener for click, focus, touch, etc.
        j.onChange.add(new Runnable() {
            @Override
            public void run() {
                attrChange.onChange();
            }
        });
    }

    @BindingAdapter("app:yAttrChanged")
    public static void setYListeners(Joystick j, final InverseBindingListener attrChange) {
        // Set a listener for click, focus, touch, etc.
        j.onChange.add(new Runnable() {
            @Override
            public void run() {
                attrChange.onChange();
            }
        });
    }

    @BindingAdapter("app:x")
    public static void setX(Joystick j, float x) {
        System.out.println("Setting x to " + x);
        j.setX(x);
//        setX(x);
    }

    @BindingAdapter("app:y")
    public static void setY(Joystick j, float y) {
        System.out.println("Setting y to " + y);
        j.setY(y);
//        setY(y);
    }
}
