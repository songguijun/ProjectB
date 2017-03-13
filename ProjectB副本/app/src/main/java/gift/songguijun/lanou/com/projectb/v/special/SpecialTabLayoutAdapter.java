package gift.songguijun.lanou.com.projectb.v.special;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 17/2/24.
 */

public class SpecialTabLayoutAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment>data;
    private String title[] = {"内地专辑销量榜","进口专辑销量榜"};

    public void setData(ArrayList<Fragment> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public SpecialTabLayoutAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
