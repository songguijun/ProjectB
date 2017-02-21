package gift.songguijun.lanou.com.mvpdome2.view;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import gift.songguijun.lanou.com.mvpdome2.R;
import gift.songguijun.lanou.com.mvpdome2.persenter.ILoginPersenter;
import gift.songguijun.lanou.com.mvpdome2.persenter.LoginPersenter;

public class MainActivity extends AppCompatActivity implements ILoginView {
    private EditText editTextName, editTextPsw;
    private Button button;
    private ILoginPersenter p;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        p = new LoginPersenter(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("加载中~~~~~~~");
        dialog.setCancelable(false);
        init();
    }

    private void init() {
        editTextName = (EditText) findViewById(R.id.et1);
        editTextPsw = (EditText) findViewById(R.id.et2);
        button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString();
                String psw = editTextPsw.getText().toString();
                p.startLogin(name, psw);
            }
        });
    }

    @Override
    public void onSucceess() {
        //跳转到 主界面
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailues(String errMsg) {
        Toast.makeText(this, errMsg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showProgress() {
       dialog.show();
    }

    @Override
    public void getProgress() {
      dialog.dismiss();
    }
}
