package com.example.ap2_3.views;

import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.ap2_3.R;
import com.example.ap2_3.view_model.FlightControlViewModel;

import com.example.ap2_3.databinding.ActivityMainBinding;
import androidx.databinding.*;

public class MainActivity extends AppCompatActivity {
    public FlightControlViewModel vm;
    ActivityMainBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_main);
        bi.setView(this);

        ((Button) findViewById(R.id.connectBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SeekBar) findViewById(R.id.sbThrottle)).setProgress(1000);
                ((SeekBar) findViewById(R.id.rudderBar)).setProgress(500);
                vm.connect();
            }
        });

        vm = new FlightControlViewModel();
        bi.setVm(vm);
    }
}