package com.example.wangtianduo.teacher_end;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.wangtianduo.teacher_end.sqlite_module.ClassDbHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class TabCardviewActivity extends AppCompatActivity {

    com.example.wangtianduo.teacher_end.sqlite_module.ClassDbHelper ClassDbHelper;
    ClassDbHelper.ClassData classData;
    String position;
    Integer pos;

    private RecyclerView cardCheckedStudent;
    private ArrayList<String> unCheckedStudentList = new ArrayList<>();
    private HomeClassAdapter mCardClassAdapter;
    private GridLayoutManager mGridLayoutManager;
    private Integer checkedStudentNO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_detail);

        Intent intent = getIntent();
        if(intent!=null){
            position = intent.getStringExtra("position");
        }
        //testList();
        pos = Integer.valueOf(position);

        ClassDbHelper = ClassDbHelper.createClassDbHelper(TabCardviewActivity.this);
        ClassDbHelper.ClassData classData = ClassDbHelper.queryOneRow(pos);

        unCheckedStudentList = processUncheckedStudent(classData.getStatus());
        checkedStudentNO = Integer.valueOf(classData.getNumber()) - unCheckedStudentList.size();

        TextView sub_ClassName = findViewById(R.id.sub_ClassName);
        TextView sub_ClassVenue = findViewById(R.id.sub_ClassVenue);
        TextView sub_ClassTime = findViewById(R.id.sub_Time);
        TextView sub_SignInNO = findViewById(R.id.sub_AttendanceNO);

        sub_ClassName.setText(classData.getName()+classData.getSession());
        sub_ClassVenue.setText(classData.getVenue());
        sub_ClassTime.setText(classData.getDate()+classData.getTiming());
        sub_SignInNO.setText(String.valueOf(checkedStudentNO)+" / "+classData.getNumber());


        mGridLayoutManager = new GridLayoutManager(this, 4);
        cardCheckedStudent = (RecyclerView)findViewById(R.id.sub_unchecked_student);
        mCardClassAdapter = new HomeClassAdapter(this,unCheckedStudentList);
        cardCheckedStudent.setAdapter(mCardClassAdapter);
        cardCheckedStudent.setLayoutManager(mGridLayoutManager);
    }

    public void testList(){
        unCheckedStudentList.add("LiYanzhang");
        unCheckedStudentList.add("LiYanzhang");
        unCheckedStudentList.add("LiYanzhang");
        unCheckedStudentList.add("LiYanzhang");
        unCheckedStudentList.add("LiYanzhang");
        unCheckedStudentList.add("LiYanzhang");
        unCheckedStudentList.add("LiYanzhang");
    }

    public ArrayList<String> processUncheckedStudent(String studentStatus){
        ArrayList<String> uncheckedStudentList = new ArrayList<>();
        try {
            JSONObject json = new JSONObject(studentStatus);
            Iterator iterator = json.keys();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                if (json.getString(key).equals("False")) {
                    uncheckedStudentList.add(key);
                    Log.i("ASDF", "namelist: " + uncheckedStudentList.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uncheckedStudentList;
    }
}
