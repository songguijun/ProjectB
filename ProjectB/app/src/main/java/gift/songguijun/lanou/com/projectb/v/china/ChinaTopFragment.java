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
import gift.songguijun.lanou.com.projectb.util.Constant;
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
    String Url = Constant.V_CHINATOP_URL;
    final String key1 = Constant.HOMEKEY_ONE;
    final String value1 = Constant.HOMEVALUE_ONE;

    final String key2 = Constant.HOMEKEY_TWO;
    final String value2 = Constant.HOMEVALUE_TWO;

    final String key3 = Constant.HOMEKEY_THREE;
    final String value3 = Constant.HOMEVALUE_THREE;

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
        retrofit = new Retrofit.Builder().baseUrl(Constant.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
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
