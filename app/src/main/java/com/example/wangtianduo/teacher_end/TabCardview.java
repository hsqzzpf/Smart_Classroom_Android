package com.example.wangtianduo.teacher_end;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class TabCardview extends AppCompatActivity {

    ClassDbHelper ClassDbHelper;
    ClassDbHelper.ClassData classData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_detail);
        Log.i("ASDF", "TabCardView");
        Intent intent = getIntent();
        if(intent!=null){
            String position = intent.getStringExtra("position");
            TextView sub_classname = findViewById(R.id.sub_ClassName);
            sub_classname.setText(position);
//            classData = ClassDbHelper.queryOneRow(Integer.valueOf(position));
        }

    }
//    TextView sub_Classname = findViewById(R.id.sub_ClassName);
//    TextView sub_Venue = findViewById(R.id.sub_ClassVenue);
//    TextView sub_Time = findViewById(R.id.sub_Time);
//    TextView sub_Status = findViewById(R.id.sub_Status);
//    TextView sub_CountDown = findViewById(R.id.sub_CountDown);
//
//    sub_Classname.setText((classData.getName()+classData.getSession()));
//    sub_Venue.setText((classData.getVenue()));
//    sub_Time.setText(classData.getDate()+classData.getTiming());

}
