package com.example.alarmclock

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView

class AlarmScheduleAdapter(val context : Context, val items : ArrayList<AlarmSchedule>) : RecyclerView.Adapter<AlarmScheduleAdapter.ViewHolder>() {
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val tv_details = view.findViewById<TextView>(R.id.tv_details)
        val tv_label = view.findViewById<TextView>(R.id.tv_label)
        val iv_icon = view.findViewById<ImageView>(R.id.iv_icon)
        val switch_status = view.findViewById<SwitchCompat>(R.id.switch_status)

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
        var message =  items[position].label
        val avatarName = "avatar_0" + items[position].avatar
        if(message.isNullOrEmpty()){
            message = "Alarm"
        }
        val label : String = message
        holder.tv_details.text = time
        holder.tv_label.text = label

        holder.switch_status.isChecked = status.equals("ON")
        val context: Context = holder.iv_icon.context
        val id = context.resources.getIdentifier(avatarName, "drawable", context.packageName)
        holder.iv_icon.setImageResource(id)


    }
}