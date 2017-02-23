package gift.songguijun.lanou.com.projectb.v.program;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

import gift.songguijun.lanou.com.projectb.R;
import gift.songguijun.lanou.com.projectb.base.BaseFragment;
import gift.songguijun.lanou.com.projectb.bean.ProgramBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dllo on 17/2/22.
 */

public class ProgramFragment extends BaseFragment {
    private RecyclerView recyclerView;

    String Url = "http://mapiv2.yinyuetai.com/component/prefecture.json?type=5";
    private Retrofit retrofit;
    final String key1 = "App-Id";
    final String value1 = "10101045";

    final String key2 = "Device-Id";
    final String value2 = "f1ef0a488a13203667bed4e6565403e1";

    final String key3 = "Device-V";
    final String value3 = "aU9TXzEwLjEuMV8xMjQyKjIyMDhfMTAwMDAxMDAwX2lQaG9uZTcsMQ==";
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_v_program;
    }

    @Override
    protected void initView(View itemView) {
        recyclerView = (RecyclerView) itemView.findViewById(R.id.program_rv);

    }

    @Override
    protected void initData() {
        retrofit = new Retrofit.Builder().baseUrl("http://mapiv2.yinyuetai.com/").addConverterFactory(GsonConverterFactory.create()).build();
        ProgramService service = retrofit.create(ProgramService.class);
        Map<String,String> map = new HashMap<>();
        map.put(key1,value1);
        map.put(key2,value2);
        map.put(key3,value3);
        Call<ProgramBean>call = service.startRequest(Url,map);
        call.enqueue(new Callback<ProgramBean>() {
            @Override
            public void onResponse(Call<ProgramBean> call, Response<ProgramBean> response) {
                ProgramBean bean = response.body();
                ProgramAdapter adapter = new ProgramAdapter(getContext());
                adapter.setData(bean.getData());
                recyclerView.setAdapter(adapter);
                LinearLayoutManager maa = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(maa);
            }

            @Override
            public void onFailure(Call<ProgramBean> call, Throwable t) {

            }
        });


    }
}
