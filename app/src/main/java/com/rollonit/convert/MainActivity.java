package com.rollonit.convert;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.rollonit.convert.ui.LengthFragment;
import com.rollonit.convert.ui.TemperatureFragment;
import com.rollonit.convert.ui.TimeFragment;
import com.rollonit.convert.ui.VolumeFragment;
import com.rollonit.convert.ui.WeightFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.nav_open, R.string.nav_close);
        toggle.getDrawerArrowDrawable().setColor(Color.BLACK);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int selected = item.getItemId();
        if (selected == R.id.menu_item_length) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LengthFragment()).commit();
        } else if (selected == R.id.menu_item_volume) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new VolumeFragment()).commit();
        } else if (selected == R.id.menu_item_weight) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new WeightFragment()).commit();
        } else if (selected == R.id.menu_item_time) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TimeFragment()).commit();
        } else if (selected == R.id.menu_item_temperature) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TemperatureFragment()).commit();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
