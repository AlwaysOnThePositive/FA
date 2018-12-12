package dmitry.com.facultativeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityAuth extends AppCompatActivity {

    EditText textLogin;
    EditText textPassword;
    Button btnSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        textLogin = findViewById(R.id.login);
        textPassword = findViewById(R.id.password);
        btnSignIn = findViewById(R.id.buttonSignIn);




    }

    public void clickSignIn (View view) {
        Intent intent = new Intent(ActivityAuth.this, ActivityMain.class);
        intent.putExtra("login", textLogin.getText().toString());
        startActivity(intent);

    }
}
