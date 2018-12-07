package com.example.wangtianduo.teacher_end.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wangtianduo.teacher_end.HomeClassAdapter;
import com.example.wangtianduo.teacher_end.R;
import com.example.wangtianduo.teacher_end.face_detect.UploadFaceSet;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class HomeFragment extends Fragment {
    private String mFrom;
    public RecyclerView mHomeCheckedStudent;
    private ArrayList<String> checkedStudentList = new ArrayList<>();
    private HomeClassAdapter mHomeClassAdapter;
    private HashMap<String,String> classinfo = new HashMap<>();
    private byte[] returnByte;
    private String strJSON;

    public static HomeFragment newInstance(String from){
        HomeFragment fragment = new HomeFragment();
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

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment_layout,null);
        try{
            Log.i("ASDF","in home, before upload class");
            AsynUpload asyn = new AsynUpload();
            asyn.execute();
            //String a = UploadFaceSet.upload(null,"111");
        }catch (Exception e){
            e.printStackTrace();
        }
        checkedStudentList = processJSON(strJSON);

        TextView homeClassName = view.findViewById(R.id.home_ClassName);
        TextView homeClassVenue = view.findViewById(R.id.home_ClassVenue);
        TextView homeClassTime = view.findViewById(R.id.home_Time);
        TextView homeClassCountDown = view.findViewById(R.id.home_CountDown);
        TextView homeCheckedNumber = view.findViewById(R.id.home_checked_number);

        mHomeCheckedStudent = (RecyclerView)view.findViewById(R.id.home_checked_student);
        mHomeClassAdapter = new HomeClassAdapter(getContext(), checkedStudentList);
        mHomeCheckedStudent.setAdapter(mHomeClassAdapter);
        mHomeCheckedStudent.setLayoutManager(new LinearLayoutManager(getContext()));

        //homeCheckedNumber.setText(checkedStudentList.size());

        return view;
    }

    class AsynUpload extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... user_id) {
            String str = "";

            try{
                 str = UploadFaceSet.getCheckedStudent();
                 strJSON = str;
            }catch (Exception e){
                e.printStackTrace();
            }
            return str;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            Log.i("ASDD", values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
//            Intent intent = new Intent(FaceDetection.this, MainActivity.class);
//            startActivity(intent);
            Log.i("ASDF","onPostExecute: "+s);
        }
    }

    public ArrayList<String> processJSON(String jsonStr){
        ArrayList<String> nameList = new ArrayList<>();
        try{
            JSONObject json = new JSONObject(jsonStr);
            Iterator iterator = json.keys();
            while(iterator.hasNext()){
                String key = (String) iterator.next();
                if(json.getString(key).equals("True")||json.getString(key).equals("TRUE")){
                    nameList.add(key);
                    Log.i("ASDF","namelist: "+nameList.toString());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.i("ASDF","ERROR of process Json");
        }
        return nameList;
    }

}
