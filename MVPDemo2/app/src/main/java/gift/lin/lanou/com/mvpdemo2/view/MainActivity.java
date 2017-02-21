package gift.lin.lanou.com.mvpdemo2.view;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import gift.lin.lanou.com.mvpdemo2.R;
import gift.lin.lanou.com.mvpdemo2.presenter.ILoginPresrnter;
import gift.lin.lanou.com.mvpdemo2.presenter.ThePresenter;

public class MainActivity extends AppCompatActivity implements ILoginView{
    private EditText etName , etNum ;
    private Button button ;
    private ProgressDialog dialog;

    private ILoginPresrnter presrnter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presrnter = new ThePresenter(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("loading.....");
        dialog.setCancelable(false);
        init();
    }

    private void init() {
        etName = (EditText) findViewById(R.id.et_name);
        etNum = (EditText) findViewById(R.id.et_num);
        button = (Button) findViewById(R.id.btn);

        /**
         * 登录 :
         * 服务器层 :
         *  获取到账号和密码 验证是否存在该账号
         *                验证密码是否正确
         *
         *  代码层 :
         *  是否有输入账号和密码
         *  确定账号和密码的规则
         *  以上操作都是 逻辑判断代码(业务代码)
         */
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String num = etNum.getText().toString();
                presrnter.startLogin(name , num);
            }
        });

    }

    @Override
    public void onSuccess() {
        //登陆成功 跳转到主界面
        Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(String errMsg) {
        Toast.makeText(this, errMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        dialog.show();
    }

    @Override
    public void hideProgress() {
        dialog.dismiss();
    }
}
