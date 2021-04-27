package com.example.uassemester2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class homePage extends AppCompatActivity implements View.OnClickListener {

    TextView tampil;
    EditText et_ambil;
    Button btn_ambil;
//    private RequestQueue mqueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

//        mqueue = Volley.newRequestQueue(this);
        tampil = (TextView)findViewById(R.id.tampil);
        et_ambil = (EditText)findViewById(R.id.etAmbil);
        btn_ambil = (Button)findViewById(R.id.btn_ambil);

        btn_ambil.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        uraiJson();
    }

    private void uraiJson() {
        StringRequest request = new StringRequest(Request.Method.POST, "http://localhost/MPA/uas2/ambil.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.contains("ok")){
                            // maka tampilkan data di text view yang mempunyai id tampil 
                        }else {
                            Toast.makeText(getApplicationContext(),
                                    "Nama tidak terdaftar...!",Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String>Params = new HashMap<>();
                Params.put("nama",et_ambil.getText().toString());
                return Params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }

}