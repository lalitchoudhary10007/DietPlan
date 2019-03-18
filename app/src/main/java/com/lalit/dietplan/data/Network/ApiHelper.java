package com.lalit.dietplan.data.Network;

import org.json.JSONObject;

import io.reactivex.Single;

public interface ApiHelper {

    Single<JSONObject> getDietData();


}
