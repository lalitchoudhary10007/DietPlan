package com.lalit.dietplan.Receiver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import com.lalit.dietplan.R;
import com.lalit.dietplan.ui.Main.MainActivity;

import java.util.Calendar;

public class AlarmReceiver extends BroadcastReceiver {

    String CHANNEL_ID = "samples.notification.lalit.com.CHANNEL_ID";
    String CHANNEL_NAME = "Sample Notification";
    private Notification mNotification;
    private int mNotificationId =  1000;

    @Override
    public void onReceive(Context context, Intent intent) {

        ShowNotification(context,intent.getStringExtra("reason"),intent.getLongExtra("timestamp", 0));


    }




    public void ShowNotification(Context context, String reason, Long timeStamp){

        createChannel(context);

        if (timeStamp > 0) {


            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Intent notifyIntent = new Intent(context , MainActivity.class);

            String title = "Your Diet";
            String message = reason;

            notifyIntent.putExtra("title", title);
            notifyIntent.putExtra("message", message);
            notifyIntent.putExtra("notification", true);

            notifyIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(timeStamp);


            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {


                mNotification = new Notification.Builder(context, CHANNEL_ID)
                        // Set the intent that will fire when the user taps the notification
                        .setContentIntent(pendingIntent)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setAutoCancel(true)
                        .setContentTitle(title)
                        .setStyle(new Notification.BigTextStyle()
                                .bigText(message))
                        .setContentText(message).build();
            } else {

                mNotification = new Notification.Builder(context)
                        // Set the intent that will fire when the user taps the notification
                        .setContentIntent(pendingIntent)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setAutoCancel(true)
                        .setPriority(Notification.PRIORITY_MAX)
                        .setContentTitle(title)
                        .setStyle(new Notification.BigTextStyle()
                                .bigText(message))
                        .setSound(uri)
                        .setContentText(message).build();

            }



            notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(mNotificationId, mNotification);

        }

    }


    private void createChannel(Context context) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            int importance = NotificationManager.IMPORTANCE_HIGH ;
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
            notificationChannel.enableVibration(true);
            notificationChannel.setShowBadge(true);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.parseColor("#e8334a"));
            notificationChannel.setDescription("Diet Plan");
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            notificationManager.createNotificationChannel(notificationChannel);
        }

    }


}
