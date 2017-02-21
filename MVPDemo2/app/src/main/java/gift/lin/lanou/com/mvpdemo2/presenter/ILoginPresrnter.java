package gift.lin.lanou.com.mvpdemo2.presenter;

/**
 * Created by dllo on 17/2/16.
 */

public interface ILoginPresrnter {

    //View向P传递请求的方法

    void startLogin(String name , String num);


    //Model将结果传给P , P接收结果的方法

    void onSuccess();

    void onFailure(String errMsg);

    }
