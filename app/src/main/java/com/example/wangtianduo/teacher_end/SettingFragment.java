package com.example.wangtianduo.teacher_end;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by zhouwei on 17/4/23.
 */

public class SettingFragment extends Fragment {

    private String mFrom;
    static SettingFragment newInstance(String from){
        SettingFragment fragment = new SettingFragment();
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

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_page,null);
        Button add_face = (Button) view.findViewById(R.id.add_new);
        Button adjust_course = (Button) view.findViewById(R.id.adjust_curriculum);

        TextView textView = view.findViewById(R.id.returnValue);

        add_face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), FaceDetection.class);
                startActivity(intent);

            }
        });

        adjust_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CalendarBasicActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }
}
