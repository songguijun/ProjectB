package gift.lin.lanou.com.mvpdemo1.model;

import gift.lin.lanou.com.mvpdemo1.presenter.ThePresenter;

/**
 * Created by dllo on 17/2/16.
 * 这是模型类
 * 在这里处理业务逻辑 : 获取数据 , 网络请求 , 逻辑判断
 */

public class TheModel {
    //p是调度者 , 来到Model里调度数据的返回
    private ThePresenter p ;

    public TheModel(ThePresenter p) {
        this.p = p;
    }


    /**
     * 1. Model处理数据
     */
    public void createString(){
        String resultStr = "经过复杂处理生成的一句话" ;
        /**
         * 2. 将数据传回给p
         *  将p定义在Model里 , 通过构造方法实例化P
         *  去P的类中定义接收方法
         */
        p.setResultStr(resultStr);
        /**
         * Activity要显示的内容
         *  M和P协作产生的 , 会将处理部分放在m中
         *  MVP的最终目的 :
         *  1 : 保证最终肯定会有东西到达Activity显示
         *  2 : 让Activity瘦身 , 将业务逻辑中抽离
         *
         *  注 : 在企业中 这些操作就是为了便于维护和修改
         */

    }

}
