package gift.songguijun.lanou.com.mvp.presenter;

import gift.songguijun.lanou.com.mvp.MainActivity;
import gift.songguijun.lanou.com.mvp.model.TheModel;

/**
 * Created by dllo on 17/2/16.
 */

public class ThePresenter {
    private MainActivity view;
    private TheModel model;
    public ThePresenter(MainActivity view) {
        this.view = view;
        model = new TheModel(ThePresenter.this);
    }

    //定义的方法 接受Modle 传来的数据
    public void setResultStr(String str){
        /**
         * 将数据返回给View
         * 将View的对象定义在p的类里
         * 在View的类中定义接受的方法
         * View 就是MainActivity
         */
        view.setResultStr(str);

    }
    public void start(){
        /**
         * p 接到了 View 的请求
         * 在p的类中定义model对象 并实例化
         * 调用 model 类中的处理方法 开始处理
         */
        model.createString();
    }
}
