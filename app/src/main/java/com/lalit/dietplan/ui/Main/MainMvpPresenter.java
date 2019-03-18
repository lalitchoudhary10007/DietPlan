package com.lalit.dietplan.ui.Main;

import com.lalit.dietplan.di.PerActivity;
import com.lalit.dietplan.ui.Base.MvpPresenter;

@PerActivity
public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    void LoadData();

    void SetReminder(MainActivity mainActivity, String food, int dayType, int i, int parseInt);


}
