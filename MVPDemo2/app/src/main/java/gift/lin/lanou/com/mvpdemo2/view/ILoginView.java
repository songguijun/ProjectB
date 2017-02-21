package gift.lin.lanou.com.mvpdemo2.view;

/**
 * Created by dllo on 17/2/16.
 */

public interface ILoginView {

    // P将接收到的结果中转传递给View
    // View的接收方法

    void onSuccess();

    void onFailure(String errMsg);


    //容错性操作
    //加载过程显示
    void showProgress();
    void hideProgress();

}
