package com.charley.messagesender;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    static final int SAY_HELLO = 0;
    static final int COUNT = 1;
    static final int RESET = 2;
    Button countBtn,resetBtn, helloBtn;
    static boolean isBinded = false;
    Messenger messenger;
    Message msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent mIntent = new Intent("com.charley.messagereceiver.RemoteService.java");
        mIntent.setPackage("com.charley.messagereceiver");
        mIntent.setAction("com.charley.RemoteService");
        bindService(mIntent,mServiceConnection,BIND_AUTO_CREATE);

        helloBtn = (Button) findViewById(R.id.helloBtn);
        helloBtn.setOnClickListener(this);
        countBtn = (Button) findViewById(R.id.countBtn);
        countBtn.setOnClickListener(this);
        resetBtn = (Button) findViewById(R.id.resetBtn);
        resetBtn.setOnClickListener(this);


    }


    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.helloBtn:
                 msg = Message.obtain(null, SAY_HELLO, 0, 0);
                if (isBinded) {
                    try {
                        messenger.send(msg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.countBtn:
                 msg = Message.obtain(null,COUNT,0,0);
                if (isBinded) {
                    try {
                        messenger.send(msg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.resetBtn:
                msg = Message.obtain(null,RESET,0,0);
                if (isBinded) {
                    try {
                        messenger.send(msg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }


    }


    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            isBinded = true;
            messenger = new Messenger(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBinded = false;
            mServiceConnection = null;
        }
    };

}
