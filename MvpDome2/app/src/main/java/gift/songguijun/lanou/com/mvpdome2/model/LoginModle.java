package gift.songguijun.lanou.com.mvpdome2.model;

import gift.songguijun.lanou.com.mvpdome2.persenter.ILoginPersenter;

/**
 * Created by dllo on 17/2/16.
 */

public class LoginModle implements ILoginModle {
    //定义p来传递结果
    private ILoginPersenter persenter;

    public LoginModle(ILoginPersenter persenter) {
        this.persenter = persenter;
    }

    @Override
    public void startLogin(final String name, final String psw) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    //1验证账号和密码
                    if (name.equals("123")&& psw.equals("456")){
                        //输入的账号符合
                        persenter.onSucceess();
                    }else {
                        //不符合
                        persenter.onFailues("不符合");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
