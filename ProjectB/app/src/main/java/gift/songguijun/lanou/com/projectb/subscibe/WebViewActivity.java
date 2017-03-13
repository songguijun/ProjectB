package gift.songguijun.lanou.com.projectb.subscibe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import gift.songguijun.lanou.com.projectb.R;

public class WebViewActivity extends AppCompatActivity {
    private WebView wv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        wv = (WebView) findViewById(R.id.wv);
        Intent intent = getIntent();
        String url = intent.getStringExtra("webView");
        wv.loadUrl(url);
    }
}
