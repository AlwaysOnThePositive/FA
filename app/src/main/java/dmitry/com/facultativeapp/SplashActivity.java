package dmitry.com.facultativeapp;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dmitry.com.facultativeapp.helpers.App;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Смотрим если есть accessToken то стартуем главное активити - если нету то активити авторизации
        if (App.getAccessToken() != null) {
            Intent intent = new Intent(this, ActivityMain.class);
            startActivity(intent);
            finishAffinity();
        } else {
            Intent intent = new Intent(this, ActivityAuth.class);
            startActivity(intent);
            finishAffinity();
        }
    }
}