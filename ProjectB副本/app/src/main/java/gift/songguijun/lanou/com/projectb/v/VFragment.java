package gift.songguijun.lanou.com.projectb.v;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import gift.songguijun.lanou.com.projectb.R;
import gift.songguijun.lanou.com.projectb.base.BaseFragment;
import gift.songguijun.lanou.com.projectb.v.china.ChinaFragment;
import gift.songguijun.lanou.com.projectb.v.program.ProgramFragment;
import gift.songguijun.lanou.com.projectb.v.special.SpecialFragment;
import gift.songguijun.lanou.com.projectb.v.vmv.MvFragment;

/**
 * Created by dllo on 17/2/20.
 */

public class VFragment extends BaseFragment implements View.OnClickListener {
    private RadioButton rbtn_program,rbtn_MV,rbtn_special,rbtn_CV;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_v;
    }

    @Override
    protected void initView(View itemView) {
        rbtn_program = (RadioButton) itemView.findViewById(R.id.rbtn_program_v);
        rbtn_MV = (RadioButton) itemView.findViewById(R.id.rbtn_MV_v);
        rbtn_special = (RadioButton) itemView.findViewById(R.id.rbtn_special_v);
        rbtn_CV = (RadioButton) itemView.findViewById(R.id.rbtn_CV);
        rbtn_program.setOnClickListener(this);
        rbtn_MV.setOnClickListener(this);
        rbtn_special.setOnClickListener(this);
        rbtn_CV.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.replace_v, new MvFragment());
        transaction.commit();

    }

    @Override
    public void onClick(View view) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (view.getId()){
            case R.id.rbtn_program_v:
                transaction.replace(R.id.replace_v,new ProgramFragment());
                break;
            case R.id.rbtn_MV_v:
                transaction.replace(R.id.replace_v,new MvFragment());
                break;
            case R.id.rbtn_special_v:
                transaction.replace(R.id.replace_v,new SpecialFragment());
                break;
            case R.id.rbtn_CV:
                transaction.replace(R.id.replace_v,new ChinaFragment());
                break;
        }
        transaction.commit();
    }
}
