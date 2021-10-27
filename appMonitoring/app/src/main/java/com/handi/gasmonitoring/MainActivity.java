package com.handi.gasmonitoring;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Looper;
import android.view.View;
import android.os.Handler;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView sensorValue, ledStatus;
    ProgressBar progressbarSensor, progressbarLed;
    DatabaseReference dref;
    String status1, status2;
    Button on, off;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorValue = findViewById(R.id.nilaiSensor);
//        ledStatus = findViewById(R.id.nilaiLed);
        progressbarSensor = findViewById(R.id.progressBarSensor);
//        progressbarLed = findViewById(R.id.progressBarLed);

        on = findViewById(R.id.buttonOn);
        off = findViewById(R.id.buttonOff);

        progressbarSensor.setVisibility(View.GONE);
//        progressbarLed.setVisibility(View.GONE);
        sensorValue.setVisibility(View.VISIBLE);
//        ledStatus.setVisibility(View.VISIBLE);

        dref = FirebaseDatabase.getInstance().getReference();
        final Handler handler = new Handler(Looper.getMainLooper());
        dref.child("Gas Sensor").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do something after 100ms
                        progressbarSensor.setVisibility(View.GONE);
//                        progressbarLed.setVisibility(View.GONE);
                        sensorValue.setVisibility(View.VISIBLE);
//                        ledStatus.setVisibility(View.VISIBLE);

                    }
                }, 1000);

                sensorValue.setVisibility(View.GONE);
//                ledStatus.setVisibility(View.GONE);
                progressbarSensor.setVisibility(View.VISIBLE);
//                progressbarLed.setVisibility(View.VISIBLE);
                status1 = dataSnapshot.child("sensorValue").getValue().toString();
                sensorValue.setText(status1);
//                status2 = dataSnapshot.child("ledStatus").getValue().toString();
//                ledStatus.setText(status2);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        on.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dref = FirebaseDatabase.getInstance().getReference();
                dref.child("Gas Sensor").child("ledStatus").setValue(true);
            }
        });
        off.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dref = FirebaseDatabase.getInstance().getReference();
                dref.child("Gas Sensor").child("ledStatus").setValue(false);
            }
        });
    }
}