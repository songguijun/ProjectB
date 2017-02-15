package gift.songguijun.lanou.com.projectb.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by dllo on 17/2/15.
 * 基类Activity
 */

/**
 * 作者 宋贵君
 *
 */

public abstract class BaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initView();
        initDate();
    }
    protected abstract int getLayout();
    protected abstract void initView();
    protected abstract void initDate();

    //精简 findViewById
    protected <V extends View> V byView(int resId){
        return (V) findViewById(resId);
    }
    //精简跳转
    protected void goTo (Class<? extends BaseActivity>to){
        Intent intent = new Intent();
        intent.setClass(this,to);
        startActivity(intent);
        //界面跳转动画

    }

}
