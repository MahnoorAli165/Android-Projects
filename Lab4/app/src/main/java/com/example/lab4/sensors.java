package com.example.lab4;

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
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class sensors extends AppCompatActivity implements SensorEventListener, LocationListener {

    private TextView tv_acc = null;
    private TextView tv_gyr = null;
    private TextView tv_prox = null;
    private TextView tv_location = null;
    private Button btn_getLocation = null;
    SensorManager smgr;
    Sensor sen_acc;
    Sensor sen_gyr;
    Sensor sen_prox;

    private LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);

        tv_acc = findViewById(R.id.tv_acc);
        tv_gyr = findViewById(R.id.tv_gyr);
        tv_prox = findViewById(R.id.tv_prox);
        tv_location = findViewById(R.id.tv_location);
        btn_getLocation = findViewById(R.id.btn_getLocation);

        smgr = (SensorManager) getSystemService(SENSOR_SERVICE);

        sen_acc = smgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        smgr.registerListener((SensorEventListener) this, sen_acc, SensorManager.SENSOR_DELAY_NORMAL);

        sen_gyr = smgr.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        smgr.registerListener(this, sen_gyr, SensorManager.SENSOR_DELAY_NORMAL);

        sen_prox = smgr.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        smgr.registerListener(this, sen_prox, SensorManager.SENSOR_DELAY_NORMAL);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10, Criteria.ACCURACY_FINE, this);

    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            float xaccl = event.values[0];
            float yaccl = event.values[1];
            float zaccl = event.values[2];
            String acc = xaccl + " -- " + yaccl + " -- " + zaccl;
            tv_acc.setText(acc+"");
        }
        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE){
            float xaccl = event.values[0];
            float yaccl = event.values[1];
            float zaccl = event.values[2];
            String acc1 = xaccl + " -- " + yaccl + " -- " + zaccl;
            tv_gyr.setText(acc1+"");
        }
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            float xaccl = event.values[0];

            String acc2 = xaccl + " -- ";
            tv_prox.setText(acc2 + "");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onLocationChanged(Location location) {
        double altitude = location.getAltitude();
        final double longitude = location.getLongitude();
        final double latitude = location.getLatitude();
        float speed = location.getSpeed();
        float bearing = location.getBearing();
        float accuracy = location.getAccuracy();
        long time = location.getTime();

        btn_getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri locationn = Uri.parse("geo:"+latitude+","+longitude+"?z=14");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, locationn);
                startActivity(mapIntent);
            }
        });

        tv_location.setText("Longitude: "+ longitude+" Latitude: "+latitude);

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
