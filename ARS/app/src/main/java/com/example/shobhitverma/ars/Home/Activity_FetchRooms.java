package com.example.shobhitverma.ars.Home;

import android.support.design.widget.FloatingActionButton;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.example.shobhitverma.ars.R;
import com.example.shobhitverma.ars.Search.Activity_AddRoom;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Activity_FetchRooms extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private FirebaseRecyclerAdapter adapter;
    private RecyclerView mRecyclerView;
    //private FloatingActionButton fab;
    //private Context mCtx;
    //private List<Product> productList;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        setContentView(R.layout.activity__fetch_rooms);
        setContentView(R.layout.content_main);
        setContentView(R.layout.toolbar);
        setContentView(R.layout.nav_header_main);
        setContentView(R.layout.list_layout);

        setContentView(R.layout.app_bar_main);
        setContentView(R.layout.activity__home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


         FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            Intent intent=new Intent(Activity_FetchRooms.this, Activity_AddRoom.class);
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


        // setContentView(R.layout);
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Rooms")
                .limitToLast(50);
       // Toast.makeText(Activity_FetchRooms.this,"Price is:" + FirebaseDatabase.getInstance()
         //       .getReference()
           //     .child("Rooms").child("price").setValue("1000"),Toast.LENGTH_LONG).show();

        FirebaseRecyclerOptions<Rooms> options =
                new FirebaseRecyclerOptions.Builder<Rooms>()
                        .setQuery(query, Rooms.class)
                        .build();


        adapter = new FirebaseRecyclerAdapter<Rooms, UsersViewHolder>(options) {

            @Override
            public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.message for each item
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_layout, parent, false);

                return new UsersViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(UsersViewHolder holder, int position, Rooms model) {

                   //holder.setName(model.getName());
                  // adapter.getRef(position).keepSynced(true);
                 //adapter.getRef(position).keepSynced(true);
                     holder.setImage(model.getImage(),getApplicationContext());
                holder.setName(model.getName());
                holder.setAddress(model.getAddress());
                holder.setRating(model.getRating());
                holder.setRent(model.getRent());
                Toast.makeText(Activity_FetchRooms.this,"Price is:" /*+ model.getStatus()*/,Toast.LENGTH_LONG).show();

                // System.out.println("onBindViewHolder" + model.getStatus());

            }
            public void onDataChanged() {

                System.out.println("Data Has Changed");
            }

        };


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
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



    public static class  UsersViewHolder extends RecyclerView.ViewHolder

    {
        View mView;
        public UsersViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
        }
        /*public  void setName(String status)
        {
            TextView userNameView=(TextView)mView.findViewById(R.id.usersinglelayout_textView_name);
            userNameView.setText(status);
        }*/
        public void setName(String name) {
            System.out.println("Status has called" + name);
            TextView userStatusView = (TextView) mView.findViewById(R.id.textViewTitle);
            userStatusView.setText(name);
        }
        public void setImage(String image,Context ctx)
        {
            System.out.println("Image has called");
            ImageView imageView=(ImageView)mView.findViewById(R.id.imageView);
            Picasso.with(ctx).load(image).placeholder(R.drawable.radisson).into(imageView);
        }
        public void setAddress(String address)
        {
            TextView textView=(TextView) mView.findViewById(R.id.textViewShortDesc);
            textView.setText(address);
        }
        public void setRating(String rating)
        {
            TextView textView=(TextView) mView.findViewById(R.id.textViewRating);
            textView.setText(rating);
        }
        public void setRent(String rent)
        {
            TextView textView=(TextView) mView.findViewById(R.id.textViewPrice);
            textView.setText(rent);
        }

    }

    @Override
    protected void onStart() {

      //  mFirebaseDatabase=FirebaseDatabase.getInstance().getReference().child("Rooms");
        super.onStart();
       adapter.startListening();


    }


}
