package com.example.wangtianduo.teacher_end.fragment_module;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wangtianduo.teacher_end.HomeClassAdapter;
import com.example.wangtianduo.teacher_end.R;
import com.example.wangtianduo.teacher_end.face_detection.UploadFaceSet;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class HomeFragment extends Fragment {
    private String mFrom;
    private RecyclerView mHomeCheckedStudent;
    private ArrayList<String> checkedStudentList = new ArrayList<>();
    private HomeClassAdapter mHomeClassAdapter;
    private HashMap<String, String> classinfo = new HashMap<>();
    private byte[] returnByte;
    private String strJSON;
    private ArrayList<String> testList = new ArrayList<>();
    private GridLayoutManager mGridLayoutManager;


    public static HomeFragment newInstance(String from) {
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("from", from);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
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
        View view = inflater.inflate(R.layout.home_fragment_layout, null);

        try {
            AsynUpload asyn = new AsynUpload();
            asyn.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        checkedStudentList = processJSON(strJSON);

        testArrayList();

        TextView homeClassName = view.findViewById(R.id.home_ClassName);
        TextView homeClassVenue = view.findViewById(R.id.home_ClassVenue);
        TextView homeClassTime = view.findViewById(R.id.home_Time);
        TextView homeCheckedNumber = view.findViewById(R.id.home_checked_number);

        mGridLayoutManager = new GridLayoutManager(getContext(), 4);
        mHomeCheckedStudent = (RecyclerView) view.findViewById(R.id.home_checked_student);
        mHomeClassAdapter = new HomeClassAdapter(getContext(), checkedStudentList);
        mHomeCheckedStudent.setLayoutManager(mGridLayoutManager);
        mHomeCheckedStudent.setAdapter(mHomeClassAdapter);

        return view;
    }

    class AsynUpload extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... user_id) {
            String str = "";

            try {
                str = UploadFaceSet.getCheckedStudent();
                strJSON = str;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    public ArrayList<String> processJSON(String jsonStr) {
        ArrayList<String> nameList = new ArrayList<>();
        try {
            JSONObject json = new JSONObject(jsonStr);
            Iterator iterator = json.keys();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                if (json.getString(key).equals("True") || json.getString(key).equals("TRUE")) {
                    nameList.add(key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nameList;
    }

    public void testArrayList(){
        if(testList.size()==0){
            testList.add("LiYanzhang");
            testList.add("TangXiaoyue");
            testList.add("WangTianduo");
            testList.add("visitor");
            testList.add("visitor");
            testList.add("visitor");
            testList.add("visitor");
            testList.add("visitor");
            testList.add("visitor");
        }
    }

}
