package com.kystudio.anotherapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AnotherActivity extends AppCompatActivity {
    private Intent intent;
    private ServiceConnection conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent();
        intent.setComponent(new ComponentName("com.kystudio.startservicefromanotherapp","com.kystudio.startservicefromanotherapp.AppService"));

        conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                System.out.println("Bind Service");
                System.out.println(iBinder);
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };

        findViewById(R.id.btnStartAppService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("btnStart");
                startService(intent);
            }
        });

        findViewById(R.id.btnStopAppService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("btnStop");
                stopService(intent);
            }
        });

        findViewById(R.id.btnBindAppService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("btnBind");
                bindService(intent,conn, Context.BIND_AUTO_CREATE);
            }
        });

        findViewById(R.id.btnUnbindAppService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("btnUnbind");
                unbindService(conn);
            }
        });
    }
}
