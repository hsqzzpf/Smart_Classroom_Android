package com.example.wangtianduo.teacher_end.calendar_module;

import android.content.Intent;
import android.util.Log;

import com.alamkanak.weekview.WeekViewEvent;
import com.example.wangtianduo.teacher_end.R;
import com.example.wangtianduo.teacher_end.sqlite_module.ClassDbHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class CalendarBasicActivity extends CalendarBaseActivity {

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        // Populate the week view with some events.
        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
        HashMap<String, Integer> class_color = new HashMap<>();

        //TODO add key-value pair that specify classname and color

        ClassDbHelper classDbHelper = ClassDbHelper.createClassDbHelper(this);
        int length = (int) classDbHelper.queryNumRows();

//        Calendar startTime = Calendar.getInstance();
//        startTime.set(Calendar.HOUR_OF_DAY, 3);
//        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.MONTH, newMonth - 1);
//        Log.i("ASDD", "newMonth value: " + String.valueOf(newMonth-1));
//        startTime.set(Calendar.YEAR, newYear);
//        Log.i("ASDD", "newYear value: " + String.valueOf(newYear));
//        Calendar endTime = (Calendar) startTime.clone();
//        endTime.add(Calendar.HOUR, 2);
//        endTime.set(Calendar.MONTH, newMonth - 1);
//        WeekViewEvent event = new WeekViewEvent(1, getResources().getText(R.string.java).toString(), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_01));
//        events.add(event);
//
//        Calendar startTime = Calendar.getInstance();
//        startTime.set(Calendar.HOUR_OF_DAY, 3);
//        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.MONTH, 11);
//        Log.i("ASDD", "newMonth value: " + String.valueOf(newMonth-1));
//        startTime.set(Calendar.YEAR, newYear);
//        Log.i("ASDD", "newYear value: " + String.valueOf(2018));
//        Calendar endTime = (Calendar) startTime.clone();
//        endTime.add(Calendar.HOUR, 2);
//        endTime.set(Calendar.MONTH, newMonth - 1);
//        WeekViewEvent event = new WeekViewEvent(1, getResources().getText(R.string.java).toString(), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_01));
//        events.add(event);

        for(int i = 0; i < length; i++) {
            ClassDbHelper.ClassData data = classDbHelper.queryOneRow(i);
            String date = data.getDate();
            String time = data.getTiming();

            int day_of_month = Integer.valueOf(date.substring(0, 2));
            int start_hour = Integer.valueOf(time.substring(0,1));

            String name = data.getName();

            WeekViewEvent event = new EventBuilder.Builder().set_month(newMonth-1).set_year(newYear).set_day_of_month(day_of_month).set_start_hour(start_hour).set_end_hour(start_hour+2).set_id(i).set_name(name).build();
            event.setColor(getResources().getColor(R.color.event_color_01));
            events.add(event);
        }


//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.DAY_OF_MONTH, 6);
//        startTime.set(Calendar.HOUR_OF_DAY, 9);
//        startTime.set(Calendar.MINUTE, 30);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.set(Calendar.HOUR_OF_DAY, 4);
//        endTime.set(Calendar.MINUTE, 30);
//        endTime.set(Calendar.MONTH, newMonth-1);
//        event = new WeekViewEvent(2, getResources().getText(R.string.compstruc).toString(), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_02));
//        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.HOUR_OF_DAY, 5);
//        startTime.set(Calendar.MINUTE, 30);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.add(Calendar.HOUR_OF_DAY, 2);
//        endTime.set(Calendar.MONTH, newMonth-1);
//        event = new WeekViewEvent(3, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_02));
//        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.HOUR_OF_DAY, 5);
//        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.MONTH, newMonth - 1);
//        startTime.set(Calendar.YEAR, newYear);
//        startTime.add(Calendar.DATE, 2);
//        endTime = (Calendar) startTime.clone();
//        endTime.add(Calendar.HOUR_OF_DAY, 3);
//        endTime.set(Calendar.MONTH, newMonth - 1);
//        event = new WeekViewEvent(4, getResources().getText(R.string.algo).toString(), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_04));
//        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.DAY_OF_MONTH, 14);
//        startTime.set(Calendar.HOUR_OF_DAY, 3);
//        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.add(Calendar.HOUR_OF_DAY, 3);
//        event = new WeekViewEvent(5, getResources().getText(R.string.algo).toString(), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_04));
//        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.DAY_OF_MONTH, 1);
//        startTime.set(Calendar.HOUR_OF_DAY, 3);
//        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.add(Calendar.HOUR_OF_DAY, 3);
//        event = new WeekViewEvent(5, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_01));
//        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.DAY_OF_MONTH, startTime.getActualMaximum(Calendar.DAY_OF_MONTH));
//        startTime.set(Calendar.HOUR_OF_DAY, 15);
//        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.add(Calendar.HOUR_OF_DAY, 3);
//        event = new WeekViewEvent(5, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_02));
//        events.add(event);
//
//        //AllDay event
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.HOUR_OF_DAY, 0);
//        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.add(Calendar.HOUR_OF_DAY, 23);
//        event = new WeekViewEvent(7, getEventTitle(startTime),null, startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_04));
//        events.add(event);
//        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.DAY_OF_MONTH, 8);
//        startTime.set(Calendar.HOUR_OF_DAY, 2);
//        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.set(Calendar.DAY_OF_MONTH, 10);
//        endTime.set(Calendar.HOUR_OF_DAY, 23);
//        event = new WeekViewEvent(8, getEventTitle(startTime),null, startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_03));
//        events.add(event);
//
//        // All day event until 00:00 next day
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.DAY_OF_MONTH, 10);
//        startTime.set(Calendar.HOUR_OF_DAY, 0);
//        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.SECOND, 0);
//        startTime.set(Calendar.MILLISECOND, 0);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.set(Calendar.DAY_OF_MONTH, 11);
//        event = new WeekViewEvent(8, getEventTitle(startTime), null, startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_01));
//        events.add(event);

        return events;
    }

}
