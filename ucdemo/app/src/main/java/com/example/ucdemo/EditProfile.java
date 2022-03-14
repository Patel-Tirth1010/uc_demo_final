package com.example.ucdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

public class EditProfile extends AppCompatActivity implements View.OnClickListener{

    Bundle bundle;
    TextInputEditText fname,lname,email,address,city,state,pincode,ph_no;
    MaterialButton update,cancel;
    SharedPreferences save_data;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        bundle = getIntent().getExtras();

        update = findViewById(R.id.updateprof);
        cancel = findViewById(R.id.cancelchange);
        fname= findViewById(R.id.fname);
        lname= findViewById(R.id.lname);
        ph_no= findViewById(R.id.ph);
        email= findViewById(R.id.email);
        address= findViewById(R.id.address);
        city= findViewById(R.id.city);
        state= findViewById(R.id.state);
        pincode= findViewById(R.id.pincode);

        fname.setText(bundle.getString("fname"));
        lname.setText(bundle.getString("lname"));
        ph_no.setText(bundle.getString("ph_no"));
        email.setText(bundle.getString("email"));
        address.setText(bundle.getString("address"));
        city.setText(bundle.getString("city"));
        state.setText(bundle.getString("state"));
        pincode.setText(bundle.getString("pincode"));

        Toast.makeText(getApplicationContext(),"hey",Toast.LENGTH_SHORT).show();


        update.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String Fname = fname.getText().toString().trim();
        String Lname = lname.getText().toString().trim();
        String Ph_no = ph_no.getText().toString().trim();
        String Email = email.getText().toString().trim();
        String Address = address.getText().toString().trim();
        String City = city.getText().toString().trim();
        String State = state.getText().toString().trim();
        String Pincode = pincode.getText().toString().trim();

        editor.putString("email",Email);
        save_data = getSharedPreferences("SAVE_DATA", MODE_PRIVATE);
        editor = save_data.edit();
        editor.commit();
        editor.apply();


        if (Fname.equals("")) {
            Toast.makeText(this, "Enter Fname", Toast.LENGTH_SHORT).show();
        } else if (Lname.equals("")) {
            Toast.makeText(this, "Enter Lname", Toast.LENGTH_SHORT).show();
        } else if (Email.equals("")) {
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
        } else if (Ph_no.equals("")) {
            Toast.makeText(this, "Enter Ph_no", Toast.LENGTH_SHORT).show();
        } else if (Address.equals("")) {
            Toast.makeText(this, "Enter Address", Toast.LENGTH_SHORT).show();
        } else if (State.equals("")) {
            Toast.makeText(this, "Enter State", Toast.LENGTH_SHORT).show();
        } else if (City.equals("")) {
            Toast.makeText(this, "Enter City", Toast.LENGTH_SHORT).show();
        } else if (Pincode.equals("")) {
            Toast.makeText(this, "Enter Pincode", Toast.LENGTH_SHORT).show();
        } else {

            StringRequest request = new StringRequest(Request.Method.POST, "https://urbanclap1.000webhostapp.com/Customer/edit_profile/Update_data.php", response -> {

                if (response.equalsIgnoreCase("success")) {
                    Toast.makeText(getApplicationContext(), "Data Updated", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(),nav_bar.class);
                    startActivity(intent);
                    finish();
                } else {
                    Log.d("response", response);
                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                }
            },
                    error -> Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show()
            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("fname", Fname);
                    params.put("lname", Lname);
                    params.put("ph_no", Ph_no);
                    params.put("address", Address);
                    params.put("city", City);
                    params.put("pincode", Pincode);
                    params.put("state", State);
                    params.put("newemail", Email);
                    params.put("oldemail", bundle.getString("email"));

                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(request);
        }
    }
}