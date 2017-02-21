package gift.songguijun.lanou.com.mvp.model;

import gift.songguijun.lanou.com.mvp.presenter.ThePresenter;

/**
 * Created by dllo on 17/2/16
 * 这是模型类 在这里处理业务逻辑 获取数据 网络请求 逻辑判断.
 */

public class TheModel {
    //p是调度者  来到 Modle 调度数据的返回
    private ThePresenter p;

    public TheModel(ThePresenter p) {
        this.p = p;
    }

    public void createString (){
        String resultStr = "经过复杂处理的一句话";
        /**
         * 将数据传回p
         * 将p定义在modle 中 通过垢找方法实例化p的对象
         * 去p的类中定义接受的方法
         */
        p.setResultStr(resultStr);
        /**
         * Activity 要显示的内容
         * M和 P 协作生产的内 会将处理部分放在M中
         * MVP的最中目的
         * 保证会有定西到达Activity显示
         * 让 activity 瘦身 将业务逻辑从中撤离
         * 
         */
    }
}
