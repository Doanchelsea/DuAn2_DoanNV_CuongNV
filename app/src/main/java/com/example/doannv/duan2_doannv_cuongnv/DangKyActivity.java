package com.example.doannv.duan2_doannv_cuongnv;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doannv.duan2_doannv_cuongnv.unti.Server;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.PhoneNumber;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DangKyActivity extends AppCompatActivity {
    private Toolbar tbthongtincanhan;
    private TextInputEditText edHoTen;
    private TextInputEditText edMail;
    private TextInputEditText edDiaChi;
    PhoneNumber SDT;
    String sdt;
    private Button btnthongtincanhan;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        AnhXa();
        ActionToolbar();
        btnthongtincanhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String validate = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}"+
                      "\\@" +
                      "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}"+
                      "(" +
                      "\\." +"[a-zA-Z-0-9][a-zA-Z0-9\\-]{0,25}"+
                      ")+";
                final String email=  edMail.getText().toString().trim();
                final String hoten=  edHoTen.getText().toString().trim();
                final String diachi=  edDiaChi.getText().toString().trim();
                Matcher matcher  = Pattern.compile(validate).matcher(email);

                if (hoten.equals("")){
                    edHoTen.setError("yêu cầu nhập dữ liệu");
                }else if (!matcher.matches()){
                    edMail.setError("chưa đúng định dạng");
                }else if (diachi.equals("")){
                    edDiaChi.setError("yêu cầu nhập dữ liệu");
                }else {
                    sdt = String.valueOf(SDT);
           Intent intent = new Intent(DangKyActivity.this,NgaysinhActivity.class);
           intent.putExtra("HOTEN",hoten);
           intent.putExtra("EMAIL",email);
           intent.putExtra("SDT",sdt);
           intent.putExtra("DIACHI",diachi);
           startActivity(intent);
                }

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
            @Override
            public void onSuccess(Account account) {
                 SDT = account.getPhoneNumber();
            }

            @Override
            public void onError(AccountKitError accountKitError) {

            }
        });
    }

    private void ActionToolbar() {
        setSupportActionBar(tbthongtincanhan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbthongtincanhan.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void AnhXa() {
        tbthongtincanhan = (Toolbar) findViewById(R.id.tbthongtincanhan);
        edHoTen = (TextInputEditText) findViewById(R.id.edHoTen);
        edMail = (TextInputEditText) findViewById(R.id.edMail);
        edDiaChi = (TextInputEditText) findViewById(R.id.edDiaChi);
        btnthongtincanhan = (Button) findViewById(R.id.btnthongtincanhan);
    }
}
