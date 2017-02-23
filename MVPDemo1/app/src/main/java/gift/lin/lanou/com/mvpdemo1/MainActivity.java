package gift.lin.lanou.com.mvpdemo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import gift.lin.lanou.com.mvpdemo1.presenter.ThePresenter;

public class MainActivity extends AppCompatActivity {

    private Button button ;
    private TextView textView ;
    private ThePresenter p ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //实例化P对象
        p = new ThePresenter(MainActivity.this);

        button = (Button) findViewById(R.id.btn);
        textView = (TextView) findViewById(R.id.tv);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // textView.setText("我是AAA");
                /**
                 * 4.向P发出请求
                 *  要求索取数据
                 *  定义P的对象在View类中
                 *  去P的类中定义处理方法
                 */
                p.start();

            }
        });

    }
    public void setResultStr(String str){
        textView.setText(str);
    }

}
