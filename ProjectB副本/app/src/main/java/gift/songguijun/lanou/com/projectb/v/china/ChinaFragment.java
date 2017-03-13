package gift.songguijun.lanou.com.projectb.v.china;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;

import gift.songguijun.lanou.com.projectb.R;
import gift.songguijun.lanou.com.projectb.base.BaseFragment;

/**
 * Created by dllo on 17/2/22.
 */

public class ChinaFragment extends BaseFragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment>data;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_v_cv;
    }

    @Override
    protected void initView(View itemView) {
        tabLayout = (TabLayout) itemView.findViewById(R.id.china_tb);
        viewPager = (ViewPager) itemView.findViewById(R.id.china_vp);
    }

    @Override
    protected void initData() {
        data = new ArrayList<>();
      ChinaTabLayoutAdapter adapter = new ChinaTabLayoutAdapter(getChildFragmentManager());
        data.add(new ChinaTopFragment());
        data.add(new ChinaHotFragment());
        adapter.setData(data);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
