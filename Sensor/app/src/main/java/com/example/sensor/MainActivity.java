package com.example.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.YELLOW;
import static android.graphics.Color.BLUE;

public class MainActivity extends AppCompatActivity implements SensorEventListener, LocationListener, View.OnClickListener {
    private static final String TAG = "";
    Sensor sen_acc, proximity, gyroscopeSensor, rotationVectorSensor;
    SensorManager senMgr;
    TextView txt1, txt2, txt3, txt5, txt6, txt8,txt9,txt10;
    LocationManager locationManager_;
    Button btn, btn2;
    double longitude =0.0;
    double latitude =0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt1 = (TextView) findViewById(R.id.textView2);
        txt2 = (TextView) findViewById(R.id.textView3);
        txt3 = (TextView) findViewById(R.id.textView4);
        txt5 = (TextView) findViewById(R.id.textView5);
        txt6 = (TextView) findViewById(R.id.textView6);
        txt8 = (TextView) findViewById(R.id.textView8);
        txt9 = (TextView) findViewById(R.id.textView9);
        txt10 = (TextView) findViewById(R.id.textView10);

        btn = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);


        btn.setOnClickListener(this);


        senMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
        sen_acc = senMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senMgr.registerListener(this, sen_acc, SensorManager.SENSOR_DELAY_NORMAL);

        locationManager_ = (LocationManager) getSystemService(LOCATION_SERVICE);
        proximity = senMgr.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if (proximity == null) {
            Log.e(TAG, "Proximity sensor not available.");
            finish(); // Close app
        }
        senMgr.registerListener(this, proximity, 2 * 1000 * 1000);


        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        locationManager_.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10,
                Criteria.ACCURACY_FINE, this);


    }



    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float xaccel = sensorEvent.values[0];
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            String x = decimalFormat.format(xaccel);
            txt1.setText(x + "");
            float yaccel = sensorEvent.values[1];
            String y = decimalFormat.format(yaccel);
            txt2.setText(y + "");
            float zaccel = sensorEvent.values[2];
            String z = decimalFormat.format(zaccel);
            txt3.setText(z + "");
        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            if (sensorEvent.values[0] < proximity.getMaximumRange()) {
                // Detected something nearby
                btn2.setBackgroundColor(RED);
            } else {
                // Nothing is nearby
                btn2.setBackgroundColor(GREEN);
            }

        }

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

        double altitude = location.getAltitude();
        longitude = location.getLongitude();
        latitude = location.getLatitude();
        float speed = location.getSpeed();
        float bearing = location.getBearing();
        float accuracy = location.getAccuracy(); //in meters
        long time = location.getTime(); //when the time was obtained
        txt6.setText(latitude+"");
        txt10.setText(longitude+"");





    }
    @Override
    public void onClick(View view) {
        Uri gmmIntentUri = Uri.parse("geo:"+latitude+","+longitude+"?z=20");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    protected void onPause() {
        super.onPause();
        locationManager_.removeUpdates(this);
        senMgr.unregisterListener(this,sen_acc);
        senMgr.unregisterListener(this,proximity);

    }

    protected void onResume() {
        super.onResume();

    }
}
