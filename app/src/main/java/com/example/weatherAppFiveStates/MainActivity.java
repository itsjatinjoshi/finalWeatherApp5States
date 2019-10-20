package com.example.weatherAppFiveStates;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public Toolbar toolbar;
    public DrawerLayout drawerLayout;
    public NavController navController;
    public NavigationView navigationView;

    ImageView img_header_bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupNavigation();

    }

    public void setupNavigation()
    {
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);

        navController = Navigation.findNavController(this, R.id.hostFragment);

        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(this);

//        ImageView image = (ImageView)findViewById(R.id.img_header_bg);
//        Glide.with(this)
//                .load("https://media.giphy.com/media/yse8MzKv61jlC/giphy.gif")
//                .into(img_header_bg);

      //  Ion.with(image).load("http://mygifimage.gif");


    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(
                Navigation.findNavController(this, R.id.hostFragment), drawerLayout);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


        menuItem.setCheckable(true);
        drawerLayout.closeDrawers();

        int id =  menuItem.getItemId();

        switch (id){

            case R.id.first:
                navController.navigate(R.id.weather1);
                break;

            case R.id.second:
                navController.navigate(R.id.weather2);
                break;

            case R.id.third:
                navController.navigate(R.id.weather3);
                break;
            case R.id.forth:
                navController.navigate(R.id.weather4);
                break;

            case R.id.fifth:
                navController.navigate(R.id.weather5);
                break;

        }



        return true;
    }
}
