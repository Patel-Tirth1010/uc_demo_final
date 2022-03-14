package com.example.ucdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;


public class nav_bar extends AppCompatActivity {

    MeowBottomNavigation bottomNavigation;
    Fragment fragment;
    Boolean exit=false;
    SharedPreferences save_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_bar);
        save_data = getSharedPreferences("SAVE_DATA", MODE_PRIVATE);
        String Email = save_data.getString("email","null");

        Log.d("email bundle", ""+Email);
        //Assign Variable
        bottomNavigation = findViewById(R.id.bottom_nav);

        //Add menu item
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_baseline_notifications_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_baseline_person_24));


        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                return;
            }
        });

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                switch (item.getId()) {

                    case 1:
                        fragment = new HomeFragment();
                        break;

                    case 2:
                        fragment = new BookingsFragment();
                        break;

                    case 3:
                        fragment = new ProfileFragment(Email);
                        break;

                }
//                loadFragment(fragment);

            }
        });
        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {


                switch (item.getId()) {

                    case 1:
                        fragment = new HomeFragment();
                        break;

                    case 2:
                        fragment = new BookingsFragment();

                        break;

                    case 3:
                        fragment = new ProfileFragment(Email);

                        break;

                }
                loadFragment(fragment);

            }

        });

//        bottomNavigation.setCount(1,"4");
        bottomNavigation.show(1, true);


    }

public void loadFragment(Fragment fragment)
{
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

    transaction.replace(R.id.framelayoutforfragment,fragment);
    transaction.commit();
}


    @Override
    public void onBackPressed() {
        bottomNavigation.show(1, true);
        if(exit)
        {
            finishAffinity();
            System.exit(0);
        }
        exit = true;
    }

}


