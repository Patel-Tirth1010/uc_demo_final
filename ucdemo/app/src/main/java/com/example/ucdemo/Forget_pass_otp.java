package com.example.ucdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Forget_pass_otp extends AppCompatActivity {

    public String Email;
   TextInputEditText otptf1, otptf2, otptf3, otptf4, otptf5, otptf6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass_otp);

        otptf1 = findViewById(R.id.otptf1);
        otptf2 = findViewById(R.id.otptf2);
        otptf3 = findViewById(R.id.otptf3);
        otptf4 = findViewById(R.id.otptf4);
        otptf5 = findViewById(R.id.otptf5);
        otptf6 = findViewById(R.id.otptf6);


        otptf1.addTextChangedListener(new movetonexttextfield(otptf2,otptf1));
        otptf2.addTextChangedListener(new movetonexttextfield(otptf3,otptf1));
        otptf3.addTextChangedListener(new movetonexttextfield(otptf4,otptf2));
        otptf4.addTextChangedListener(new movetonexttextfield(otptf5,otptf3));
        otptf5.addTextChangedListener(new movetonexttextfield(otptf6,otptf4));
        otptf6.addTextChangedListener(new movetonexttextfield(otptf6,otptf5));
    }

    public void sendotp(View view){

        Bundle bundle = getIntent().getExtras();
        Email = bundle.getString("email");
        String otp = Objects.requireNonNull(otptf1.getText()).toString()+ otptf2.getText().toString() + otptf3.getText().toString() + otptf4.getText().toString() + otptf5.getText().toString() + otptf6.getText().toString() ;


//        Toast.makeText(getApplicationContext(), "Approved1 ", Toast.LENGTH_SHORT).show();
        StringRequest request = new StringRequest(Request.Method.POST, "https://urbanclap1.000webhostapp.com/Customer/forgot_password/otp_verify.php", response -> {

            if(response.equalsIgnoreCase("Success")) {
                Toast.makeText(getApplicationContext(), "Otp is correct", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,Changepass.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString("email",Email);
                intent.putExtras(bundle2);
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
                params.put("otp",otp);


                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }
}


class movetonexttextfield implements TextWatcher
{
    TextInputEditText next,prev;

    public movetonexttextfield(TextInputEditText next, TextInputEditText prev) {
        this.next = next;
        this.prev = prev;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        String text = s.toString();
        if (text.length() == 1)
            next.requestFocus();
        else if (text.length() == 0)
            prev.requestFocus();


    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

}