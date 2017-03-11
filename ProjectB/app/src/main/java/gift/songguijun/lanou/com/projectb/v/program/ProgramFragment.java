package gift.songguijun.lanou.com.projectb.v.program;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

import gift.songguijun.lanou.com.projectb.R;
import gift.songguijun.lanou.com.projectb.base.BaseFragment;
import gift.songguijun.lanou.com.projectb.bean.ProgramBean;
import gift.songguijun.lanou.com.projectb.util.Constant;
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

    String Url = Constant.V_PROGRAM_URL;
    private Retrofit retrofit;
    final String key1 = Constant.HOMEKEY_ONE;
    final String value1 = Constant.HOMEVALUE_ONE;

    final String key2 = Constant.HOMEKEY_TWO;
    final String value2 = Constant.HOMEVALUE_TWO;

    final String key3 = Constant.HOMEKEY_THREE;
    final String value3 = Constant.HOMEVALUE_THREE;
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
        retrofit = new Retrofit.Builder().baseUrl(Constant.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
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
