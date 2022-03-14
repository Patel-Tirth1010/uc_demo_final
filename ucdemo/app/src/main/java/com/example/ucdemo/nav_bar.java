package com.example.ucdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;


public class nav_bar extends AppCompatActivity {

    MeowBottomNavigation bottomNavigation;
    Fragment fragment;
    Boolean exit=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_bar);

        //Assign Variable
        bottomNavigation = findViewById(R.id.bottom_nav);

        //Add menu item
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_baseline_notifications_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_baseline_info_24));



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
                        fragment = new ProfileFragment();
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
                        fragment = new ProfileFragment();

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


//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//    }
}


