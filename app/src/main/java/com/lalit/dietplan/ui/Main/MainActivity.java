package com.lalit.dietplan.ui.Main;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.lalit.dietplan.R;
import com.lalit.dietplan.Receiver.AlarmReceiver;
import com.lalit.dietplan.ui.Base.BaseActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject
    MainMvpPresenter<MainMvpView> mPrsenter;
    RecyclerView recyclerView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycle_view_weekdays);


        getActivityComponent().inject(MainActivity.this);
        mPrsenter.onAttach(this);
        mPrsenter.LoadData();


    }




    @Override
    protected void setUp() {

    }

    @Override
    public void ShowData(JSONObject response) {
        JSONObject js = null;
        try {
            js = response.getJSONObject("week_diet_data");
            ArrayList<String> weekdays = new ArrayList<String>();
            Iterator<String> keys = js.keys();
            while (keys.hasNext()){
                String key = keys.next();
                weekdays.add(key);
                JSONArray array = js.getJSONArray(key);
                for(int i = 0 ; i < array.length() ; i++){
                    JSONObject obj = array.getJSONObject(i);
                    String food = obj.getString("food");
                    String[] time = obj.getString("meal_time").split(":");
                    String hour;
                    String min ;

                    if(time[0].charAt(0) == '0'){
                        hour = time[0].replaceFirst("0" , "");
                    }else{
                        hour = time[0];
                    }

                    if(time[1].charAt(0) == '0'){
                        min = time[1].replaceFirst("0" , "");
                    }else{
                        min = time[1];
                    }

                    int dayType = 0 ;
                    switch (key){
                        case "thursday":
                        dayType = 5 ;
                        break;
                        case "wednesday":
                        dayType = 4 ;
                        break;
                        case "monday":
                        dayType = 2 ;
                        break;
                        case "tuesday":
                        dayType = 3 ;
                        break;
                 }

                mPrsenter.SetReminder(this, food , dayType , Integer.parseInt(hour), Integer.parseInt(min));

                }

            }

            DaysAdapter mAdapter = new DaysAdapter(weekdays , js);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(mAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }






    }

    @Override
    public void Reminder() {



    }








}
