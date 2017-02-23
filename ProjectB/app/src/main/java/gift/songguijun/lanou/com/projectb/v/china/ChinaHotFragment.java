package gift.songguijun.lanou.com.projectb.v.china;

import android.view.View;
import android.widget.ListView;

import java.util.HashMap;
import java.util.Map;

import gift.songguijun.lanou.com.projectb.R;
import gift.songguijun.lanou.com.projectb.base.BaseFragment;
import gift.songguijun.lanou.com.projectb.bean.ChinaHotBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dllo on 17/2/23.
 */

public class ChinaHotFragment extends BaseFragment{
    private ListView listView;
    private Retrofit retrofit;
    String Url = "http://mapiv2.yinyuetai.com/bb/trend.json?offset=0&size=20&type=Billboard";
    final String key1 = "App-Id";
    final String value1 = "10101045";

    final String key2 = "Device-Id";
    final String value2 = "f1ef0a488a13203667bed4e6565403e1";

    final String key3 = "Device-V";
    final String value3 = "aU9TXzEwLjEuMV8xMjQyKjIyMDhfMTAwMDAxMDAwX2lQaG9uZTcsMQ==";
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_v_china_hot;
    }

    @Override
    protected void initView(View itemView) {
        listView = (ListView) itemView.findViewById(R.id.china_hot_lv);
    }

    @Override
    protected void initData() {

        retrofit = new Retrofit.Builder().baseUrl("http://mapiv2.yinyuetai.com/").addConverterFactory(GsonConverterFactory.create()).build();
        HotService service = retrofit.create(HotService.class);
        Map<String,String> map = new HashMap<>();
        map.put(key1,value1);
        map.put(key2,value2);
        map.put(key3,value3);
        Call<ChinaHotBean> call = service.startRequest(Url,map);
        call.enqueue(new Callback<ChinaHotBean>() {
            @Override
            public void onResponse(Call<ChinaHotBean> call, Response<ChinaHotBean> response) {
                ChinaHotBean bean = response.body();
                ChinaHotListViewAdapter adapter = new ChinaHotListViewAdapter(mContext);
                adapter.setData(bean.getData().getVideos());
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ChinaHotBean> call, Throwable t) {

            }
        });
    }
}
