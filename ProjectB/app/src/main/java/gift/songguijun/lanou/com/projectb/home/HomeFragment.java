package gift.songguijun.lanou.com.projectb.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gift.songguijun.lanou.com.projectb.R;
import gift.songguijun.lanou.com.projectb.util.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dllo on 17/2/20.
 */

public class HomeFragment extends Fragment implements XBanner.XBannerAdapter{
    private ListView lvHome ;
    private Retrofit retrofit ;
    private List<String> dataPic ;
    private List<String> dataText ;

    private XBanner xBanner ;
    private View viewBanner;
    private GridView gvHead ;
    private View viewHead;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home,container,false);
        viewHead = LayoutInflater.from(getContext()).inflate(R.layout.home_head , container , false);
        gvHead = (GridView) viewHead.findViewById(R.id.gv_home_head);
        viewBanner = LayoutInflater.from(getContext()).inflate(R.layout.banner , container , false);
        xBanner = (XBanner) viewBanner.findViewById(R.id.banner);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvHome = (ListView) view.findViewById(R.id.listview_home);
        dataPic = new ArrayList<>();
        dataText = new ArrayList<>();
        useListView();

    }

    private void useListView() {
        final String key1 = Constant.HOMEKEY_ONE;
        final String value1 = Constant.HOMEVALUE_ONE;

        final String key2 = Constant.HOMEKEY_TWO;
        final String value2 = Constant.HOMEVALUE_TWO;

        final String key3 = Constant.HOMEKEY_THREE;
        final String value3 = Constant.HOMEVALUE_THREE;
        retrofit = new Retrofit.Builder().baseUrl(Constant.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
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
                for (int i = 0; i < bean.getData().size(); i++) {
                    dataPic.add(bean.getData().get(0).getData().get(i).getPosterPic());
                    dataText.add(bean.getData().get(0).getData().get(i).getTitle());
                }
                xBanner.setData(dataPic , dataText);
                xBanner.setmAdapter(HomeFragment.this);
                xBanner.setmAutoPalyTime(3000);
                xBanner.setPoinstPosition(XBanner.RIGHT);
                xBanner.setPointsIsVisible(true);
                lvHome.addHeaderView(viewBanner);
                HomeHeadAdapter homeHeadAdapter = new HomeHeadAdapter(getContext());
                homeHeadAdapter.setData(list);
                gvHead.setAdapter(homeHeadAdapter);
                lvHome.addHeaderView(viewHead);


            }

            @Override
            public void onFailure(Call<HomeBean> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBanner(XBanner banner, View view, int position) {
        Glide.with(getContext()).load(dataPic.get(position)).into((ImageView) view);
    }

    @Override
    public void onResume() {
        super.onResume();
        xBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        xBanner.stopAutoPlay();
    }
}
