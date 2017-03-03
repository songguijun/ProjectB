package gift.songguijun.lanou.com.projectb.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gift.songguijun.lanou.com.projectb.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dllo on 17/2/20.
 */

public class HomeFragment extends Fragment {
    private ListView lvHome ;
    private Retrofit retrofit ;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvHome = (ListView) view.findViewById(R.id.listview_home);
        useListView();

    }

    private void useListView() {
        final String key1 = "App-Id";
        final String value1 = "10201046";

        final String key2 = "Device-Id";
        final String value2 = "1862d8a3966fa7e1b4083af0c9456680";

        final String key3 = "Device-V";
        final String value3 = "QW5kcm9pZF81LjAuMl8xMDgwKjE5MjBfMTEwMDA5MDAwXyUyMlJlZG1pK05vdGUrMyUyMg==";
        retrofit = new Retrofit.Builder().baseUrl("http://mapiv2.yinyuetai.com/").addConverterFactory(GsonConverterFactory.create()).build();
        HomeListViewService service = retrofit.create(HomeListViewService.class);
        Map<String , String> map = new HashMap<>();
        map.put(key1 , value1);
        map.put(key2 , value2);
        map.put(key3 , value3);
        Call<HomeBean> call = service.startRequest("component/prefecture.json?&type=1" , map);
        call.enqueue(new Callback<HomeBean>() {
            @Override
            public void onResponse(Call<HomeBean> call, Response<HomeBean> response) {
                HomeBean bean = response.body();
                List<HomeBean.DataBeanX> list = bean.getData();
                    HomeListviewAdapter listAdapter = new HomeListviewAdapter(getContext());
                listAdapter.setData(list);
                    lvHome.setAdapter(listAdapter);


            }

            @Override
            public void onFailure(Call<HomeBean> call, Throwable t) {

            }
        });
    }
}
