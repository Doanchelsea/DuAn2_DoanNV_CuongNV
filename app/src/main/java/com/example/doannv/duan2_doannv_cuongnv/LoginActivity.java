package com.example.doannv.duan2_doannv_cuongnv;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.doannv.duan2_doannv_cuongnv.unti.PrefManager;
import com.example.doannv.duan2_doannv_cuongnv.unti.Server;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {
    RelativeLayout menu_progressbar2;
    private TextInputLayout TiTaiKhoan;
    private TextInputEditText edTaiKhoan;
    private TextInputLayout TiMatKhau;
    private TextInputEditText edMatKhau;
    private Button btnDangNhap;
    private Button btnDangNhapRong;
    private Button btnTaoTK;
    private PrefManager prefManager;
    SharedPreferences preferences;
    String sdt = "";
    String mk = "";
    String anhdd = "";
    String hoten = "";
    String gmail = "";
    String anhbia = "";
    String gioitinh = "";
    String diachi = "";
    int ngaysn = 0;
    int thangsn = 0;
    int namsn = 0;
    int Id = 0;
    private final static int REQUEST_CODE = 999;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AnhXa();
        Keyhash();
        ShowPass();
        ShowDN();
        EventButton();
        LuuTK();
    }

    private void LuuTK() {
        if (!prefManager.isFirstTimeLaunch()){
            lauHome();
            finish();
        }
    }
    private void Keyhash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.example.doannv.duan2_doannv_cuongnv",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures){
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KEYHASH: ",Base64.encodeToString(md.digest(),Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    private void lauHome() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.duongdandn, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                final String username2 = preferences.getString("usernameok", "");
                final String password2 = preferences.getString("passwordok", "");
                if (response != null){
                    for (int i=0; i<response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            Id = jsonObject.getInt("id");
                            ngaysn = jsonObject.getInt("ngaysn");
                            thangsn = jsonObject.getInt("thangsn");
                            namsn = jsonObject.getInt("namsn");
                            sdt = jsonObject.getString("sodienthoai");
                            mk = jsonObject.getString("matkhau");
                            anhdd = jsonObject.getString("anhdaidien");
                            hoten = jsonObject.getString("hoten");
                            gmail = jsonObject.getString("gmailcn");
                            anhbia = jsonObject.getString("anhbia");
                            gioitinh = jsonObject.getString("gioitinh");
                            diachi = jsonObject.getString("diachi");
                            if (username2.equals(sdt) && password2.equals(mk)) {
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                intent.putExtra("OK","ok1 :"+gioitinh);
                                startActivity(intent);
                                return;
                            }
                            if (!username2.equals(sdt) && !password2.equals(mk)){
                                prefManager.setFirstTimeLaunch(true);
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }else {
                    prefManager.setFirstTimeLaunch(true);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        intent.putExtra("OK","ok2 :"+sdt);
        startActivity(intent);
    }

    private void EventButton() {
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String tk = edTaiKhoan.getText().toString().trim();
                final String mk1 = edMatKhau.getText().toString().trim();
                menu_progressbar2.setVisibility(View.VISIBLE);
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.duongdandn, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response != null){

                            for (int i=0; i<response.length(); i++){
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    Id = jsonObject.getInt("id");
                                    ngaysn = jsonObject.getInt("ngaysn");
                                    thangsn = jsonObject.getInt("thangsn");
                                    namsn = jsonObject.getInt("namsn");
                                    sdt = jsonObject.getString("sodienthoai");
                                    mk = jsonObject.getString("matkhau");
                                    anhdd = jsonObject.getString("anhdaidien");
                                    hoten = jsonObject.getString("hoten");
                                    gmail = jsonObject.getString("gmailcn");
                                    anhbia = jsonObject.getString("anhbia");
                                    gioitinh = jsonObject.getString("gioitinh");
                                    diachi = jsonObject.getString("diachi");

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                if (tk.equals(sdt) && mk1.equals(mk)){
                                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                    intent.putExtra("OK",sdt);
                                    startActivity(intent);
                                    preferences.edit().putString("usernameok", sdt).commit();
                                    preferences.edit().putString("passwordok", mk).commit();
                                    menu_progressbar2.setVisibility(View.GONE);
                                    prefManager.setFirstTimeLaunch(false);
                                    return;
                                }else {
                                    menu_progressbar2.setVisibility(View.GONE);
                                    final AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                    builder.setTitle("Đăng Nhập Thất Bại");
                                    builder.setIcon(R.drawable.thongbao);
                                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            builder.setCancelable(true);
                                        }
                                    });
                                    builder.show();
                                    return;
                                }
                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                requestQueue.add(jsonArrayRequest);
            }
        });

        btnTaoTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,AccountKitActivity.class);
                AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                        new AccountKitConfiguration.AccountKitConfigurationBuilder(LoginType.PHONE,
                                AccountKitActivity.ResponseType.TOKEN);
                intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,configurationBuilder.build());
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE) {
            AccountKitLoginResult result = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            if (result.getError() != null){
                Toast.makeText(this, ""+result.getError().getErrorType().getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }else if (result.wasCancelled()){
                return;
            }else {
                startActivity(new Intent(LoginActivity.this,DangKyActivity.class));
            }
        }
    }
    private void ShowDN() {
        edTaiKhoan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(final CharSequence s, int start, int before, int count) {

                edMatKhau.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence d, int start, int before, int count) {
                        if (d.length() == 0 && s.length() !=0){
                            btnDangNhap.setVisibility(View.GONE);
                            btnDangNhapRong.setVisibility(View.VISIBLE);
                        }else if (s.length() == 0 && d.length() !=0){
                            btnDangNhap.setVisibility(View.GONE);
                            btnDangNhapRong.setVisibility(View.VISIBLE);
                        }else if (s.length() != 0 && d.length() !=0){
                            btnDangNhapRong.setVisibility(View.GONE);
                            btnDangNhap.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void ShowPass() {
        edMatKhau.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(final CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    TiMatKhau.setPasswordVisibilityToggleEnabled(false);
                } else {
                    TiMatKhau.setPasswordVisibilityToggleEnabled(true);
                }
                edTaiKhoan.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence d, int start, int before, int count) {
                        if (d.length() == 0 && s.length() !=0){
                            btnDangNhap.setVisibility(View.GONE);
                            btnDangNhapRong.setVisibility(View.VISIBLE);
                        }else if (s.length() == 0 && d.length() !=0){
                            btnDangNhap.setVisibility(View.GONE);
                            btnDangNhapRong.setVisibility(View.VISIBLE);
                        }else if (s.length() != 0 && d.length() !=0){
                            btnDangNhapRong.setVisibility(View.GONE);
                            btnDangNhap.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void AnhXa() {
        TiTaiKhoan = (TextInputLayout) findViewById(R.id.TiTaiKhoan);
        edTaiKhoan = (TextInputEditText) findViewById(R.id.edTaiKhoan);
        TiMatKhau = (TextInputLayout) findViewById(R.id.TiMatKhau);
        edMatKhau = (TextInputEditText) findViewById(R.id.edMatKhau);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        btnDangNhapRong = (Button) findViewById(R.id.btnDangNhapRong);
        btnTaoTK = (Button) findViewById(R.id.btnTaoTK);
        menu_progressbar2 = findViewById(R.id.menu_progressbar2);
        prefManager = new PrefManager(this);
        preferences = getSharedPreferences("Account", Context.MODE_PRIVATE);
    }
}
