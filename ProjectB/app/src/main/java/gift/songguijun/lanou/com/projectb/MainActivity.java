package gift.songguijun.lanou.com.projectb;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import gift.songguijun.lanou.com.projectb.base.BaseActivity;
import gift.songguijun.lanou.com.projectb.home.HomeFragment;
import gift.songguijun.lanou.com.projectb.mine.MineFragment;
import gift.songguijun.lanou.com.projectb.navigation.NavigationFragment;
import gift.songguijun.lanou.com.projectb.subscibe.SubscibeFragment;
import gift.songguijun.lanou.com.projectb.v.VFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private RadioButton rbt_home,rbt_navigation,rbt_v,rbt_subscibe,rbt_mine;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        rbt_home = (RadioButton) findViewById(R.id.rbt_home);
        rbt_navigation = (RadioButton) findViewById(R.id.rbt_navigation);
        rbt_v = (RadioButton) findViewById(R.id.rbt_V_notice);
        rbt_subscibe = (RadioButton) findViewById(R.id.rbt_subscibe);
        rbt_mine = (RadioButton) findViewById(R.id.rbt_mine);
        rbt_home.setOnClickListener(this);
        rbt_navigation.setOnClickListener(this);
        rbt_v.setOnClickListener(this);
        rbt_subscibe.setOnClickListener(this);
        rbt_mine.setOnClickListener(this);
    }

    @Override
    protected void initDate() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.replace, new HomeFragment());
        transaction.commit();
    }

    @Override
    public void onClick(View view) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (view.getId()){
            case R.id.rbt_home:
                transaction.replace(R.id.replace,new HomeFragment());
                break;
            case R.id.rbt_navigation:
                transaction.replace(R.id.replace,new NavigationFragment());
                break;
            case R.id.rbt_V_notice:
                transaction.replace(R.id.replace,new VFragment());
                break;
            case R.id.rbt_subscibe:
                transaction.replace(R.id.replace, new SubscibeFragment());
                break;
            case R.id.rbt_mine:
                transaction.replace(R.id.replace,new MineFragment());
                break;
        }
        transaction.commit();
    }
}
