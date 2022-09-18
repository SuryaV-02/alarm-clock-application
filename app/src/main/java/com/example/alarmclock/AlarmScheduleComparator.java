package com.example.alarmclock;

import java.util.Comparator;

public class AlarmScheduleComparator implements Comparator<AlarmSchedule> {
    public int compare(AlarmSchedule s1, AlarmSchedule s2)
    {
        if (s1.getMilliSecs() == s2.getMilliSecs())
            return 0;
        else if (s1.getMilliSecs() > s2.getMilliSecs())
            return 1;
        else
            return -1;
    }
}
