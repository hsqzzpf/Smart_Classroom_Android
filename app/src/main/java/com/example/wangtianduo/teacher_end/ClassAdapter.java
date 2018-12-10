package com.example.wangtianduo.teacher_end;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wangtianduo.teacher_end.sqlite_module.ClassDbHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;


public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder>{

    LayoutInflater mInflater;
    Context context;
    com.example.wangtianduo.teacher_end.sqlite_module.ClassDbHelper ClassDbHelper;
    public ArrayList<String> uncheckedStudent = new ArrayList<>();

    private ClassAdapter.OnItemClickListener onItemClickListener;
    //Click Interface
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
    //Click Listener
    public void setOnItemClickListener(ClassAdapter.OnItemClickListener listener){
        this.onItemClickListener = listener;
    }


    public ClassAdapter(Context context, ClassDbHelper ClassDbHelper) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.ClassDbHelper = ClassDbHelper;
    }


    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.layout, viewGroup, false);
        return new ClassViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull final ClassViewHolder classViewHolder, int i) {
        ClassDbHelper.ClassData classData = ClassDbHelper.queryOneRow(i);

        uncheckedStudent = processUncheckedStudent(classData.getStatus());
        int checkedStudentNO = Integer.valueOf(classData.getNumber()) - uncheckedStudent.size();

        classViewHolder.textViewClassName.setText((classData.getName() + " - "+ classData.getSession()));
        classViewHolder.textViewTime.setText(classData.getDate() + " " + classData.getTiming());
        classViewHolder.textViewVenue.setText(classData.getVenue());
        classViewHolder.textViewSignInStatus.setText(String.valueOf(checkedStudentNO)+" / "+classData.getNumber());
        classViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null){
                    int pos = classViewHolder.getLayoutPosition();
                    onItemClickListener.onItemClick(classViewHolder.itemView,pos);
                }
            }
        });
    }


    @Override
    public int getItemCount() {

        return (int) ClassDbHelper.queryNumRows();
    }


    class ClassViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewClassName;
        public TextView textViewTime;
        public TextView textViewVenue;
        public TextView textViewSignInStatus;


        public ClassViewHolder(View view){
            super(view);
            textViewClassName = view.findViewById(R.id.cardViewTextName);
            textViewTime = view.findViewById(R.id.cardViewTime);
            textViewVenue = view.findViewById(R.id.cardViewVenue);
            textViewSignInStatus = view.findViewById(R.id.cardViewSignInStatus);
        }

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
