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

import gift.songguijun.lanou.com.projectb.util.Constant;

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
    String Url = Constant.V_VMV_URL ;
    final String key1 = Constant.HOMEKEY_ONE;
    final String value1 = Constant.HOMEVALUE_ONE;

    final String key2 = Constant.HOMEKEY_TWO;
    final String value2 = Constant.HOMEVALUE_TWO;

    final String key3 = Constant.HOMEKEY_THREE;
    final String value3 = Constant.HOMEVALUE_THREE;



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
        retrofit = new Retrofit.Builder().baseUrl(Constant.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
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
