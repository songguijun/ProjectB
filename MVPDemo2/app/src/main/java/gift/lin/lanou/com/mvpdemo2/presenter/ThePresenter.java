package gift.lin.lanou.com.mvpdemo2.presenter;

import android.os.Handler;
import android.os.Message;

import gift.lin.lanou.com.mvpdemo2.model.ILoginModel;
import gift.lin.lanou.com.mvpdemo2.model.TheModel;
import gift.lin.lanou.com.mvpdemo2.view.ILoginView;
import gift.lin.lanou.com.mvpdemo2.view.MainActivity;

/**
 * Created by dllo on 17/2/16.
 */

public class ThePresenter implements ILoginPresrnter {
    private ILoginView view ;
    private ILoginModel model ;

    // Model中登陆的操作是在子线程完成
    // 现在要将结果传回主线程
    // 线程间的通信 - 使用Handler
    private Handler handler ;
    private static final int SUCCESS_CODE = 1 ;
    private static final int FAILURE_CODE = 2 ;

    public ThePresenter(final ILoginView view) {
        this.view = view;

        model = new TheModel(this);

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                switch (message.what){
                    case SUCCESS_CODE :
                        // 在主线程中接收到成功的通知
                        // 传给View
                        view.onSuccess();
                        //登陆结果产生 , 登陆过程消失
                        view.hideProgress();
                        break;
                    case FAILURE_CODE :
                        String errMsg = (String) message.obj;
                        view.onFailure(errMsg);
                        view.hideProgress();
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void startLogin(String name, String num) {
        view.showProgress();
        if (!name.isEmpty() && !num.isEmpty()){
        model.startLogin(name , num);}else {
            view.onFailure("请输入账号或密码");
            view.hideProgress();
        }
    }

    @Override
    public void onSuccess() {
        //在子线程中执行的 , 要通过Handler机制发回主线程
        handler.sendEmptyMessage(SUCCESS_CODE);

    }

    @Override
    public void onFailure(String errMsg) {
        //由于失败是有失败信息要发送
        //handler进行传值
        Message message = Message.obtain();
        message.what = FAILURE_CODE ;
        message.obj = errMsg ;
        handler.sendMessage(message);
    }
}
