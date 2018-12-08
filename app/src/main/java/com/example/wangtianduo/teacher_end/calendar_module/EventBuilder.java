package com.example.wangtianduo.teacher_end.calendar_module;

import com.alamkanak.weekview.WeekViewEvent;

import java.util.Calendar;

public class EventBuilder extends WeekViewEvent {

    EventBuilder(Builder builder) {
        super(builder.event_id, builder.event_name, builder.startTime, builder.endTime);

    }

    static class Builder {
        private int year;
        private int month;
        private int day_of_month;
        private int start_hour;
        private int end_hour;
        private int minute;
        private String event_name;
        private int event_color;
        private int event_id;
        private Calendar startTime;
        private Calendar endTime;

        Builder(){}

        public Builder set_year(int year) {
            this.year = year;
            return this;
        }

        public Builder set_month(int month) {
            this.month = month;
            return this;
        }

        public Builder set_day_of_month(int day) {
            this.day_of_month = day;
            return this;
        }

        public Builder set_start_hour(int start) {
            this.start_hour = start;
            return this;
        }

        public Builder set_end_hour(int end) {
            this.end_hour = end;
            return this;
        }

        public Builder set_minute(int minute) {
            this.minute = minute;
            return this;
        }

        public Builder set_name(String name) {
            this.event_name = name;
            return this;
        }

        public Builder set_color(int color) {
            this.event_color = color;
            return this;
        }

        public Builder set_id(int id) {
            this.event_id = id;
            return this;
        }
        public EventBuilder build() {
            this.startTime = Calendar.getInstance();
            startTime.set(Calendar.MONTH, this.month);
            startTime.set(Calendar.DAY_OF_MONTH, this.day_of_month);
            startTime.set(Calendar.HOUR_OF_DAY, this.start_hour);
            startTime.set(Calendar.MINUTE, this.minute);
            startTime.set(Calendar.YEAR, this.year);
            this.endTime = (Calendar) startTime.clone();
            endTime.add(Calendar.HOUR, this.end_hour);
            endTime.set(Calendar.MONTH, this.month);

            return new EventBuilder(this);
        }



    }


}
