package com.dimas9009.apismssender;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public TextView TextView;
    private SocketProvider socketProvider;
    public static final int SERVERPORT = 6000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView = (TextView)findViewById(R.id.text2);

        socketProvider = new SocketProvider(this);
        socketProvider.StartServerCommunication(SERVERPORT);


        // uiConversationHandler = new Handler();


        //SmsProvider smsProvider = new SmsProvider();
        //Cursor cursor = getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
        //ReceivedSms sms = smsProvider.GetLastNotReadSms("+79930261127", cursor);
        //text.setText(sms.Message);
    }

    @Override
    protected void onStop() {
        super.onStop();
        socketProvider.StopServerCommunication();
    }
}
