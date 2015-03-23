package com.example.dhruv.callupmate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private ArrayList<String> numbers;

    private Integer i =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        numbers = new ArrayList<>();

        numbers.add("tel:111111");
        numbers.add("tel:22222");
        numbers.add("tel:33333");


            TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            TelephonyMgr.listen(new TeleListener(),
                    PhoneStateListener.LISTEN_CALL_STATE);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    class TeleListener extends PhoneStateListener {
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);



            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:
                    // CALL_STATE_IDLE;
                    if(i<numbers.size()) {
                        makeACall();
                    }
                    else
                    {
                        finish();
                    }

                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    // CALL_STATE_OFFHOOK;

                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    // CALL_STATE_RINGING

                    break;
                default:
                    break;
            }


        }

    }


    public void makeACall()
    {
        String url =  numbers.get(i);

        i++;

        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));

        startActivity(intent);

    }

}