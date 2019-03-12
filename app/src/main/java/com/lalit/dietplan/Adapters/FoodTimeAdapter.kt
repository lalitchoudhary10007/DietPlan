package com.lalit.dietplan.Adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lalit.dietplan.R
import org.json.JSONArray

class FoodTimeAdapter(val foods: JSONArray): RecyclerView.Adapter<FoodTimeAdapter.ViewHolder>() {


    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {

        val food = foods.getJSONObject(p1);
        p0.locationName?.text = food.getString("food")
        p0.locationAddress?.text = food.getString("meal_time")


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodTimeAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_foods, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return foods.length()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val locationName = itemView.findViewById<TextView>(R.id.locationName)
        val locationAddress = itemView.findViewById<TextView>(R.id.locationAddress)
    }

}