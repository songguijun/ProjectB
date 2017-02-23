package gift.lin.lanou.com.mvpdemo2.model;

import gift.lin.lanou.com.mvpdemo2.presenter.ILoginPresrnter;

/**
 * Created by dllo on 17/2/16.
 */

public class TheModel implements ILoginModel {

    // 定义P来传递结果
    private ILoginPresrnter presrnter ;

    public TheModel(ILoginPresrnter presrnter) {
        this.presrnter = presrnter;
    }

    @Override
    public void startLogin(final String name, final String num) {
        // 为了模拟登陆效果 , 休眠2秒
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    // 1.验证账号和密码
                    if (name.equals("123") && num.equals("456")) {
                        //输入的是测试账号 , 符合
                        presrnter.onSuccess();
                    }else {
                        //输入的账号不对
                        presrnter.onFailure("账号或密码不对");
                    }



                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
