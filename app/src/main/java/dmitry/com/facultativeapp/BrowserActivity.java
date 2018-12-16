package dmitry.com.facultativeapp;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import dmitry.com.facultativeapp.R;

public class BrowserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        WebView webView = findViewById(R.id.webView);
        Uri data = getIntent().getData();
        assert data != null;
        webView.loadUrl(data.toString());

    }
}
