package com.example.wangtianduo.teacher_end.fragment_module;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.wangtianduo.teacher_end.calendar_module.CalendarBasicActivity;

/**
 * Created by zhouwei on 17/4/23.
 */

public class ScheduleFragment extends Fragment {

    private String mFrom;

    public static ScheduleFragment newInstance(String from){
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle bundle = new Bundle();
        bundle.putString("from",from);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            mFrom = getArguments().getString("from");
        }

        Intent intent = new Intent(getContext(), CalendarBasicActivity.class);
        startActivity(intent);
    }

}
