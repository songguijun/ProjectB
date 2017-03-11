package gift.songguijun.lanou.com.projectb.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by dllo on 17/2/22.
 */

/**
 * Fragment基类
 */

public abstract class BaseFragment extends Fragment {
    protected Context mContext;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(),container,false);
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //初始化各种组件
        initView(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化数据
        initData();
    }
    //返回布局文件的id
    //要绑定布局文件的id
    protected abstract int getLayoutId();
    protected abstract void  initView(View itemView);
    protected abstract void  initData();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
    protected void showToast(String text){
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();

    }


}
