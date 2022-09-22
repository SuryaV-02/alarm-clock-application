package com.example.alarmclock

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AlarmScheduleAdapter(val context : Context, val items : ArrayList<AlarmSchedule>) : RecyclerView.Adapter<AlarmScheduleAdapter.ViewHolder>() {
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val tv_details = view.findViewById<TextView>(R.id.tv_details)
        val tv_status = view.findViewById<TextView>(R.id.tv_status)
        val tv_label = view.findViewById<TextView>(R.id.tv_label)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context)
            .inflate(R.layout.item_alarm_row,parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val time : String? = items[position].time
        val status : String? = items[position].status
        val label : String? = items[position].label
        holder.tv_details.text = time
        holder.tv_status.text = status
        holder.tv_label.text = label
    }
}