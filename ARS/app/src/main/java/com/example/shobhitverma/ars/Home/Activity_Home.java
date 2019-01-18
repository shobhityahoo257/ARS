package com.example.shobhitverma.ars.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.shobhitverma.ars.R;
import com.example.shobhitverma.ars.Search.Activity_AddRoom;

import java.util.ArrayList;
import java.util.List;

public class Activity_Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private  RecyclerView recyclerView;
    ProductAdapter productAdapter;

    List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.content_main);
        setContentView(R.layout.toolbar);
        setContentView(R.layout.nav_header_main);
        setContentView(R.layout.list_layout);
        setContentView(R.layout.home);
        setContentView(R.layout.app_bar_main);
        setContentView(R.layout.activity__home);

        productList =new ArrayList<>();

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList.add(
                new Product(
                        1,
                        "TAJ HOTELS Mumbai,India",
                        "Near Bandra",
                        4.3,
                        60000,
                        R.drawable.taj));

        productList.add(
                new Product(
                        1,
                        "Hilton",
                        "USA",
                        4.3,
                        60000,
                        R.drawable.hilton));

        productList.add(
                new Product(
                        1,
                        "Marriot",
                        "Dubai",
                        4.3,
                        60000,
                        R.drawable.marriot));

        productList.add(
                new Product(
                        1,
                        "Radisson",
                        "Varanasi",
                        4.3,
                        60000,
                        R.drawable.radisson));



        productAdapter=new ProductAdapter(this,productList);
        recyclerView.setAdapter(productAdapter);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent=new Intent(Activity_Home.this, Activity_AddRoom.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_addroom) {
            // Handle the camera action
        } else if (id == R.id.nav_acc) {

        } else if (id == R.id.nav_xx) {

        } else if (id == R.id.nav_yy) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}