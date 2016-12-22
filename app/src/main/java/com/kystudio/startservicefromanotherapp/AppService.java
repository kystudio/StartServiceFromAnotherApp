package com.kystudio.startservicefromanotherapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

public class AppService extends Service {
    public AppService() {
    }

    private String data = "默认值";
    private boolean running = false;

    @Override
    public IBinder onBind(Intent intent) {
        return new IAppServiceRemoteBinder.Stub() {
            @Override
            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

            }

            @Override
            public void setData(String data) throws RemoteException {
                AppService.this.data = data;
            }
        };
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(getApplicationContext(),"------>Service started",Toast.LENGTH_SHORT).show();
        System.out.println("------>Service started");
        new Thread(){
            @Override
            public void run() {
                super.run();
                running = true;
                while (running){
                    System.out.println("输出值--->" + data);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(),"------>Service destroyed",Toast.LENGTH_SHORT).show();
        System.out.println("------>Service destroyed");

        running = false;
    }
}
