package com.example.uassemester2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etNama, etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = (EditText) findViewById(R.id.et_nama);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button)findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        login();
    }

    private void login() {
        StringRequest request = new StringRequest(Request.Method.POST, "http://localhost/MPA/uas2/login.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.contains("ok")){
                            startActivity(new Intent(getApplicationContext(),homePage.class));
                        }else {
                            Toast.makeText(getApplicationContext(),
                                    "Nama dan Password tidak terdaftar...!",Toast.LENGTH_SHORT).show();
                        }
//                        Toast.makeText(getApplicationContext(),
//                                "this is response : " + response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String>Params = new HashMap<>();
                Params.put("nama",etNama.getText().toString());
                Params.put("password",etPassword.getText().toString());
                return Params;
            }
        };
        Volley.newRequestQueue(this).add(request);

    }
}