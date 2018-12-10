package com.example.wangtianduo.teacher_end;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.wangtianduo.teacher_end.fragment_module.DataGenerator;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private Fragment[] mFragmensts;

    private Timer timer = new Timer();
    private Integer classDay=8,classHour=11,classMin=50,classSec=00;
    private Integer resHour,resMin,resSec;
    private Integer year,month,day,hours,minute,second;
    private TextView countDown,signInStatus;
    private Integer remainSeconds,resSeconds,remainMinutes,resMinutes,remainHours,resHours;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_tab_layout_ac);
        mFragmensts = DataGenerator.getFragments("TabLayout Tab");


        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getDateAndTime(null);
                remainSeconds = calResTime(hours,minute,second);
                resSeconds = remainSeconds%60;
                remainMinutes = (remainSeconds - resSeconds)/60;
                resMinutes = remainMinutes%60;
                remainHours = (remainMinutes - resMinutes)/60;
                resHours = remainHours;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //setContentView(mTabLayout);
                        countDown = (TextView)findViewById(R.id.home_CountDown);
                        signInStatus = (TextView)findViewById(R.id.home_Status);
                        if(countDown!=null){
                            if(remainSeconds==0||remainSeconds<0){
                                signInStatus.setText("Stop Taking Attendance");
                                countDown.setText("00:00:00");
                            }
                            else {
                                countDown.setText(String.format("%02d",resHours)+":"+String.format("%02d",resMinutes)+":"+String.format("%02d",resSeconds));
                            }
                        }
                    }
                });
            }
        }, 0,1);

        initView();
    }

    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.bottom_tab_layout);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                onTabItemSelected(tab.getPosition());

                for (int i = 0; i < mTabLayout.getTabCount(); i++) {
                    if (i == tab.getPosition()) {
                        mTabLayout.getTabAt(i).setIcon(getResources().getDrawable(DataGenerator.mTabResPressed[i]));
                    } else {
                        mTabLayout.getTabAt(i).setIcon(getResources().getDrawable(DataGenerator.mTabRes[i]));
                    }
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        mTabLayout.addTab(mTabLayout.newTab().setIcon(getResources().getDrawable(R.drawable.home_unsel)));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(getResources().getDrawable(R.drawable.class_unsel)));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(getResources().getDrawable(R.drawable.schedule_unsel)));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(getResources().getDrawable(R.drawable.setting_unsel)));

    }

    private void onTabItemSelected(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = mFragmensts[0];
                break;
            case 1:
                fragment = mFragmensts[1];
                break;

            case 2:
                fragment = mFragmensts[2];
                break;
            case 3:
                fragment = mFragmensts[3];
                break;
        }
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.home_container, fragment).commit();
        }
    }

    public void getDateAndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date == null) {
            calendar.setTimeInMillis(System.currentTimeMillis());
        } else {
            calendar.setTime(date);
        }
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hours = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);
    }

    public Integer calResTime(int hour,int minute,int second){
        int targetTime = classSec + classMin*60 + classHour *60 *60;
        int currentTime = second + minute*60 + hour*60*60;
        int remainTime = targetTime - currentTime;
        return remainTime;
    }



}