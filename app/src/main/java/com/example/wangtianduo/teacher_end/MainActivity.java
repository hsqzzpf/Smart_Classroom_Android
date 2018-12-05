package com.example.wangtianduo.teacher_end;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private Fragment[] mFragmensts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_tab_layout_ac);
        mFragmensts = DataGenerator.getFragments("TabLayout Tab");

        initView();
    }

    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.bottom_tab_layout);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                onTabItemSelected(tab.getPosition());

                //改变Tab 状态
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
}
