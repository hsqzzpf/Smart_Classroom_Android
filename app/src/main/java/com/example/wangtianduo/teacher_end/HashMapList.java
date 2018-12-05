package com.example.wangtianduo.teacher_end;

import java.util.HashMap;

public class HashMapList {
    public static HashMap<String,Integer> nameListHashMap = new HashMap<>();

    public static HashMap initHashMap(){
        if(nameListHashMap.size()==0){
            nameListHashMap.put("LiYanzhang",R.drawable.liyanzhang);
            nameListHashMap.put("WangTianduo",R.drawable.whitebbird);
            nameListHashMap.put("TangXiaoyue",R.drawable.tangxiaoyue);
            nameListHashMap.put("liyanzhang",R.drawable.liyanzhang);
            nameListHashMap.put("whiteBbird",R.drawable.whitebbird);
            nameListHashMap.put("visitor",R.drawable.visitor);
        }
        return nameListHashMap;
    }

}
