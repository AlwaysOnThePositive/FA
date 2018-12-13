package dmitry.com.facultativeapp;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

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

import com.ncapdevi.fragnav.FragNavController;

import java.util.ArrayList;
import java.util.List;

import dmitry.com.facultativeapp.fragments.FragmentContacts;
import dmitry.com.facultativeapp.fragments.FragmentInfo;
import dmitry.com.facultativeapp.fragments.FragmentMap;
import dmitry.com.facultativeapp.fragments.FragmentRepo;
import dmitry.com.facultativeapp.fragments.FragmentSensor;

public class ActivityMain extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragNavController fragNavController;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TextView uName = navigationView.getHeaderView(0).findViewById(R.id.githubName);
        TextView uCom = navigationView.getHeaderView(0).findViewById(R.id.githubCom);

        String name = getIntent().getStringExtra("login");
        uName.setText(name);
        String visit = "Visit: ";
        String com = ".com";
        name = visit + name + com;
        uCom.setText(name);

        //Все что нужно для включения навигации через bottomNavigation
        fragNavController = new FragNavController(getSupportFragmentManager(), R.id.container);
        //Список с нашими фрагментами
        List<Fragment> rootFragments = new ArrayList<>();
        rootFragments.add(new FragmentRepo());
        rootFragments.add(new FragmentMap());
        rootFragments.add(new FragmentContacts());
        rootFragments.add(new FragmentInfo());
        rootFragments.add(new FragmentSensor());
        fragNavController.setRootFragments(rootFragments);
        fragNavController.setFragmentHideStrategy(FragNavController.HIDE);
        //!!! инициализация нашего контроллера!!! ОБЯЗАТЕЛЬНО ИНАЧЕ ОШИБКА
        fragNavController.initialize(FragNavController.TAB1, savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_repo) {
            fragNavController.switchTab(FragNavController.TAB1);
        } else if (id == R.id.nav_map) {
            fragNavController.switchTab(FragNavController.TAB2);
        } else if (id == R.id.nav_contacts) {
            fragNavController.switchTab(FragNavController.TAB3);
        } else if (id == R.id.nav_info) {
            fragNavController.switchTab(FragNavController.TAB4);
        } else if (id == R.id.nav_sensor) {
            fragNavController.switchTab(FragNavController.TAB5);
        } else if (id == R.id.nav_logout) {
            //Доделать кнопку выхода из приложения
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
