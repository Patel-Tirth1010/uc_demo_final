package com.example.ucdemo;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment{

    Bundle bundle = new Bundle();
    String Email;
    public List<String> option = Arrays.asList("Feedback History","Order History","About Us","Log Out","Exit");

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }
    public  ProfileFragment(String email)
    {
        Email=email;
    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        ListView lvw = (ListView) view.findViewById(R.id.lview_profile);
        CustomeArrayAdapter adptcls = new CustomeArrayAdapter(getContext(),option);
        lvw.setAdapter(adptcls);

        TextView editprofile = (TextView)view.findViewById(R.id.edtprf_tv);

        editprofile.setOnClickListener(v -> {

            StringRequest request = new StringRequest(Request.Method.POST, "https://urbanclap1.000webhostapp.com/Customer/edit_profile/Retrive_data.php", response -> {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("customer");


                    if (success.equals("1")) {
//                    Toast.makeText(getContext(),"enterd",Toast.LENGTH_SHORT).show();
                        for (int i = 0; i < jsonArray.length(); i++) {

//                    Toast.makeText(getContext(),"enterd2",Toast.LENGTH_SHORT).show();
                            Log.e( "json", "onResponse: array executed");

                            JSONObject object;
                            object = jsonArray.getJSONObject(i);
                            String fname = object.getString("fname");
                            String lname = object.getString("lname");
                            String ph_no = object.getString("ph_no");
                            String email = object.getString("email");
                            String address = object.getString("address");
                            String city = object.getString("city");
                            String state = object.getString("state");
                            String pincode = object.getString("pincode");

                            bundle.putString("fname",fname);
                            bundle.putString("lname",lname);
                            bundle.putString("ph_no",ph_no);
                            bundle.putString("email",email);
                            bundle.putString("address",address);
                            bundle.putString("city",city);
                            bundle.putString("state",state);
                            bundle.putString("pincode",pincode);

                            Intent intent = new Intent(getContext(), EditProfile.class);
                            intent.putExtras(bundle);
                            startActivity(intent);

//                            Toast.makeText(getContext(),fname+lname+ph_no+email+address+city+state+pincode,Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (JSONException je) {
                    je.printStackTrace();
                }

            }, error -> Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show())
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("email", Email);
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            requestQueue.add(request);

        });

        return view;
    }

}