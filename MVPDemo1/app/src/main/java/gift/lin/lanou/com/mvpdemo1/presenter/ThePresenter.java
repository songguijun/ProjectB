package gift.lin.lanou.com.mvpdemo1.presenter;

import gift.lin.lanou.com.mvpdemo1.MainActivity;
import gift.lin.lanou.com.mvpdemo1.model.TheModel;

/**
 * Created by dllo on 17/2/16.
 */

public class ThePresenter {

    private MainActivity  view ;
    private TheModel model ;

    public ThePresenter(MainActivity view) {
        this.view = view;
        model = new TheModel(ThePresenter.this);
    }

    // 定义方法 : 接收Model传来的数据
    public void setResultStr(String str){
        /**
         * 3. 将数据返回给View
         * 将View对象定义在P的类里
         * 去View的类中定义接收方法
         * 注 : View就是Activity
         */
        view.setResultStr(str);

    }

    public void start(){
        /**
         * P接到了View的请求
         *  在P的类中定义Model对象 ,  并实例化
         *  调用model类中的处理方法 , 开始处理
         */
        model.createString();
    }

}
