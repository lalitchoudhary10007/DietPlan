package com.lalit.dietplan.ui.Main;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import com.androidnetworking.error.ANError;
import com.lalit.dietplan.Receiver.AlarmReceiver;
import com.lalit.dietplan.data.DataManager;
import com.lalit.dietplan.ui.Base.BasePresenter;
import com.lalit.dietplan.utils.rx.SchedulerProvider;

import org.json.JSONObject;

import java.util.Calendar;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V>{


    @Inject
    public MainPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void LoadData() {

        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getDietData()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<JSONObject>() {
                    @Override
                    public void accept(JSONObject response) throws Exception {

                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();


                       getMvpView().ShowData(response);
                       getDataManager().SetReminderValue(true);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();

                        // handle the login error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));


    }

    @Override
    public void SetReminder(MainActivity mainActivity, String food, int dayType, int hour, int min) {

        Log.e("Reminder Value" , ""+getDataManager().GetRemindervalue());

        if(getDataManager().GetRemindervalue() == false){
            setNotification(mainActivity, food , dayType , hour , min);
        }

    }


    public void setNotification(Activity context, String reason , int day , int hour, int minute) {

        //------------  alarm settings start  -----------------//
        Log.e("Notification Set"+reason+ day , ""+hour + minute);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute - 5);
        long sd1 = calendar.getTimeInMillis();

        long currentTimeMillis = Calendar.getInstance().getTimeInMillis();
        if (currentTimeMillis < sd1) {

            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Activity.ALARM_SERVICE);
            Intent alarmIntent = new Intent(context.getApplicationContext(), AlarmReceiver.class); // AlarmReceiver1 = broadcast receiver

            alarmIntent.putExtra("reason", reason);
            alarmIntent.putExtra("timestamp", sd1);

            int _id = (int) System.currentTimeMillis();
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, _id, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, sd1, 24 * 7 * 60 * 60 * 1000, pendingIntent);

        }

    }

}
