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
        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
        HashMap<String, Integer> class_color = new HashMap<>();

        //TODO add key-value pair that specify classname and color

        ClassDbHelper classDbHelper = ClassDbHelper.createClassDbHelper(this);
        int length = (int) classDbHelper.queryNumRows();

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

        return events;
    }

}
