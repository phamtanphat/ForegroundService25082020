package com.example.foregroundservice25082020;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class MyService extends Service {
    String MY_CHANNEL = "MY_CHANNEL";
    NotificationManager mNotificationManager;
    NotificationCompat.Builder mNotification;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotification = createNotification(this);
        startForeground(1,mNotification.build());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("BBB","onStartCommand");
        Toast.makeText(this, "onStartCommand", Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }
    private NotificationCompat.Builder createNotification(Context context){
        NotificationCompat.Builder notification =
                new NotificationCompat.Builder(context, MY_CHANNEL)
                        .setContentTitle("Ban cap nhat moi")
                        .setContentText("Phien ban app 15.0")
                        .setShowWhen(true)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setLargeIcon(
                                BitmapFactory.decodeResource(
                                        getResources(),
                                        R.drawable.icon_jetpack
                                ))
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(
                                getResources(),
                                R.drawable.icon_jetpack
                        )))
                        .setOngoing(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel =
                    new NotificationChannel(
                            MY_CHANNEL,
                            "CHANNEL",
                            NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
        return notification;
    }

}
