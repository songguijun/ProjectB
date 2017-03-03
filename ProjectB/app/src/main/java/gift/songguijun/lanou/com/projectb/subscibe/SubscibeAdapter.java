package gift.songguijun.lanou.com.projectb.subscibe;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 17/2/24.
 */

public class SubscibeAdapter extends FragmentPagerAdapter{
    private ArrayList<Fragment> data ;
    private String title[] = {"全部"} ;

    public SubscibeAdapter setData(ArrayList<Fragment> data) {
        this.data = data;
        notifyDataSetChanged();
        return this;
    }

    public SubscibeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
