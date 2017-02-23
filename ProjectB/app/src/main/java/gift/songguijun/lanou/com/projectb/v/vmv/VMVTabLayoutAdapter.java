package gift.songguijun.lanou.com.projectb.v.vmv;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 17/2/22.
 */

public class VMVTabLayoutAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> data;
    private String title[] = {"内地", "港台", "欧美", "韩国","日本"};

    public void setData(ArrayList<Fragment> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public VMVTabLayoutAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data != null ? data.size():0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
