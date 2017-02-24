package gift.songguijun.lanou.com.projectb.subscibe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gift.songguijun.lanou.com.projectb.R;
import gift.songguijun.lanou.com.projectb.base.BaseFragment;

/**
 * Created by dllo on 17/2/20.
 */

public class SubscibeFragment extends BaseFragment {
    private TabLayout tabSubscibe ;
    private ViewPager vpSubscibe ;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_subscibe;
    }

    @Override
    protected void initView(View itemView) {

    }

    @Override
    protected void initData() {

    }
}
