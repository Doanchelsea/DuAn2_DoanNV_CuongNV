package com.example.doannv.duan2_doannv_cuongnv.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.doannv.duan2_doannv_cuongnv.R;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

public class MenuFragment extends Fragment {
    ImageView imgdowwn;
    LinearLayout linearLayout;
    ExpandableRelativeLayout relativeLayout;
    int a = 2;


    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.menu_fragment,container,false);
        linearLayout = view.findViewById(R.id.linerlay);
        relativeLayout = view.findViewById(R.id.expReLayout);
        imgdowwn = view.findViewById(R.id.imgdowwn);
        relativeLayout.collapse();
        OnClick();
        return view;
    }

    private void OnClick() {
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (a == 2){
                    animateExpand();
                    relativeLayout.toggle();
                    a = 1;
                    return;
                }
                if (a == 1){
                    animateCollapse();
                    relativeLayout.toggle();
                    a = 2;
                    return;
                }
            }
        });
    }

    private void animateExpand() {
        RotateAnimation rotate =
                new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        imgdowwn.startAnimation(rotate);
    }

    private void animateCollapse() {
        RotateAnimation rotate =
                    new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        imgdowwn.startAnimation(rotate);
    }
}
