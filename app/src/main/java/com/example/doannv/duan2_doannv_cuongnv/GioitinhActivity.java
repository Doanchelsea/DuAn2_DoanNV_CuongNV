package com.example.doannv.duan2_doannv_cuongnv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class GioitinhActivity extends AppCompatActivity {
    private Toolbar tbGioitinh;
    private RadioButton radioNam;
    private RadioButton radioNu;
    private RadioButton radioTuyChinh;
    private Button btnNgaysinh;
    String nam = "Nam";
    String nu = "Nữ";
    String tuyChinh = "Giới tính thứ 3";
    String hoten,email,diachi,sdt,year,thang,ngay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gioitinh);
        AnhXa();
        ThongTin();
        EventButton();
        ActionToolbar();
    }

    private void ThongTin() {
        Intent intent = getIntent();
        hoten =   intent.getStringExtra("HOTEN");
        email =   intent.getStringExtra("EMAIL");
        sdt   =   intent.getStringExtra("SDT");
        diachi =  intent.getStringExtra("DIACHI");
        year =   intent.getStringExtra("NAM");
        thang   =   intent.getStringExtra("THANG");
        ngay =  intent.getStringExtra("NGAY");
    }

    private void ActionToolbar() {
        setSupportActionBar(tbGioitinh);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbGioitinh.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void EventButton() {
        btnNgaysinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioNam.isChecked()){
                    Intent intent = new Intent(GioitinhActivity.this,MatKhauActivity.class);
                    intent.putExtra("HOTEN",hoten);
                    intent.putExtra("EMAIL",email);
                    intent.putExtra("SDT",sdt);
                    intent.putExtra("DIACHI",diachi);
                    intent.putExtra("NAM",""+year);
                    intent.putExtra("THANG",""+thang);
                    intent.putExtra("NGAY",""+ngay);
                    intent.putExtra("GIOITINH",nam);
                    startActivity(intent);
                }else if (radioNu.isChecked()){
                    Intent intent = new Intent(GioitinhActivity.this,MatKhauActivity.class);
                    intent.putExtra("HOTEN",hoten);
                    intent.putExtra("EMAIL",email);
                    intent.putExtra("SDT",sdt);
                    intent.putExtra("DIACHI",diachi);
                    intent.putExtra("NAM",""+year);
                    intent.putExtra("THANG",""+thang);
                    intent.putExtra("NGAY",""+ngay);
                    intent.putExtra("GIOITINH",nu);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(GioitinhActivity.this,MatKhauActivity.class);
                    intent.putExtra("HOTEN",hoten);
                    intent.putExtra("EMAIL",email);
                    intent.putExtra("SDT",sdt);
                    intent.putExtra("DIACHI",diachi);
                    intent.putExtra("NAM",""+year);
                    intent.putExtra("THANG",""+thang);
                    intent.putExtra("NGAY",""+ngay);
                    intent.putExtra("GIOITINH",tuyChinh);
                    startActivity(intent);
                }
            }
        });
    }

    private void AnhXa() {
        tbGioitinh = (Toolbar) findViewById(R.id.tbGioitinh);
        radioNam = (RadioButton) findViewById(R.id.radioNam);
        radioNu = (RadioButton) findViewById(R.id.radioNu);
        radioTuyChinh = (RadioButton) findViewById(R.id.radioTuyChinh);
        btnNgaysinh = (Button) findViewById(R.id.btnNgaysinh);
    }
}
