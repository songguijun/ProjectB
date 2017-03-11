package gift.songguijun.lanou.com.projectb.v.special;

import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import gift.songguijun.lanou.com.projectb.R;
import gift.songguijun.lanou.com.projectb.base.BaseActivity;

/**
 * Created by dllo on 17/3/9.
 */

public class ImPortActivity extends BaseActivity {
    private TextView textView ;
    private WebView webView;
    private ImageView imageView;
    @Override
    protected int getLayout() {
        return R.layout.activity_import;
    }

    @Override
    protected void initView() {
        textView = (TextView) findViewById(R.id.tv_import);
        webView = (WebView) findViewById(R.id.import_wb);
        imageView = (ImageView) findViewById(R.id.import_iv_finsh);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initDate() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("标题");
        textView.setText(title);
        String url = intent.getStringExtra("网址");
        webView.loadUrl(url);
    }
}
