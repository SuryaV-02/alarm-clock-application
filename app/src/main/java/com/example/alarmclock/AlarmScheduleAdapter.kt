package com.example.alarmclock

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView

class AlarmScheduleAdapter(val context : Context, val items : ArrayList<AlarmSchedule>) : RecyclerView.Adapter<AlarmScheduleAdapter.ViewHolder>() {
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val tv_details = view.findViewById<TextView>(R.id.tv_details)
        val tv_label = view.findViewById<TextView>(R.id.tv_label)
//        val iv_icon = view.findViewById<ImageView>(R.id.iv_icon)
        val switch_status = view.findViewById<SwitchCompat>(R.id.switch_status)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context)
            .inflate(R.layout.item_alarm_row,parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }
    @RequiresApi(Build.VERSION_CODES.M)
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
        if(items[position].status.equals("OFF")){
            holder.switch_status.isChecked = !holder.switch_status.isChecked
            Log.i("SKHST_451","Schedule is ON");
        }
        holder.switch_status.setOnClickListener{
            if(!holder.switch_status.isChecked){
                val dbHelper : SqliteOpenHelper? = null
                dbHelper?.toggleAlarmSchedule(items[position].id.toString())
                AlarmHelper.cancelAlarm(items[position].id.toString().toInt(),context)
                AlarmHelper.showToast(context,"Alarm cancelled")
            }else{
                val alarmSchedule = AlarmSchedule(items[position].avatar,items[position].id,
                    items[position].time,items[position].label,items[position].millisecs,
                    "ON")
                AlarmHelper.createAlarmPendingIntent(alarmSchedule,context)
                AlarmHelper.showToast(context,"Alarm created for ${items[position].time}")
            }
        }
//        val context: Context = holder.iv_icon.context
        val id = context.resources.getIdentifier(avatarName, "drawable", context.packageName)
//        holder.iv_icon.setImageResource(id)




    }
}