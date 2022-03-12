package com.example.ucdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class Forget_pass extends AppCompatActivity {
    Button button;
    TextInputEditText email;
    public String Email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);

        button = findViewById(R.id.next);
        email = findViewById(R.id.forgetemail);

    }

    public void gotootp(View view){

        Email = email.getText().toString().trim();


        StringRequest request = new StringRequest(Request.Method.POST, "https://urbanclap1.000webhostapp.com/Customer/forgot_password/email_verify.php", response -> {

            if(response.equalsIgnoreCase("success")) {
                Toast.makeText(this,"executed:", Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(this,Forget_pass_otp.class);
                Bundle bundle = new Bundle();
                bundle.putString("email",Email);
                intent.putExtras(bundle);
                startActivity(intent);
            }
            else
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();

        },
                error -> Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show()
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("email",Email);


                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);

    }
    }

