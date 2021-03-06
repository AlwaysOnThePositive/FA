package dmitry.com.facultativeapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import dmitry.com.facultativeapp.Model.AccessToken;
import dmitry.com.facultativeapp.Model.User;
import dmitry.com.facultativeapp.helpers.App;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityAuth extends AppCompatActivity {

    Button btnSignIn;

    private String LOG = "TestLog";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        btnSignIn = findViewById(R.id.buttonSignIn);
    }

    public void clickSignIn (View view) {
        signIn();
    }

    //метод для входа в акк
    private void signIn() {
        String myUrlGit = "https://github.com/login/oauth/authorize?client_id=" + App.getClientId
                () +
                "&scope=repo&redirect_uri=" + App.getRedirectUri();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(myUrlGit));
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Uri uri = getIntent().getData();
        if (uri != null && uri.toString().startsWith(App.getRedirectUri())) {

            String code = uri.getQueryParameter("code");

            App.getNetClient().getAccessToken(App.getClientId(), App.getClientSecret(), code, new
                    Callback<AccessToken>() {
                @Override
                public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(ActivityAuth.this, "Токен = " + response.body().getAccessToken(), Toast.LENGTH_LONG).show();
                        App.setAccessToken(response.body().getAccessToken());
                        App.setBaseNetClient();
                        getUserName();
                    } else {
                        Log.d(LOG, "Код ошибки = " + response.code());
                        try {
                            Log.d(LOG, "Сообщение ошибки = " + response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<AccessToken> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

    private void getUserName() {
        App.getNetClient().getCurrentUser(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    App.setUsername(response.body().getLogin());
                    goMainActivity();
                } else {
                    Log.d(LOG, "Код ошибки = " + response.code());
                    try {
                        Log.d(LOG, "Сообщение ошибки = " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void goMainActivity() {
        Intent intent = new Intent(this, ActivityMain.class);
        startActivity(intent);
        finishAffinity();
    }
}
