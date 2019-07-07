package com.example.drugone1;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

//    public int flag;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPageAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        tabLayout=(TabLayout)findViewById(R.id.tablayout_id);
        viewPager=(ViewPager)findViewById(R.id.viewpager_id);
        adapter=new ViewPageAdapter(getSupportFragmentManager());

        //Add Fragment Here

        adapter.AddFragment(new FragmentHome(),"");
        adapter.AddFragment(new FragmentMyorder(),"");
        if(currentUser==null){
//            Toast.makeText(getApplicationContext(),"FLAG==0",Toast.LENGTH_LONG).show();
        adapter.AddFragment(new FragmentAccount(),"");}
        else {
//            Toast.makeText(getApplicationContext(),"FLAG==1",Toast.LENGTH_LONG).show();
        adapter.AddFragment(new FragmentAccount2(),"");}
        adapter.AddFragment(new FragmentAbout(),"");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_shopping_cart_black_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_account_circle_black_24dp);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_info_black_24dp);

//        ActionBar actionBar=getSupportActionBar();
//        actionBar.setElevation(0);

    }
}
