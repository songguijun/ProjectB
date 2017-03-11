package gift.songguijun.lanou.com.projectb.v.special;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.HashMap;
import java.util.Map;

import gift.songguijun.lanou.com.projectb.R;
import gift.songguijun.lanou.com.projectb.base.BaseFragment;
import gift.songguijun.lanou.com.projectb.bean.ImportBean;
import gift.songguijun.lanou.com.projectb.util.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dllo on 17/2/24.
 */

public class ImportFragment extends BaseFragment {
    private ListView listView;
    String Url = Constant.V_SPECIAL_IMPORT_URL;
    private Retrofit retrofit;
    final String key1 = Constant.HOMEKEY_ONE;
    final String value1 = Constant.HOMEVALUE_ONE;

    final String key2 = Constant.HOMEKEY_TWO;
    final String value2 = Constant.HOMEVALUE_TWO;

    final String key3 = Constant.HOMEKEY_THREE;
    final String value3 = Constant.HOMEVALUE_THREE;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_special_import;
    }

    @Override
    protected void initView(View itemView) {
        listView = (ListView) itemView.findViewById(R.id.import_lv);
    }

    @Override
    protected void initData() {
        retrofit = new Retrofit.Builder().baseUrl("http://mapiv2.yinyuetai.com/").addConverterFactory(GsonConverterFactory.create()).build();
        ImportService service = retrofit.create(ImportService.class);
        Map<String,String> map = new HashMap<>();
        map.put(key1,value1);
        map.put(key2,value2);
        map.put(key3,value3);
        Call<ImportBean> call = service.startRequest(Url,map);
        call.enqueue(new Callback<ImportBean>() {
            @Override
            public void onResponse(Call<ImportBean> call, Response<ImportBean> response) {
                final ImportBean bean = response.body();
                ImportListViewAdapter adapter = new ImportListViewAdapter(getContext());
                adapter.setData(bean.getData().getVideos());
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent  = new Intent(getContext(),ImPortActivity.class);
                        String title = bean.getData().getVideos().get(i).getTitle();
                        String url = bean.getData().getVideos().get(i).getUrl();
                        intent.putExtra("标题",title);
                        intent.putExtra("网址",url);
                        getContext().startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<ImportBean> call, Throwable t) {

            }
        });
    }
}
