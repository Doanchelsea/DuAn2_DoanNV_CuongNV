package com.example.doannv.duan2_doannv_cuongnv;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.doannv.duan2_doannv_cuongnv.model.ViewPage;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewface);
        tabLayout = findViewById(R.id.tabface);

        PagerAdapter adapter = new ViewPage(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.home);
        tabLayout.getTabAt(1).setIcon(R.drawable.banbe);
        tabLayout.getTabAt(2).setIcon(R.drawable.canhan);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_thongbao_24dp);
        tabLayout.getTabAt(4).setIcon(R.drawable.menu);

    }
}
