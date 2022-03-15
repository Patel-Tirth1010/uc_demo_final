package com.example.ucdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.icu.util.Measure;
import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class emp_nav_bar extends AppCompatActivity {

    MeowBottomNavigation bottomNavigation_emp;
    Fragment fragment_emp;
    Boolean exit2 = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_nav_bar);

        //Assign Variable
        bottomNavigation_emp = findViewById(R.id.bottom_nav_emp);

        //Add menu item
        bottomNavigation_emp.add(new MeowBottomNavigation.Model(1, R.drawable.ic_baseline_home_24));
        bottomNavigation_emp.add(new MeowBottomNavigation.Model(2, R.drawable.ic_baseline_notifications_24));
        bottomNavigation_emp.add(new MeowBottomNavigation.Model(3, R.drawable.ic_baseline_person_24));



        //WHEN MENUICON PRESSED TWICE
        bottomNavigation_emp.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

                switch (item.getId()) {

                    case 1:
                        fragment_emp = new emp_HomeFragment();

                        break;

                    case 2:
                        fragment_emp = new emp_BookingsFragment();
                        break;

                    case 3:
                        fragment_emp = new emp_ProfileFragment();
                        break;

                }


            }
        });



        //WHEN MENUICON PRESSED TWICE
        bottomNavigation_emp.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                return;
            }
        });


        //DISPLAYING OF MENU ICONS
        bottomNavigation_emp.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {


                switch (item.getId()) {

                    case 1:
                        fragment_emp = new emp_HomeFragment();
                        break;

                    case 2:
                        fragment_emp = new emp_BookingsFragment();

                        break;

                    case 3:
                        fragment_emp = new emp_ProfileFragment();

                        break;

                }

                loadFragment(fragment_emp);

            }

        });

        bottomNavigation_emp.show(1, true);


    }

    //LOADS FRAGMENT
    public void loadFragment(Fragment fragment_emp)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.framelayout_emp,fragment_emp);
        transaction.commit();
    }


    //WHEN BACK BUTTON PRESSED
    @Override
    public void onBackPressed() {
        bottomNavigation_emp.show(1, true);
        if(exit2)
        {
            finishAffinity();
            System.exit(0);
        }
        exit2 = true;
    }

}