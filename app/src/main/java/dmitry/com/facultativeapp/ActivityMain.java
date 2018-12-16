package dmitry.com.facultativeapp;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.ncapdevi.fragnav.FragNavController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dmitry.com.facultativeapp.Model.User;
import dmitry.com.facultativeapp.fragments.FragmentContacts;
import dmitry.com.facultativeapp.fragments.FragmentInfo;
import dmitry.com.facultativeapp.fragments.FragmentMap;
import dmitry.com.facultativeapp.fragments.FragmentRepo;
import dmitry.com.facultativeapp.fragments.FragmentSensor;
import dmitry.com.facultativeapp.helpers.App;
import dmitry.com.facultativeapp.sync.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityMain extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String LOG = "Logs";

    private FragNavController fragNavController;
    private DrawerLayout drawer;

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
        uName.setText(App.getUsername());

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
            logOut();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    // ДОРАБОТАТЬ !!!!!

    //Метод лдя выхода из приложения
    private void logOut() {

        // удаляем токен
        // https://github.com/settings/applications/952021/revoke_all_tokens
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://github.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        Api api = retrofit.create(Api.class);
//        Call<String> sendTokenCall = api.deleteToken();
//        sendTokenCall.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                if (response.isSuccessful()) {
//                    Toast.makeText(ActivityMain.this, "token is deleted", Toast.LENGTH_SHORT)
//                            .show();
//                } else {
//                    Toast.makeText(ActivityMain.this, "server returned a mistake", Toast
//                            .LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Toast.makeText(ActivityMain.this, "error", Toast.LENGTH_SHORT).show();
//            }
//        });


        Intent intent = new Intent(this, ActivityAuth.class);
        startActivity(intent);

    }

    private void finishActivity() {
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);
        finishAffinity();
    }
}
