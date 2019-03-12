package com.lalit.dietplan.Adapters

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.lalit.dietplan.R
import org.json.JSONObject

class MainAdapter(val weekDays: ArrayList<String> , val ResponseObj: JSONObject): RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        // Update date label
        holder.date?.text = weekDays.get(position);
        // Create vertical Layout Manager
        holder.rv?.layoutManager = LinearLayoutManager(holder.rv.context, LinearLayout.VERTICAL, false)
        // Access RecyclerView Adapter and load the data
        var js = ResponseObj.getJSONArray(weekDays.get(position))
        var adapter = FoodTimeAdapter(js);
        holder.rv?.adapter = adapter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_weekdays, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return weekDays.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val date = itemView.findViewById<TextView>(R.id.locationDate)
        val rv = itemView.findViewById<RecyclerView>(R.id.locationList)
    }

}