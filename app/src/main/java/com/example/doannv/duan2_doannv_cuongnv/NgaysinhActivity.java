package com.example.doannv.duan2_doannv_cuongnv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NgaysinhActivity extends AppCompatActivity {
    private Toolbar tbNgaySinh;
    private DatePicker dateNgaysinh;
    private Button btnNgaysinh;
    int nam = 1999;
    int thang = 12;
    int ngay = 23;
    String hoten,email,diachi,sdt;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngaysinh);
        AnhXa();
        ActionToolbar();
        setupdatePicker();
        btnNgaysinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NgaysinhActivity.this, GioitinhActivity.class);
                intent.putExtra("HOTEN",hoten);
                intent.putExtra("EMAIL",email);
                intent.putExtra("SDT",sdt);
                intent.putExtra("DIACHI",diachi);
                intent.putExtra("NAM",""+nam);
                intent.putExtra("THANG",""+thang);
                intent.putExtra("NGAY",""+ngay);
                startActivity(intent);
            }
        });
    }
    private void ActionToolbar() {
        setSupportActionBar(tbNgaySinh);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbNgaySinh.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void setupdatePicker() {

         dateNgaysinh.init(nam, 11, ngay, new DatePicker.OnDateChangedListener() {
             @Override
             public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                 nam = year;
                 thang = monthOfYear+1;
                 ngay = dayOfMonth;
             }
         });
    }

    private void AnhXa() {
        tbNgaySinh = (Toolbar) findViewById(R.id.tbNgaySinh);
        dateNgaysinh = (DatePicker) findViewById(R.id.dateNgaysinh);
        btnNgaysinh = (Button) findViewById(R.id.btnNgaysinh);
        Intent intent = getIntent();
       hoten =   intent.getStringExtra("HOTEN");
       email =   intent.getStringExtra("EMAIL");
       sdt   =   intent.getStringExtra("SDT");
       diachi =  intent.getStringExtra("DIACHI");

    }
}
