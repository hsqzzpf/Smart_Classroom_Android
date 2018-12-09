package com.example.wangtianduo.teacher_end.fragment_module;

import android.support.v4.app.Fragment;

import com.example.wangtianduo.teacher_end.R;
import com.example.wangtianduo.teacher_end.fragment_module.ClassFragment;
import com.example.wangtianduo.teacher_end.fragment_module.HomeFragment;
import com.example.wangtianduo.teacher_end.fragment_module.ScheduleFragment;
import com.example.wangtianduo.teacher_end.fragment_module.SettingFragment;

public class DataGenerator {

        public static final int []mTabRes = new int[]{R.drawable.home_unsel,
                R.drawable.class_unsel,R.drawable.schedule_unsel,
                R.drawable.setting_unsel};
        public static final int []mTabResPressed = new int[]{R.drawable.home_dosel,
                R.drawable.class_dosel,R.drawable.schedule_dosel,
                R.drawable.setting_dosel};

        public static Fragment[] getFragments(String from){
            Fragment fragments[] = new Fragment[4];
            fragments[0] = HomeFragment.newInstance(from);
            fragments[1] = ClassFragment.newInstance(from);
            fragments[2] = ScheduleFragment.newInstance(from);
            fragments[3] = SettingFragment.newInstance(from);
            return fragments;
        }

}

