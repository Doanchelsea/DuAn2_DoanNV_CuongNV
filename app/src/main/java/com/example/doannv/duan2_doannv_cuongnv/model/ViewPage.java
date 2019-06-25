package com.example.doannv.duan2_doannv_cuongnv.model;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.doannv.duan2_doannv_cuongnv.fragment.BanbeFragment;
import com.example.doannv.duan2_doannv_cuongnv.fragment.BangtinFragment;
import com.example.doannv.duan2_doannv_cuongnv.fragment.CaNhanFragment;
import com.example.doannv.duan2_doannv_cuongnv.fragment.MenuFragment;
import com.example.doannv.duan2_doannv_cuongnv.fragment.ThongbaoFragment;

public class ViewPage extends FragmentPagerAdapter {

    public ViewPage(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                BangtinFragment bangtinFragment = new BangtinFragment();
                return bangtinFragment;
            case 1:
                BanbeFragment banbeFragment = new BanbeFragment();
                return banbeFragment;
            case 2:
                CaNhanFragment caNhanFragment = new CaNhanFragment();
                return caNhanFragment;
            case 3:
                ThongbaoFragment thongbaoFragment = new ThongbaoFragment();
                return thongbaoFragment;
            case 4:
                MenuFragment menuFragment = new MenuFragment();
                return menuFragment;
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
