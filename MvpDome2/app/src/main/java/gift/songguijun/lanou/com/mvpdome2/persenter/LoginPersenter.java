package gift.songguijun.lanou.com.mvpdome2.persenter;

import android.os.Handler;
import android.os.Message;

import gift.songguijun.lanou.com.mvpdome2.model.ILoginModle;
import gift.songguijun.lanou.com.mvpdome2.model.LoginModle;
import gift.songguijun.lanou.com.mvpdome2.view.ILoginView;

/**
 * Created by dllo on 17/2/16.
 */

public class LoginPersenter implements ILoginPersenter {
    private ILoginView view;
    private ILoginModle modle;
    private Handler handler;
    private static final int SUCCEESS_CODE = 1;
    private static final int FAILURE_CODE = 2;

    //Modle 中登录是在子线程完成
    //现在将结果传回主线程
    //线程间的通信使用 Handler
    public LoginPersenter(final ILoginView view) {
        this.view = view;
        modle = new LoginModle(this);
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                switch (message.what) {
                    case SUCCEESS_CODE:
                        //在主线程中接受成功的通知 传给View
                        view.onSucceess();
                        view.getProgress();
                        break;
                    case FAILURE_CODE:
                        String errMsg = (String) message.obj;
                        view.onFailues(errMsg);
                        view.getProgress();
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void startLogin(String name, String psw) {
        view.showProgress();
        if (!name.isEmpty() && !psw.isEmpty()) {
            modle.startLogin(name, psw);
        } else {
            view.onFailues("账号密码不能为空");
            view.getProgress();
        }
    }

    @Override
    public void onSucceess() {
        //在子线程中执行的 要通过Handler 机制发回组线程

        handler.sendEmptyMessage(SUCCEESS_CODE);
    }

    @Override
    public void onFailues(String errMsg) {
        //由于失败是有失败信息进行发送 handler 进行传值
        Message msg = new Message();
        msg.what = FAILURE_CODE;
        msg.obj = errMsg;
        handler.sendMessage(msg);
    }
}
