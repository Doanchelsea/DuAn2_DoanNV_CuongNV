package com.example.doannv.duan2_doannv_cuongnv;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doannv.duan2_doannv_cuongnv.unti.Server;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class MatKhauActivity extends AppCompatActivity {
    RelativeLayout relativeLayout;
    private final static int REQUEST_CODE = 999;
    private Toolbar tbMatkhau;
    private TextInputLayout tiTaoMK;
    private TextInputEditText edTaoMK;
    private Button btnSinhnhat;
    String hoten,email,diachi,sdt,year,thang,ngay,gioitinh,matkhau;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mat_khau);
        Anhxa();
        Thongtin();
        ShowPass();
        EventButton();
    }


    private void Thongtin() {
        Intent intent = getIntent();
        hoten =   intent.getStringExtra("HOTEN");
        email =   intent.getStringExtra("EMAIL");
        sdt   =   intent.getStringExtra("SDT");
        diachi =  intent.getStringExtra("DIACHI");
        year =   intent.getStringExtra("NAM");
        thang   =   intent.getStringExtra("THANG");
        ngay =  intent.getStringExtra("NGAY");
        gioitinh = intent.getStringExtra("GIOITINH");
    }

    private void EventButton() {
            btnSinhnhat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    relativeLayout.setVisibility(View.VISIBLE);
                     matkhau = edTaoMK.getText().toString().trim();
                    if (matkhau.equals("")) {
                        edTaoMK.setError("Yêu cầu nhập dữ liệu");
                    }else {
                        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.duongdandn, new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                if (response != null){
                                    String taikhoan = "";
                                    for (int i=0; i<response.length();i++){
                                        try {
                                            JSONObject jsonObject = response.getJSONObject(i);
                                            taikhoan = jsonObject.getString("sodienthoai");

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        if (sdt.equals(taikhoan)){
                                            relativeLayout.setVisibility(View.GONE);
                                            final AlertDialog.Builder builder = new AlertDialog.Builder(MatKhauActivity.this);
                                            builder.setTitle("Số điện thoại đã sử dụng");
                                            builder.setIcon(R.drawable.thongbao);
                                            builder.setNegativeButton("Quay Lại", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent intent = new Intent(MatKhauActivity.this,LoginActivity.class);
                                                    startActivity(intent);
                                                }
                                            });
                                            builder.show();
                                            return;
                                        }
                                    }

                                }
                                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                                StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdandk, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Intent intent = new Intent(MatKhauActivity.this,LoginActivity.class);
                                        startActivity(intent);
                                        relativeLayout.setVisibility(View.GONE);
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        HashMap<String,String> hashMap = new HashMap<>();
                                        hashMap.put("sodienthoai",sdt);
                                        hashMap.put("matkhau",matkhau);
                                        hashMap.put("anhdaidien","");
                                        hashMap.put("hoten",hoten);
                                        hashMap.put("anhbia","");
                                        hashMap.put("gmailcn",email);
                                        hashMap.put("ngaysn",ngay);
                                        hashMap.put("thangsn",thang);
                                        hashMap.put("namsn",year);
                                        hashMap.put("gioitinh",gioitinh);
                                        hashMap.put("diachi",diachi);
                                        return hashMap;
                                    }
                                };
                                requestQueue.add(stringRequest);

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
                        requestQueue.add(jsonArrayRequest);
                    }
                }
            });
        }

    private void ShowPass() {
        edTaoMK.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0){
                    tiTaoMK.setPasswordVisibilityToggleEnabled(false);
                }else {
                    tiTaoMK.setPasswordVisibilityToggleEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void Anhxa() {
        tbMatkhau = (Toolbar) findViewById(R.id.tbMatkhau);
        tiTaoMK = (TextInputLayout) findViewById(R.id.tiTaoMK);
        edTaoMK = (TextInputEditText) findViewById(R.id.edTaoMK);
        btnSinhnhat = (Button) findViewById(R.id.btnSinhnhat);
        relativeLayout = findViewById(R.id.menu_progressbar);
    }
}
