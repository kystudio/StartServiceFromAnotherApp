package com.kystudio.startservicefromanotherapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class AppService extends Service {
    public AppService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(getApplicationContext(),"------>Service started",Toast.LENGTH_SHORT).show();
        System.out.println("------>Service started");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(),"------>Service destroyed",Toast.LENGTH_SHORT).show();
        System.out.println("------>Service destroyed");
    }
}
