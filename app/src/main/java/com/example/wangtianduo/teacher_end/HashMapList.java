package com.example.wangtianduo.teacher_end;

import java.util.HashMap;

public class HashMapList {
    public static HashMap<String,Integer> nameListHashMap = new HashMap<>();

    public static HashMap initHashMap(){
        if(nameListHashMap.size()==0){
            nameListHashMap.put("WangTianduo",R.drawable.wangtianduo);
            nameListHashMap.put("TangXiaoyue",R.drawable.tangxiaoyue);
            nameListHashMap.put("LiYanzhang",R.drawable.liyanzhang);
            nameListHashMap.put("visitor",R.drawable.visitor);
            nameListHashMap.put("student1",R.drawable.student1);
            nameListHashMap.put("student2",R.drawable.student2);
            nameListHashMap.put("student3",R.drawable.student3);
            nameListHashMap.put("student4",R.drawable.student4);
            nameListHashMap.put("student5",R.drawable.student5);
            nameListHashMap.put("student6",R.drawable.student6);
        }
        return nameListHashMap;
    }

}
