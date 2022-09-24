package com.example.alarmclock

class AlarmSchedule(
    var avatar : Int?,
    var id: String?,
    var time: String?,
    var label: String? = "Alarm",
    var millisecs: Long?,
    var status: String?) {

    fun getMilliSecs(): Long? {
        return this.millisecs
    }
    fun getAlarmTime(): String? {
        return this.time
    }
    fun setMilliSecs(millisecs: Long){
        this.millisecs = millisecs
    }
    fun setAlarmTime(time: String?){
        this.time = time
    }
}