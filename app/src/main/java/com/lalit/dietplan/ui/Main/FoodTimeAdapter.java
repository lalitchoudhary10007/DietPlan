package com.lalit.dietplan.ui.Main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lalit.dietplan.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FoodTimeAdapter extends RecyclerView.Adapter<FoodTimeAdapter.MyViewHolder>{

    JSONArray response ;

    public FoodTimeAdapter(JSONArray foods){

        this.response = foods ;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_foods, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        try {
            myViewHolder.name.setText(response.getJSONObject(i).getString("food"));
            myViewHolder.time.setText(response.getJSONObject(i).getString("meal_time"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @Override
    public int getItemCount() {
        return response.length();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name , time;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.foodName);
            time = (TextView) view.findViewById(R.id.foodTime);
        }
    }



}
