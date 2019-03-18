package com.lalit.dietplan.ui.Main;

import com.lalit.dietplan.ui.Base.MvpView;

import org.json.JSONObject;

public interface MainMvpView extends MvpView {

   void ShowData(JSONObject response);
   void Reminder();

}
