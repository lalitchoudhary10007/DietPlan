package com.lalit.dietplan

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        Log.e("ON" , "RECEIVE")
        Toast.makeText(context,"ON RECEIVE",Toast.LENGTH_LONG).show();

//        val service = Intent(context, NotificationService::class.java)
//        service.putExtra("reason", intent.getStringExtra("reason"))
//        service.putExtra("timestamp", intent.getLongExtra("timestamp", 0))
//
//        context.startService(service)

        NotificationService().ShowNotification(context,intent.getStringExtra("reason"),intent.getLongExtra("timestamp", 0))

    }


}