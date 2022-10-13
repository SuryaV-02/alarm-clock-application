package com.example.alarmclock

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.util.*
import kotlin.collections.ArrayList

class SqliteOpenHelper(context: Context, factory: SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
        companion object{
            val DATABASE_NAME = "canonicalalarm.db"
            val DATABASE_VERSION= 1
            val TABLE_ALARM_SCHEDULES= "alarmSchedules"

            private val COLUMN_AVATAR = "avatar"
            private val COLUMN_ID = "id_"
            private val COLUMN_TIME = "time"
            private val COLUMN_LABEL = "label"
            private val COLUMN_MILLISECS = "millisecs"
            private val COLUMN_STATUS = "status"
        }

    override fun onCreate(db: SQLiteDatabase?) {
        val createAlarmSchedulesTableCommand =( "CREATE TABLE $TABLE_ALARM_SCHEDULES (" +
                "$COLUMN_AVATAR INTEGER, " +
                "$COLUMN_ID TEXT,"+
                "$COLUMN_TIME TEXT,"+
                "$COLUMN_LABEL TEXT,"+
                "$COLUMN_MILLISECS INTEGER," +
                "$COLUMN_STATUS TEXT)")
        db?.execSQL(createAlarmSchedulesTableCommand)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val upgradeAlarmSchedulesTableCommand =("DROP TABLE IF EXISTS $TABLE_ALARM_SCHEDULES")
        db?.execSQL(upgradeAlarmSchedulesTableCommand)
        onCreate(db)
    }

    fun createAlarmSchedule(alarmSchedule : AlarmSchedule){

        val values = ContentValues()
        val db = this.writableDatabase

        values.put(COLUMN_AVATAR, alarmSchedule.avatar)
        values.put(COLUMN_ID,alarmSchedule.id)
        values.put(COLUMN_TIME,alarmSchedule.time)
        values.put(COLUMN_LABEL,alarmSchedule.label)
        values.put(COLUMN_MILLISECS,alarmSchedule.millisecs)
        values.put(COLUMN_STATUS,alarmSchedule.status)
        db.insert(TABLE_ALARM_SCHEDULES,null,values)
        Log.i("SKHST_DB","Insertion COMMAND success @createAlarmSchedule")
        db.close()
    }


    fun getAlarmSchedules() : TreeSet<AlarmSchedule>{
        var alarmSchedules  = TreeSet<AlarmSchedule>(AlarmScheduleComparator())
        var avatar : Int
        var id : String
        var time : String
        var label : String
        var millisecs : Long
        var status : String
        val SELECTION_STATUS = "\"ON\""
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_ALARM_SCHEDULES WHERE $COLUMN_STATUS = $SELECTION_STATUS",null)
        while (cursor.moveToNext()){
            avatar = cursor.getInt(cursor.getColumnIndex(COLUMN_AVATAR))
            id = cursor.getString(cursor.getColumnIndex(COLUMN_ID))
            time = cursor.getString(cursor.getColumnIndex(COLUMN_TIME))
            label = cursor.getString(cursor.getColumnIndex(COLUMN_LABEL))
            millisecs = cursor.getInt(cursor.getColumnIndex(COLUMN_MILLISECS)).toLong()
            status = cursor.getString(cursor.getColumnIndex(COLUMN_STATUS))

            val tempObj = AlarmSchedule(avatar,
                id,
                time,
                label,
                millisecs,
                status)
            alarmSchedules.add(tempObj)
        }
        cursor.close()
        return alarmSchedules
    }

    fun getAllAlarmSchedules() : TreeSet<AlarmSchedule>{
        var alarmSchedules  = TreeSet<AlarmSchedule>(AlarmScheduleComparator())
        var avatar : Int
        var id : String
        var time : String
        var label : String
        var millisecs : Long
        var status : String
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_ALARM_SCHEDULES",null)
        while (cursor.moveToNext()){
            avatar = cursor.getInt(cursor.getColumnIndex(COLUMN_AVATAR))
            id = cursor.getString(cursor.getColumnIndex(COLUMN_ID))
            time = cursor.getString(cursor.getColumnIndex(COLUMN_TIME))
            label = cursor.getString(cursor.getColumnIndex(COLUMN_LABEL))
            millisecs = cursor.getInt(cursor.getColumnIndex(COLUMN_MILLISECS)).toLong()
            status = cursor.getString(cursor.getColumnIndex(COLUMN_STATUS))

            val tempObj = AlarmSchedule(avatar,
                id,
                time,
                label,
                millisecs,
                status)
            alarmSchedules.add(tempObj)
        }
        cursor.close()
        return alarmSchedules
    }

    fun updateAlarmSchedule(id : String?,
                            time : String?,
                            label:String?,
                            millisecs :Long?,
                            status :String?){
        val values = ContentValues()
        val db = this.writableDatabase
        values.put(COLUMN_ID,id)
        values.put(COLUMN_TIME,time)
        values.put(COLUMN_LABEL,label)
        values.put(COLUMN_MILLISECS,millisecs)
        values.put(COLUMN_STATUS,status)
        val id  = "\"$id\" "
        db.update(TABLE_ALARM_SCHEDULES,values,"$COLUMN_ID = $id",null)
        db.close()
    }

    fun toggleAlarmSchedule(id : String){
        var alarmSchedule = getAlarmSchedule(id)
        val status = if((alarmSchedule.status).equals("ON")) "OFF" else "ON"
        val values = ContentValues()
        val db = this.writableDatabase
        values.put(COLUMN_STATUS,status)
        val identifier  = "\"$id\""
        db.update(TABLE_ALARM_SCHEDULES,values,"$COLUMN_ID = $identifier",null)
        db.close()
    }


    fun getAlarmSchedule(uid : String) : AlarmSchedule{
        val db = this.readableDatabase
        val id = "\"$uid\""
        var tempObj : AlarmSchedule
        var avatar : Int
        var time : String
        var label : String
        var millisecs : Long
        var status : String
        val cursor = db.rawQuery("SELECT * FROM $TABLE_ALARM_SCHEDULES WHERE $COLUMN_ID = $id",null)
        if(cursor!=null){
            cursor.moveToFirst()
        }
        avatar = cursor.getInt(cursor.getColumnIndex(COLUMN_AVATAR))
        time = cursor.getString(cursor.getColumnIndex(COLUMN_TIME))
        label = cursor.getString(cursor.getColumnIndex(COLUMN_LABEL))
        millisecs = cursor.getInt(cursor.getColumnIndex(COLUMN_MILLISECS)).toLong()
        status = cursor.getString(cursor.getColumnIndex(COLUMN_STATUS))
        tempObj = AlarmSchedule(avatar,
            id,
            time,
            label,
            millisecs,
            status)
        cursor.close()
        return tempObj
    }

    fun deleteAlarmSchedule(id : String){
        val db = this.writableDatabase
        val id  = "\"$id\" "
        db.delete(TABLE_ALARM_SCHEDULES,"$COLUMN_ID = $id",null)
        db.close()
    }

    fun getLatestAlarmSchedule() : AlarmSchedule{
        var alarmSchedules = getAlarmSchedules()
        Log.i("SKHST_056",alarmSchedules.isNotEmpty().toString())
        return alarmSchedules.first()
    }

}