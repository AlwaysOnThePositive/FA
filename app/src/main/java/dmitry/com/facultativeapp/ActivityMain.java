package dmitry.com.facultativeapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;

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
import android.widget.TextView;

import dmitry.com.facultativeapp.fragments.FragmentContacts;
import dmitry.com.facultativeapp.fragments.FragmentInfo;
import dmitry.com.facultativeapp.fragments.FragmentMap;
import dmitry.com.facultativeapp.fragments.FragmentRepo;
import dmitry.com.facultativeapp.fragments.FragmentSensor;

public class ActivityMain extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentRepo fRepo;
    private FragmentMap fMap;
    private FragmentContacts fContacts;
    private FragmentInfo fInfo;
    private FragmentSensor fSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        TextView uName = navigationView.getHeaderView(0).findViewById(R.id.githubName);
        TextView uCom = navigationView.getHeaderView(0).findViewById(R.id.githubCom);

        Intent intent = getIntent();
        String name = intent.getStringExtra("login");
        uName.setText(name);
        String visit = "Visit: ";
        String com = ".com";
        name = visit + name + com;
        uCom.setText(name);

        fRepo = new FragmentRepo();
        fMap = new FragmentMap();
        fContacts = new FragmentContacts();
        fInfo = new FragmentInfo();
        fSensor = new FragmentSensor();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentTransaction fTrans = getSupportFragmentManager().beginTransaction();

        if (id == R.id.nav_repo) {
            fTrans.replace(R.id.container, fRepo);

        } else if (id == R.id.nav_map) {
            fTrans.replace(R.id.container, fMap);
        } else if (id == R.id.nav_contacts) {
            fTrans.replace(R.id.container, fContacts);
        } else if (id == R.id.nav_info) {
            fTrans.replace(R.id.container, fInfo);
        } else if (id == R.id.nav_sensor) {
            fTrans.replace(R.id.container, fSensor);
        } else if (id == R.id.nav_logout) {

        }

        fTrans.commit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
