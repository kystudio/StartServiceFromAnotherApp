package com.kystudio.anotherapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.kystudio.startservicefromanotherapp.IAppServiceRemoteBinder;

public class AnotherActivity extends AppCompatActivity {
    private Intent serviceIntent;
    private ServiceConnection conn;
    private EditText etInputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInputText = (EditText) findViewById(R.id.etInputText);

        serviceIntent = new Intent();
        serviceIntent.setComponent(new ComponentName("com.kystudio.startservicefromanotherapp", "com.kystudio.startservicefromanotherapp.AppService"));

        conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                System.out.println("Bind Service");
                System.out.println(iBinder);

                binder = IAppServiceRemoteBinder.Stub.asInterface(iBinder);
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };

        findViewById(R.id.btnStartAppService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("btnStart");
                startService(serviceIntent);
            }
        });

        findViewById(R.id.btnStopAppService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("btnStop");
                stopService(serviceIntent);
            }
        });

        findViewById(R.id.btnBindAppService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("btnBind");
                bindService(serviceIntent, conn, Context.BIND_AUTO_CREATE);
            }
        });

        findViewById(R.id.btnUnbindAppService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("btnUnbind");
                unbindService(conn);
                binder = null;
            }
        });

        findViewById(R.id.btnSyncData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("btnSyncData");
                if (binder != null) {
                    try {
                        binder.setData(etInputText.getText().toString());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private IAppServiceRemoteBinder binder = null;
}
