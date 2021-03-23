package com.example.texttospeech;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.Telephony;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String str = "";
    TextView textView = null;
    public  static final int RequestPermissionCode  = 1 ;
    public  static final int REQUEST_OK  = 1 ;
    public  static final int REQ_CODE  = 100 ;
    TextToSpeech tts = null;
    Button button = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        button = findViewById(R.id.button);
        button.setOnClickListener(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction(Telephony.Sms.Intents.SMS_RECEIVED_ACTION);
        registerReceiver(new MyReceiver(), filter);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                Toast.makeText(MainActivity.this, "Text to speech online!", Toast.LENGTH_SHORT).show();
            }
        });
        tts.setLanguage(Locale.US);

        EnableRuntimePermissions();
    }

    public void EnableRuntimePermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                MainActivity.this,
                Manifest.permission.RECEIVE_SMS)) {
            Toast.makeText(MainActivity.this,"CONTACTS permission allows us to Access CONTACTS app", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                    Manifest.permission.RECEIVE_SMS}, RequestPermissionCode);
        }

        if (ActivityCompat.shouldShowRequestPermissionRationale(
                MainActivity.this,
                Manifest.permission.SEND_SMS)) {
            Toast.makeText(MainActivity.this,"CONTACTS permission allows us to Access CONTACTS app", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                    Manifest.permission.SEND_SMS}, RequestPermissionCode);
        }

        if (ActivityCompat.shouldShowRequestPermissionRationale(
                MainActivity.this,
                Manifest.permission.READ_CONTACTS)) {
            Toast.makeText(MainActivity.this,"CONTACTS permission allows us to Access CONTACTS app", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                    Manifest.permission.READ_CONTACTS}, RequestPermissionCode);
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == button.getId()) {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Need to speak");
            try {
                Log.e("START", "Attempting to start activity.");
                startActivityForResult(intent, REQ_CODE);
            } catch (ActivityNotFoundException a) {
                Toast.makeText(this,
                        "Sorry your device not supported",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("RESULT", "Came back!");
        if (requestCode==REQ_CODE && resultCode==RESULT_OK && data != null) {
            ArrayList<String> thingsYouSaid = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            textView.setText("Things you said:"+thingsYouSaid.get(0));
            //
            Intent intent = new Intent(this, SendActivity.class);
            intent.putExtra("message", thingsYouSaid.get(0));
            startActivity(intent);
        }
    }

    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            SmsMessage[] smsMessages = null;
            if (bundle != null)
            {
                Object[] pdus = (Object[]) bundle.get("pdus");
                smsMessages = new SmsMessage[pdus.length];
                for (int i=0; i < pdus.length; i++)
                {
                    smsMessages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    str = "SMS from " + smsMessages[i].getOriginatingAddress() +
                            " : " + smsMessages[i].getMessageBody().toString();
                }
            }
            tts.speak(str, TextToSpeech.QUEUE_FLUSH,null);
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
            textView.setText(str);
        }
    }
}
