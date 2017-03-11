package gift.songguijun.lanou.com.projectb.mine;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import gift.songguijun.lanou.com.projectb.R;
import gift.songguijun.lanou.com.projectb.base.BaseFragment;

/**
 * Created by dllo on 17/2/20.
 */

public class MineFragment extends BaseFragment {
    private LinearLayout llCollect ;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View itemView) {
        llCollect = (LinearLayout) itemView.findViewById(R.id.mine_collect_ll);
    }

    @Override
    protected void initData() {
        llCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity() , CollectActivity.class);
                startActivity(intent);
            }
        });

    }
}
