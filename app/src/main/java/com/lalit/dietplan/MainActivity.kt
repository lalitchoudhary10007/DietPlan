package com.lalit.dietplan

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import com.lalit.dietplan.Adapters.MainAdapter
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    private lateinit var preferencesHelper: PreferencesHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        preferencesHelper = PreferencesHelper(this)

        val remind = findViewById<Button>(R.id.button2);
        remind.setOnClickListener(View.OnClickListener {
            val mNotificationTime = Calendar.getInstance().timeInMillis + 10000
            setfixReminder(this@MainActivity, mNotificationTime);
        });


        ShowDataAndReminder();


    }

    fun ShowDataAndReminder(){

        val file_name = "Response.json"
        val json_string = application.assets.open(file_name).bufferedReader().use{
            it.readText()
        }

        var jsonObject = JSONObject(json_string);
        var js = jsonObject.getJSONObject("week_diet_data");
        val keys = js.keys()
        val weedays = ArrayList<String>();
        while (keys.hasNext()){
            val currentDynamicKey = keys.next() as String ;
            weedays.add(currentDynamicKey);
            var f = js.getJSONArray(currentDynamicKey)
            for (i in 0..(f.length()-1)) {
                val item = f.getJSONObject(i)
                var food = item.getString("food")
                var time = item.getString("meal_time").split(":")
                var hour = ""
                var min = ""
                if(time[0].get(0) == '0'){
                    hour = time[0].replaceFirst("0" , "")
                }else{
                    hour = time[0]
                }
                if(time[1].get(0) == '0'){
                    min = time[1].replaceFirst("0" , "")
                }else{
                    min = time[1]
                }

                var dayType = 0
                when(currentDynamicKey) {
                    "thursday" -> dayType = 5
                    "wednesday" -> dayType = 4
                    "monday" -> dayType = 2
                    "tuesday" -> dayType = 3
                }
                if(preferencesHelper.GetSharedPrefValue(PreferencesHelper.Diet_Key) == false){
                    Log.e("Reminder Set" , "DONE");
                    setNotification(
                            this@MainActivity,
                            food,
                            dayType,
                            hour.toInt(), min.toInt(), 1)
                }else{
                    Log.e("Already Reminder Set" , "DONE")
                }


            }
        }

        val rv = findViewById<RecyclerView>(R.id.recycle_view_weekdays)
        rv.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        // Access RecyclerView Adapter and load the data
        val adapter = MainAdapter(weedays, js)
        rv.adapter = adapter

        preferencesHelper.SaveValueInSharedPref(false ,PreferencesHelper.Diet_Key)


    }




    fun setNotification(activity: Activity, reason: String, day: Int, hour: Int, minute: Int, i: Int) {

        //------------  alarm settings start  -----------------//

                val calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_WEEK, day)
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, minute - 5)
                var sd1 = calendar.timeInMillis

        val currentTimeMillis = Calendar.getInstance().timeInMillis
        Log.e("**"+currentTimeMillis , ""+sd1)
        if (currentTimeMillis < sd1) {

            val alarmManager = activity.getSystemService(Activity.ALARM_SERVICE) as AlarmManager
            val alarmIntent = Intent(activity.applicationContext, AlarmReceiver::class.java) // AlarmReceiver1 = broadcast receiver

            alarmIntent.putExtra("reason", reason)
            alarmIntent.putExtra("timestamp", sd1)

            val _id = System.currentTimeMillis().toInt()
            val pendingIntent = PendingIntent.getBroadcast(activity, _id, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT)
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, sd1, 24 * 7 * 60 * 60 * 1000, pendingIntent)

        }

        //------------ end of alarm settings  -----------------//


    }


    fun setfixReminder(activity: Activity, timeInMilliSeconds: Long){

        val alarmManager = activity.getSystemService(Activity.ALARM_SERVICE) as AlarmManager
        val alarmIntent = Intent(activity.applicationContext, AlarmReceiver::class.java) // AlarmReceiver1 = broadcast receiver

        alarmIntent.putExtra("reason", "Custom Reminder")
        alarmIntent.putExtra("timestamp", timeInMilliSeconds)

        val _id = System.currentTimeMillis().toInt()
        val pendingIntent = PendingIntent.getBroadcast(activity, _id, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT)
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeInMilliSeconds, pendingIntent)


    }



}
