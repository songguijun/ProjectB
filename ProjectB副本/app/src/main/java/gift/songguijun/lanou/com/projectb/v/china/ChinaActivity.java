package gift.songguijun.lanou.com.projectb.v.china;

import android.content.Intent;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import gift.songguijun.lanou.com.projectb.R;
import gift.songguijun.lanou.com.projectb.base.BaseActivity;
import gift.songguijun.lanou.com.projectb.bean.ChinaActBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dllo on 17/3/10.
 */

public class ChinaActivity extends BaseActivity {
    private JCVideoPlayerStandard standard;
    private Retrofit retrofit;
    String Url = "http://mapiv2.yinyuetai.com/video/detail.json?videoId=";
    final String key1 = "App-Id";
    final String value1 = "10101045";

    final String key2 = "Device-Id";
    final String value2 = "f1ef0a488a13203667bed4e6565403e1";

    final String key3 = "Device-V";
    final String value3 = "aU9TXzEwLjEuMV8xMjQyKjIyMDhfMTAwMDAxMDAwX2lQaG9uZTcsMQ==";
    @Override
    protected int getLayout() {
        return R.layout.activity_china;
    }

    @Override
    protected void initView() {
        standard = (JCVideoPlayerStandard) findViewById(R.id.fm);
    }

    @Override
    protected void initDate() {
        Intent intent = getIntent();
        String id = intent.getStringExtra("不爽");
        Log.d("woqunima", id);

        retrofit = new Retrofit.Builder().baseUrl("http://mapiv2.yinyuetai.com/").addConverterFactory(GsonConverterFactory.create()).build();
        ChinaService service = retrofit.create(ChinaService.class);
        Map<String,String> map = new HashMap<>();
        map.put(key1,value1);
        map.put(key2,value2);
        map.put(key3,value3);
        Call<ChinaActBean> call = service.startRequest(Url+id,map);
        call.enqueue(new Callback<ChinaActBean>() {
            @Override
            public void onResponse(Call<ChinaActBean> call, Response<ChinaActBean> response) {
                ChinaActBean bean = new ChinaActBean();
                String url = bean.getData().getUrl();
                Log.d("nima", url);
                standard.setUp(url, JCVideoPlayer.SCREEN_LAYOUT_NORMAL);
            }

            @Override
            public void onFailure(Call<ChinaActBean> call, Throwable t) {

            }
        });

    }
}
