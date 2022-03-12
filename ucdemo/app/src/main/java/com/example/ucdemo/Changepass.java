package com.example.ucdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Changepass extends AppCompatActivity {

    TextInputEditText newpass, newpass1;
    String Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepass);
        newpass = findViewById(R.id.newpass);
        newpass1 = findViewById(R.id.newpass1);
    }

    public void setNewpass(View view){
        Bundle bundle = getIntent().getExtras();
        Email = bundle.getString("email");
        String p1 = Objects.requireNonNull(newpass.getText()).toString().trim();
        String p2 = Objects.requireNonNull(newpass1.getText()).toString().trim();

        if(p1.equals(p2))
        {

            StringRequest request = new StringRequest(Request.Method.POST, "https://urbanclap1.000webhostapp.com/Customer/forgot_password/change_password.php", response -> {

                if(response.equalsIgnoreCase("success")) {
                    Toast.makeText(this,"Password changed successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this,Login.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();

            },
                    error -> Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show()
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String,String>();
                    params.put("email",Email);
                    params.put("password",p1);

                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(request);
        }
    }
}