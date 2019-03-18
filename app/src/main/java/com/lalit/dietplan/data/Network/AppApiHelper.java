package com.lalit.dietplan.data.Network;

import android.util.Log;

import com.lalit.dietplan.data.prefs.AppPreferencesHelper;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONObject;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
@Singleton
public class AppApiHelper implements ApiHelper{

    private AppPreferencesHelper mPrefernceHelper;
    private ApiHeader mApiHeader;

    @Inject
    public AppApiHelper(ApiHeader apiHeader,AppPreferencesHelper mPrefernceHelper) {
        mApiHeader = apiHeader;
        this.mPrefernceHelper=mPrefernceHelper;

    }

    @Override
    public Single<JSONObject> getDietData() {
        Log.e("END POINT:- " , ""+ApiEndPoint.ENDPOINT_DIET_DATA);
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_DIET_DATA)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getJSONObjectSingle();
               // .getObjectSingle(DietResponse.class);
    }
}
