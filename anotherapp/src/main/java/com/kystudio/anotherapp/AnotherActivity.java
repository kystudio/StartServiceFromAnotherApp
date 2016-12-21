package com.kystudio.anotherapp;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AnotherActivity extends AppCompatActivity {
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent();
        intent.setComponent(new ComponentName("com.kystudio.startservicefromanotherapp","com.kystudio.startservicefromanotherapp.AppService"));

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
    }
}
