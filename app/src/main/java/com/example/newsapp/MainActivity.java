package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tab;
    TabItem tech, home, sports, business, health, entertainment;
    Toolbar toolbar;
    ViewPager viewPager;
    RelativeLayout toolbar_layout;
    String api="278bc349b08447cbb1b26c45a18a8a2a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        home=findViewById(R.id.home);
        tech=findViewById(R.id.tech);
        sports=findViewById(R.id.sports);
        business=findViewById(R.id.business);
        health=findViewById(R.id.health);
        entertainment=findViewById(R.id.entertainment);
        toolbar_layout=findViewById(R.id.toolbar_layout);

        viewPager=findViewById(R.id.fragmentContainer);
        tab=findViewById(R.id.tab);

        PagerAdapter pagerAdapter=new PagerAdapter(getSupportFragmentManager(), 6);
        viewPager.setAdapter(pagerAdapter);

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0 || tab.getPosition()==1 ||tab.getPosition()==2 ||tab.getPosition()==3 ||tab.getPosition()==4 ||tab.getPosition()==5){
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setMessage("Are you sure you wish to exit?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", null).show();
    }
}