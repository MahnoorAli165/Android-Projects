package com.example.labsessional1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.gsm.SmsMessage;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txt;
    RadioButton b1, b2, b3, b4;
    Button btn1;
    String str = "";
    TextView txt1,txt2;
    public static final int RequestPermissionCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = (TextView) findViewById(R.id.textView);
        b1 = (RadioButton) findViewById(R.id.radioButton);
        b2 = (RadioButton) findViewById(R.id.radioButton2);
        b3 = (RadioButton) findViewById(R.id.radioButton3);
        b4 = (RadioButton) findViewById(R.id.radioButton4);
        btn1 = (Button) findViewById(R.id.button);

        btn1.setOnClickListener(this);
        txt1 = (TextView) findViewById(R.id.textView3);
        txt2 = (TextView) findViewById(R.id.textView4);
        EnableRuntimePermissions();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Telephony.Sms.Intents.SMS_RECEIVED_ACTION);
        registerReceiver(new MyReceiver(), filter);


    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(this, displayImg.class);
        if (view.getId() == btn1.getId()) {

            if (view.getId() == b1.getId()) {
                String a = b1.getText() + "";
                b1.setChecked(true);
                b2.setChecked(false);
                b3.setChecked(false);
                b4.setChecked(false);
                i.putExtra("stone", a);



            }
            if (view.getId() == b2.getId()) {
                String a = b2.getText() + "";
                b1.setChecked(false);
                b2.setChecked(true);
                b3.setChecked(false);
                b4.setChecked(false);
                i.putExtra("leave", a);
            }
            if (view.getId() == b3.getId()) {
                String a = b3.getText() + "";
                b1.setChecked(false);
                b2.setChecked(false);
                b3.setChecked(true);
                b4.setChecked(false);
                i.putExtra("waterfall", a);

            }

            if (view.getId() == b4.getId()) {
                String a = b4.getText() + "";
                b1.setChecked(false);
                b2.setChecked(false);
                b3.setChecked(false);
                b4.setChecked(true);
                i.putExtra("hut", a);

            }
        }
        startActivity(i);
    }

    public void EnableRuntimePermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                MainActivity.this,
                Manifest.permission.RECEIVE_SMS)) {
            Toast.makeText(MainActivity.this, "CONTACTS permission allows us to Access CONTACTS app", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.RECEIVE_SMS}, RequestPermissionCode);
        }
    }


    public class MyReceiver extends BroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {

            Bundle bundle = intent.getExtras();
            SmsMessage[] smsMessages = null;
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                smsMessages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++) {
                    smsMessages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    str = "SMS from " + smsMessages[i].getOriginatingAddress() +
                            " : " + smsMessages[i].getMessageBody().toString();

                }
                txt1.setText(str + "");
            }

        }
    }
}










