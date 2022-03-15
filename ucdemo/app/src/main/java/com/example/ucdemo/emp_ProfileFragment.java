package com.example.ucdemo;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link emp_ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class emp_ProfileFragment extends Fragment {

    public ListView lvw_emp;
    public View view_emp;
    public List<String> op_emp = Arrays.asList("Feedback History","Order History","About Us","Log Out","Exit");

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public emp_ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment emp_ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static emp_ProfileFragment newInstance(String param1, String param2) {
        emp_ProfileFragment fragment = new emp_ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        // Inflate the layout for this fragment
        view_emp =  inflater.inflate(R.layout.fragment_emp__profile, container, false);


        //custom ListView Implementation
        lvw_emp = (ListView) view_emp.findViewById(R.id.lview_emp_profile);
        arrayAdapterclass adptcls_emp = new arrayAdapterclass(getContext(),op_emp);
        lvw_emp.setAdapter(adptcls_emp);

        //Edit Profile TextView
        TextView tv_emp = (TextView)view_emp.findViewById(R.id.edtprf_tv_emp);

        //Click listener of Edit Profile TV
        tv_emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditProfile.class);
                startActivity(intent);

            }
        });

        return view_emp;

        }
}