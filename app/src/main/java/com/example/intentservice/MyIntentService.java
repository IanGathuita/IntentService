package com.example.intentservice;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class MyIntentService extends IntentService {

    public static final String TAG = "My IntentService";
    public static final String NOTIFICATION_IDENTIFIER = "com.example.notification";
    public static final String VISIBLE_CHANNEL_NAME = "Basic notification";
    NotificationChannel notificationChannel;
    NotificationManager notificationManager;

    public MyIntentService() {
        super("My IntentService");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();
        //We can acquire a wakelock here if we want service to run when screen is off
        notificationChannel = new NotificationChannel(NOTIFICATION_IDENTIFIER,VISIBLE_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,NOTIFICATION_IDENTIFIER)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("My IntentService")
                .setContentText("This is my IntentService notification");
        Notification notification = builder.build();

        startForeground(123,notification);

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        for(int i=0;i<10;i++){
            Log.d(TAG,"Service at " + i + "\n");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
