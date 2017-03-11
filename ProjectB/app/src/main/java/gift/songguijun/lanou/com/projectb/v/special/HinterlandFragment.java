package gift.songguijun.lanou.com.projectb.v.special;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.HashMap;
import java.util.Map;

import gift.songguijun.lanou.com.projectb.R;
import gift.songguijun.lanou.com.projectb.base.BaseFragment;
import gift.songguijun.lanou.com.projectb.bean.SpecialBean;
import gift.songguijun.lanou.com.projectb.util.Constant;
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
    String Url = Constant.V_SPECIAL_HINTERLAND_URL;
    private Retrofit retrofit;
    final String key1 = Constant.HOMEKEY_ONE;
    final String value1 = Constant.HOMEVALUE_ONE;

    final String key2 = Constant.HOMEKEY_TWO;
    final String value2 = Constant.HOMEVALUE_TWO;

    final String key3 = Constant.HOMEKEY_THREE;
    final String value3 = Constant.HOMEVALUE_THREE;
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
                final SpecialBean bean = response.body();
                HinterlandListViewAdapter adapter = new HinterlandListViewAdapter(getContext());
                adapter.setData(bean.getData().getVideos());
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(getContext(),HinterLandActivity.class);
                        String title = bean.getData().getVideos().get(i).getTitle();
                        String url = bean.getData().getVideos().get(i).getUrl();
                        intent.putExtra("强大的标题",title);
                        intent.putExtra("强大的网址",url);
                        getContext().startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<SpecialBean> call, Throwable t) {

            }
        });



    }
}
