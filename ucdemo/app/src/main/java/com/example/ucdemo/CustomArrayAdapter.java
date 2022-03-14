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


class CustomeArrayAdapter extends ArrayAdapter<String> {

    //    String[] str =  {"Feedback History","Order History","About Us","Log Out","Exit"};
    List<String> options ;

    Context context;
    public CustomeArrayAdapter(@NonNull Context context,List<String> option) {
        super(context, R.layout.fragment_profile_listv,option);
        this.context=context;
        options = option;

    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.d("Entered", "getView: ");
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_profile_listv,null,true);

        TextView option = (TextView)view.findViewById(R.id.lbl_tv);

        option.setText(options.get(position));
        return view;
    }
}
