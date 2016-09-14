package com.charley.messagereceiver;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by junchi on 9/14/2016.
 */
public class RemoteService extends Service {

    static final int SAY_HELLO = 0;
    static final int COUNT = 1;
    static final int RESET = 2;
    static int countNum = 0;


    class MyHandler extends Handler
    {
         int countNum = 0;
        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);
            switch(msg.what)
            {
                case COUNT:
                    countNum++;
                    Toast.makeText(getApplicationContext(), Integer.toString(countNum), Toast.LENGTH_SHORT).show();
                    break;
                case RESET:
                    countNum = 0;
                    Toast.makeText(getApplicationContext(), "Reset to 0", Toast.LENGTH_SHORT).show();
                    break;
                case SAY_HELLO:
                    Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    }
    Messenger mMessenger = new Messenger(new MyHandler());

    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
