package com.example.wangtianduo.teacher_end.fragment_module;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.wangtianduo.teacher_end.face_detection.FaceDetectGrayActivity;
import com.example.wangtianduo.teacher_end.face_detection.FaceDetectionActivity;
import com.example.wangtianduo.teacher_end.R;

/**
 * Created by zhouwei on 17/4/23.
 */

public class SettingFragment extends Fragment {

    private String mFrom;
    public static SettingFragment newInstance(String from){
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
        Button add_face_2 = (Button)view.findViewById(R.id.add_new_2);

        add_face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), FaceDetectionActivity.class);
                startActivity(intent);

            }
        });

        add_face_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),FaceDetectGrayActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }
}
