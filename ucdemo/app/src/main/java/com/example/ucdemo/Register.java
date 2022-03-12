package com.example.ucdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    EditText fname,lname,email,address,city,state,pincode,password,ph_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fname= findViewById(R.id.fname);
        lname= findViewById(R.id.lname);
        ph_no= findViewById(R.id.ph);
        email= findViewById(R.id.email);
        password= findViewById(R.id.password);
        address= findViewById(R.id.address);
        city= findViewById(R.id.city);
        state= findViewById(R.id.state);
        pincode= findViewById(R.id.pincode);
    }
    public void movetologin(View view) {
        String FName = fname.getText().toString().trim();
        String LName = lname.getText().toString().trim();
        String Ph_no = ph_no.getText().toString().trim();
        String Email = email.getText().toString().trim();
        String Address = address.getText().toString().trim();
        String City = city.getText().toString().trim();
        String State = state.getText().toString().trim();
        String Pincode = pincode.getText().toString().trim();
        String Password = password.getText().toString().trim();

        StringRequest request = new StringRequest(Request.Method.POST, "https://urbanclap1.000webhostapp.com/Customer/insert.php", response -> {

            if (response.equalsIgnoreCase("Data Inserted"))
                Toast.makeText(getApplicationContext(), "Data Inserted", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

        },
                error -> Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show()
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("fname", FName);
                params.put("lname", LName);
                params.put("ph_no", Ph_no);
                params.put("address", Address);
                params.put("city", City);
                params.put("pincode", Pincode);
                params.put("state", State);
                params.put("password", Password);
                params.put("email", Email);


                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);

        Intent intent = new Intent(getApplicationContext(),Login.class);
        startActivity(intent);

        finish();
    }
}