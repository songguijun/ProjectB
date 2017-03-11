package gift.songguijun.lanou.com.projectb.v.china;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.HashMap;
import java.util.Map;

import gift.songguijun.lanou.com.projectb.R;
import gift.songguijun.lanou.com.projectb.base.BaseFragment;
import gift.songguijun.lanou.com.projectb.bean.ChinaTopBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dllo on 17/2/23.
 */

public class ChinaTopFragment extends BaseFragment{
    private ListView listView;
    private Retrofit retrofit;
    String Url = "http://mapiv2.yinyuetai.com/cvc/trend.json?offset=0&size=20&type=ChinaVchart";
    final String key1 = "App-Id";
    final String value1 = "10101045";

    final String key2 = "Device-Id";
    final String value2 = "f1ef0a488a13203667bed4e6565403e1";

    final String key3 = "Device-V";
    final String value3 = "aU9TXzEwLjEuMV8xMjQyKjIyMDhfMTAwMDAxMDAwX2lQaG9uZTcsMQ==";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_v_china_top;
    }

    @Override
    protected void initView(View itemView) {
        listView = (ListView) itemView.findViewById(R.id.china_top_lv);
    }

    @Override
    protected void initData() {
        retrofit = new Retrofit.Builder().baseUrl("http://mapiv2.yinyuetai.com/").addConverterFactory(GsonConverterFactory.create()).build();
        TopService service = retrofit.create(TopService.class);
        Map<String,String> map = new HashMap<>();
        map.put(key1,value1);
        map.put(key2,value2);
        map.put(key3,value3);
        Call<ChinaTopBean> call = service.startRequest(Url,map);
        call.enqueue(new Callback<ChinaTopBean>() {
            @Override
            public void onResponse(Call<ChinaTopBean> call, Response<ChinaTopBean> response) {
                final ChinaTopBean bean = response.body();
                ChinaListViewTopAdapter adapter = new ChinaListViewTopAdapter(mContext);
                adapter.setData(bean.getData().getVideos());
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(getContext(),ChinaActivity.class);
                        String id = bean.getData().getVideos().get(i).getVideoId()+"";
                        intent.putExtra("不爽",id);
                        getContext().startActivity(intent);
                    }
                });
            }
            @Override
            public void onFailure(Call<ChinaTopBean> call, Throwable t) {

            }
        });
    }
}
