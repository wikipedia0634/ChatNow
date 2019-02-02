package com.example.peter.project2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peter.project2.Service.SaveLocal;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;
    public FirebaseUser user;
    public GoogleApiClient mGoogleApiClient;
    FirebaseAuth mAuth;
    MemberData userLogged;
    ImageView imgAvatar;
    TextView txtName, txtemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //SharedPreferences

        AnhXa();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //get User from local
        MemberData cuurentUser= (MemberData) getIntent().getSerializableExtra("user-info");
        //set Info CurrentUser

       txtName.setText(cuurentUser.getName());
        txtemail.setText(cuurentUser.getUsername());
        Picasso.get().load(cuurentUser.getUrlAvatar()).into(imgAvatar);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        initGoogle();
    }

    private void AnhXa() {
        // get headerLayout
        NavigationView navigationView = findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        imgAvatar = header.findViewById(R.id.Avatar);
        txtName = header.findViewById(R.id.txtusername);
        txtemail = header.findViewById(R.id.txtEmail);

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

        if (id == R.id.contactlist) {
            Intent intent = new Intent(MainActivity.this, Contact.class);
            startActivity(intent);
        } else if (id == R.id.group) {
            Intent i = new Intent(MainActivity.this, NewGroup.class);
            startActivity(i);
        } else if (id == R.id.infomation) {

        } else if (id == R.id.mygroup) {
            Intent i1 = new Intent(MainActivity.this, MyGroup.class);
            startActivity(i1);
        } else if (id == R.id.Logout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    //    private void initGoogle() {
//        mAuth = FirebaseAuth.getInstance();
//
//        //Get imageview and textview header
//        View view = navigationView.getHeaderView(0);
//        imgAvatar = (ImageView) view.findViewById(R.id.Avatar);
//        txtName = (TextView) view.findViewById(R.id.txtusername);
//        txtemail = (TextView) view.findViewById(R.id.txtEmail);
//
//        //------------GSO----------------
//
//        try {
//            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                    .requestEmail()
//                    .build();
//            mGoogleApiClient = new GoogleApiClient.Builder(this)
//                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                    .build();
//            mGoogleApiClient.connect();
//        } catch (Exception e) {
////            Toast.makeText(this, e+"", Toast.LENGTH_SHORT).show();
//        }
//
//        //---------------------------------
//
//        user = mAuth.getCurrentUser();
//
//        //get user data
//
//        try {
//            if (user != null) {
//                txtemail.setText(user.getEmail().toString());
//                txtName.setText(user.getDisplayName().toString());
//                Picasso.get().load(user.getPhotoUrl()).into(imgAvatar);
//            } else {
//                imgAvatar.setImageResource(R.drawable.logo);
//                txtName.setText("Hello");
//            }
//        } catch (Exception e) {
//            Toast.makeText(this, e + "", Toast.LENGTH_SHORT).show();
//        }
//    }

}
