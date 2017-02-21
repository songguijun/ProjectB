package gift.songguijun.lanou.com.okhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import gift.songguijun.lanou.com.okhttp.ok.OkHttpManager;
import gift.songguijun.lanou.com.okhttp.ok.OnNetResultListener;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        final String postUrl = "http://appserver.jnwtv.com:8080/jnwtv-client/movie/getmoviedetail";
        final String key1 = "account";
        final String value1 = "26690576370";
        final String key2 = "episodeNo";
        final String value2 = "1";
        final String key3 = "mId";
        final String value3 = "1193";
        Map<String, String> maps = new HashMap<>();
        maps.put(key1,value1);
        maps.put(key2,value2);
        maps.put(key3,value3);

        OkHttpManager.getInstance()
                .startHeader(postUrl, maps, new OnNetResultListener() {

                    @Override
                    public void onSucceessListener(String succeessStr) {
                        Log.d("SecondActivity", succeessStr);
                    }

                    @Override
                    public void onFailureListener(String errMsg) {
                        Toast.makeText(SecondActivity.this, errMsg, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
