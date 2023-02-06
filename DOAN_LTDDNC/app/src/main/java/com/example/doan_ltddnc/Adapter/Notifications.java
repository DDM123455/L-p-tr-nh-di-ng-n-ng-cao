package com.example.doan_ltddnc.Adapter;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.doan_ltddnc.App;
import com.example.doan_ltddnc.MainActivity;
import com.example.doan_ltddnc.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class Notifications extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        RemoteMessage.Notification notification=message.getNotification();
        if (notification==null){
            return;}
        String title=notification.getTitle();
        String body=notification.getBody();
        sendNotification(title,body);

    }

    private void sendNotification(String title, String body) {
        Intent intent =new Intent(this, MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notificationBulider=new NotificationCompat.Builder(this, App.ChannelID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent);
       Notification notification=notificationBulider.build();
        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (notificationManager!=null)
        {
            notificationManager.notify(1,notification);
        }
    }
}
