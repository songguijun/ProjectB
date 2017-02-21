package gift.songguijun.lanou.com.mvpdome2.persenter;

/**
 * Created by dllo on 17/2/16.
 */

public interface ILoginPersenter {
    //View 想p 传送请求
    void startLogin(String name,String psw);
    //Modei 将结果传给了 p p接受结果的方法
    void onSucceess();
    void onFailues(String errMsg);

}
