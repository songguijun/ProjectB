package gift.songguijun.lanou.com.projectb.v.vmv;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.HashMap;
import java.util.Map;

import gift.songguijun.lanou.com.projectb.R;
import gift.songguijun.lanou.com.projectb.base.BaseFragment;
import gift.songguijun.lanou.com.projectb.bean.VMVBean;
import gift.songguijun.lanou.com.projectb.video.MvVideoActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dllo on 17/2/22.
 */

public class VMVSelectedFragment extends BaseFragment {
    private ListView listView;
    private Retrofit retrofit;
    String Url = "http://mapiv2.yinyuetai.com/vchart/trend.json?area=ML&offset=0&size=20";
    final String key1 = "App-Id";
    final String value1 = "10101045";

    final String key2 = "Device-Id";
    final String value2 = "f1ef0a488a13203667bed4e6565403e1";

    final String key3 = "Device-V";
    final String value3 = "aU9TXzEwLjEuMV8xMjQyKjIyMDhfMTAwMDAxMDAwX2lQaG9uZTcsMQ==";



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_v_mv_selected;
    }

    @Override
    protected void initView(View itemView) {
        listView = (ListView) itemView.findViewById(R.id.v_mv_lv);
    }

    @Override
    protected void initData() {
        retrofit = new Retrofit.Builder().baseUrl("http://mapiv2.yinyuetai.com/").addConverterFactory(GsonConverterFactory.create()).build();
        HeaderService service = retrofit.create(HeaderService.class);
        Map<String,String>map = new HashMap<>();
        map.put(key1,value1);
        map.put(key2,value2);
        map.put(key3,value3);
        Call<VMVBean>call = service.startRequest(Url,map);
        call.enqueue(new Callback<VMVBean>() {
            @Override
            public void onResponse(Call<VMVBean> call, Response<VMVBean> response) {
                final VMVBean bean = response.body();
                VMVListViewAdapter adapter = new VMVListViewAdapter(getContext());
                adapter.setData(bean.getData().getVideos());
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(getContext(), MvVideoActivity.class);
                        String name = bean.getData().getVideos().get(i).getVideoId()+"";
                        intent.putExtra("我的天",name);
                        startActivity(intent);
                    }
                });
            }
            @Override
            public void onFailure(Call<VMVBean> call, Throwable t) {

            }
        });

    }
}
