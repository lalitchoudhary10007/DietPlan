package com.lalit.dietplan.ui.Main;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lalit.dietplan.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.MyViewHolder>{

    ArrayList<String> dates ;
    JSONObject response ;

   public DaysAdapter(ArrayList<String> days , JSONObject res){
       this.dates = days ;
       this.response = res ;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_weekdays, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
         holder.date.setText(dates.get(position));

        FoodTimeAdapter mAdapter = null;
        try {
            mAdapter = new FoodTimeAdapter(response.getJSONArray(dates.get(position)));
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(holder.recyclerView.getContext());
            holder.recyclerView.setLayoutManager(mLayoutManager);
            holder.recyclerView.setItemAnimator(new DefaultItemAnimator());
            holder.recyclerView.setAdapter(mAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return dates.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView date;
        private RecyclerView recyclerView;

        public MyViewHolder(View view) {
            super(view);
            date = (TextView) view.findViewById(R.id.locationDate);
            recyclerView = (RecyclerView) view.findViewById(R.id.locationList);
        }
    }



}
