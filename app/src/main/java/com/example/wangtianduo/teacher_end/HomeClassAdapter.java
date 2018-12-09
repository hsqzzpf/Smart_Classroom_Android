package com.example.wangtianduo.teacher_end;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeClassAdapter extends RecyclerView.Adapter<HomeClassAdapter.HomeViewHolder>{

    //LayoutInflater mInflater;
    private Context context;
    public static ArrayList<String> checkedStudentList;
    private ArrayList<String> studentList;
    private HashMap<String,Integer> nameListHashMap;


    public HomeClassAdapter(Context context, ArrayList<String> list) {
        //ArrayList list 是状态为true的学生的名单
        //mInflater = LayoutInflater.from(context);
        this.context = context;
        this.checkedStudentList = list;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = View.inflate(context,R.layout.checked_student_view, null);
        return new HomeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeViewHolder homeViewHolder, int i) {
        nameListHashMap = HashMapList.initHashMap();

        homeViewHolder.checkedStudentName.setText(checkedStudentList.get(i));
        if(nameListHashMap.get(checkedStudentList.get(i))!=null){
            homeViewHolder.checkedStudentPhoto.setImageResource(nameListHashMap.get(checkedStudentList.get(i)));
        } else{
            homeViewHolder.checkedStudentPhoto.setImageResource(nameListHashMap.get("visitor"));
        }

    }

    @Override
    public int getItemCount() {
        return checkedStudentList.size();
    }

    class HomeViewHolder extends RecyclerView.ViewHolder{

        TextView checkedStudentName;
        ImageView checkedStudentPhoto;

        public HomeViewHolder(View view){
            super(view);
            checkedStudentName = view.findViewById(R.id.checked_student_name);
            checkedStudentPhoto = view.findViewById(R.id.checked_student_photo);
        }

    }


}

