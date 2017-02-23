package gift.lin.lanou.com.okreviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import gift.lin.lanou.com.okreviewdemo.ok.OkHttpManager;
import gift.lin.lanou.com.okreviewdemo.ok.OnNetResultListener;

public class TestActivity extends AppCompatActivity {
    final String headUrl = "http://mapiv2.yinyuetai.com/component/prefecture.json?&type=1";
    final String key1  = "App-Id";
    final String value1 = "10201046";
    final String key2  = "Device-Id";
    final String value2 = "1862d8a3966fa7e1b4083af0c9456680";
    final String key3  = "Device-V";
    final String value3 = "QW5kcm9pZF81LjAuMl8xMDgwKjE5MjBfMTEwMDA5MDAwXyUyMlJlZG1pK05vdGUrMyUyMg==";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Map<String, String> maps = new HashMap<>();
        maps.put(key1,value1);
        maps.put(key2,value2);
        maps.put(key3,value3);
        OkHttpManager.getInstance()
                .startHeader(headUrl, maps, new OnNetResultListener() {
                    @Override
                    public void onSuccessListener(String successStr) {
                        Log.d("TestActivity", successStr);
                    }

                    @Override
                    public void onFailureListener(String errMsg) {
                        Toast.makeText(TestActivity.this, errMsg, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
