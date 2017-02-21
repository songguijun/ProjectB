package gift.songguijun.lanou.com.mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import gift.songguijun.lanou.com.mvp.presenter.ThePresenter;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;
    private ThePresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //实例化
        presenter = new ThePresenter(MainActivity.this);
        button = (Button) findViewById(R.id.btn);
        textView = (TextView) findViewById(R.id.tv);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击给TeXView 设置文字
                //textView.setText("adadadasdadasd");
            presenter.start();
            }
        });

    }
    public void setResultStr(String str){
        /**
         * 想p发出请求
         * 要求 索取数据
         * p 定义p的对象在View中 并创建p的对象
         * 去p的类中定义处理方法
         */
       textView.setText(str);

    }
}
