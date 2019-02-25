package com.baoviet.bvlife.bfr.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.baoviet.bvlife.bfr.R;
import com.baoviet.bvlife.bfr.util.StringDefine;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText edtUsername, edtPassword;
    CheckBox cbRemember;
    TextView txtNoticeLogin;
    ProgressDialog progressDialog;

    SharedPreferences sharedPreferences;

    String username,password,responseMessage,responseStatus, fullName, token   ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Anhxa();

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        sharedPreferences = getSharedPreferences("dataLogin",MODE_PRIVATE);

        //Lay gia tri sharedPreferences
        edtUsername.setText(sharedPreferences.getString("taikhoan",""));
        edtPassword.setText(sharedPreferences.getString("matkhau",""));
        if(sharedPreferences.getString("check","").equals("check")){
            cbRemember.setChecked(sharedPreferences.getBoolean("checked", true));
        }else {
            cbRemember.setChecked(sharedPreferences.getBoolean("checked", false));
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = edtUsername.getText().toString().trim();
                password = edtPassword.getText().toString().trim();

                if(username.length() > 0 && password.length() >0){

                    progressDialog.setMessage("Đang đăng nhập...");
                    showDialog();

                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    JSONObject obj = new JSONObject();
                    try {
                        obj.put("userName", username);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        obj.put("password", password);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        obj.put("appCode", StringDefine.AppCode);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    //RequestQueue queue = MyVolley.getRequestQueue();
                    JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST,StringDefine.URL_LOGIN,obj,
                            new com.android.volley.Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try{
                                        hideDialog();
                                        responseStatus = response.getString("responseStatus").toString();
                                        responseMessage = response.getString("responseMessage");

                                        //Dang nhap
                                        if(responseStatus.equals(StringDefine.responseStatusLoginSuccess)){
                                            // Dang nhap thanh cong
                                            JSONObject jsonObject = response.getJSONObject("obj");
                                            fullName = jsonObject.getString("fullName");
                                            token = jsonObject.getString("token");
                                            //System.out.println(responseStatus + "_" + responseMessage + "_" +token);

                                            Intent intent = new Intent(LoginActivity.this, ContentActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putString("token_login", token);
                                            bundle.putString("fullName",fullName);
                                            bundle.putString("userName",username);

                                            intent.putExtra("token",bundle);
                                            startActivity(intent);
                                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_LONG).show();

                                            // nếu có check
                                            if(cbRemember.isChecked()){
                                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                                editor.putString("taikhoan",username);
                                                editor.putString("matkhau",password);
                                                editor.putString("check","check");
                                                editor.putBoolean("cheked", true);
                                                editor.commit();
                                            }else{
                                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                                editor.remove("taikhoan");
                                                editor.remove("matkhau");
                                                editor.remove("check");
                                                editor.remove("checked");
                                                editor.commit();
                                            }
                                        }else{
                                            // Sai userName hoac pass
                                            txtNoticeLogin.setText(responseMessage);
                                        }

                                    }catch (JSONException e){
                                        e.printStackTrace();
                                        hideDialog();
                                        txtNoticeLogin.setText("Đã có lỗi trong quá trình đăng nhập, kiểm tra lại kết nối Internet!");
                                    }
                                }
                            },
                            new com.android.volley.Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    //Log.d("Error.Response", error.getMessage().toString());
                                    hideDialog();
                                    txtNoticeLogin.setText("Đã có lỗi trong quá trình đăng nhập, kiểm tra lại kết nối Internet!");
                                }
                            }){

                        @Override
                        public String getBodyContentType() {
                            return "application/json; charset=utf-8";
                        }

                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            HashMap<String, String> headers = new HashMap<String, String>();
                            headers.put("AppCode", StringDefine.AppCode);
                            return headers;
                        }
                    };
                    requestQueue.add(jsObjRequest);

                 /*   Intent intent = new Intent(LoginActivity.this, ContentActivity.class);

                    startActivity(intent);*/

                }else{
                    txtNoticeLogin.setText("Bạn phải nhập cả tên đăng nhập và mật khẩu!");
                }
            }
        });
    }

    private void Anhxa() {
        btnLogin = (Button) findViewById(R.id.buttonLogin);
        edtUsername = (EditText) findViewById(R.id.edtUserName);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        cbRemember =(CheckBox) findViewById(R.id.checkBoxRemember);
        txtNoticeLogin = (TextView) findViewById(R.id.textViewNoticeLogin);

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        //getMenuInflater().inflate(R.menu.menu_context, menu);

        return super.onCreateOptionsMenu(menu);
    }

    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }
    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }
}
