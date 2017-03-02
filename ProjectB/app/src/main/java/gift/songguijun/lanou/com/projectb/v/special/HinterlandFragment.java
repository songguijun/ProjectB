package gift.songguijun.lanou.com.projectb.v.special;

import android.view.View;
import android.widget.ListView;

import java.util.HashMap;
import java.util.Map;

import gift.songguijun.lanou.com.projectb.R;
import gift.songguijun.lanou.com.projectb.base.BaseFragment;
import gift.songguijun.lanou.com.projectb.bean.SpecialBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dllo on 17/2/24.
 */

public class HinterlandFragment extends BaseFragment {
    private ListView listView;
    String Url = "http://mapiv2.yinyuetai.com/album/trend_rank.json?offset=0&size=20&type=ml";
    private Retrofit retrofit;
    final String key1 = "App-Id";
    final String value1 = "10101045";

    final String key2 = "Device-Id";
    final String value2 = "f1ef0a488a13203667bed4e6565403e1";

    final String key3 = "Device-V";
    final String value3 = "aU9TXzEwLjEuMV8xMjQyKjIyMDhfMTAwMDAxMDAwX2lQaG9uZTcsMQ==";
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_special_hinterland;
    }

    @Override
    protected void initView(View itemView) {
        listView = (ListView) itemView.findViewById(R.id.hinterland_lv);
    }

    @Override
    protected void initData() {
        retrofit = new Retrofit.Builder().baseUrl("http://mapiv2.yinyuetai.com/").addConverterFactory(GsonConverterFactory.create()).build();
        HinterlandService service = retrofit.create(HinterlandService.class);
        Map<String,String> map = new HashMap<>();
        map.put(key1,value1);
        map.put(key2,value2);
        map.put(key3,value3);
        Call<SpecialBean> call = service.startRequest(Url,map);
        call.enqueue(new Callback<SpecialBean>() {
            @Override
            public void onResponse(Call<SpecialBean> call, Response<SpecialBean> response) {
                SpecialBean bean = response.body();
                HinterlandListViewAdapter adapter = new HinterlandListViewAdapter(getContext());
                adapter.setData(bean.getData().getVideos());
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<SpecialBean> call, Throwable t) {

            }
        });

    }
}
