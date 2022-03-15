package com.example.ucdemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ucdemo.R;

import java.util.ArrayList;
import java.util.List;


public class arrayadapt_emp extends ArrayAdapter<String> {

    //    String[] str =  {"Feedback History","Order History","About Us","Log Out","Exit"};
    List<String> options_emp ;

    Context context;
    public arrayadapt_emp(@NonNull Context context,List<String> op_emp) {
        super(context, R.layout.fragment_emp_profile_listv,op_emp);
        this.context=context;
        options_emp = op_emp;

    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.d("Entered", "getView: ");
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_emp_profile_listv,null,true);

        TextView empname_emp = (TextView)view.findViewById(R.id.lbl_emp_tv);

        empname_emp.setText(options_emp.get(position));
        return view;
    }
}
