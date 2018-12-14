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
import dmitry.com.facultativeapp.helpers.App;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityAuth extends AppCompatActivity {

    EditText textLogin;
    EditText textPassword;
    Button btnSignIn;

    private String LOG = "MyLog";

    //ClientId приложения на гите
    private String cliendId = "830e851082bc0c849162";
    //ClientSecret приложения на гите
    private String clientSecret = "58c5b23435daf4ea2c2dc099a5be5ed7a7f75678";
    //CallBack для окончания авторизации
    private String redirectUri = "dmitry.com.facultativeapp://callback";
    //Ссылка по которой будет производится авторизация
    private String myUrlGit = "https://github.com/login/oauth/authorize?client_id=" + cliendId +
            "&scope=repo&redirect_uri=" + redirectUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        textLogin = findViewById(R.id.login);
        textPassword = findViewById(R.id.password);
        btnSignIn = findViewById(R.id.buttonSignIn);

    }

    public void clickSignIn (View view) {
        signIn();
    }

    //метод для входа в акк
    private void signIn() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(myUrlGit));
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Uri uri = getIntent().getData();
        if (uri != null && uri.toString().startsWith(redirectUri)) {

            String code = uri.getQueryParameter("code");

            App.getNetClient().getAccessToken(cliendId, clientSecret, code, new Callback<AccessToken>() {
                @Override
                public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(ActivityAuth.this, "Токен = " + response.body().getAccessToken(), Toast.LENGTH_LONG).show();
                        App.setAccessToken(response.body().getAccessToken());
                        App.setBaseNetClient();
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
                public void onFailure(Call<AccessToken> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

    //Метод для перехода на след активити - которое головное
    private void goMainActivity() {
        Intent intent = new Intent(this, ActivityMain.class);
        startActivity(intent);
        finishAffinity();
    }
}
