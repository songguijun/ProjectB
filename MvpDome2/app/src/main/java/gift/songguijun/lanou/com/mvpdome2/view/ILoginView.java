package gift.songguijun.lanou.com.mvpdome2.view;

/**
 * Created by dllo on 17/2/16.
 */

public interface ILoginView {
 //p将接受到的结果 中转给View View的接受方法
    void onSucceess();
    void onFailues(String errMsg);
    //容错性的操作
    //加载过的显示
    void showProgress();
    void getProgress();
}
