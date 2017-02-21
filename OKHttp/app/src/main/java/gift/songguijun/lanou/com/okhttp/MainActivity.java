package gift.songguijun.lanou.com.okhttp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnOne,btnTwo,btnThree,btnFour;
    private TextView textView;
    private ProgressDialog dialog;
    private OkHttpClient okHttpClient;

    /**
     *
      OkHttp 是和HTTP.URLConnection 是一个级别的
     okHttp 需要在线程中进行 因为 安卓4.0以后 规定网络 操作必须在子线程中执行
     2 同步 和异步
     同步和 异步的 差别
     使用同步 的情况比较少 支付 登录 注册 必须获取
     *@param savedInstanceState
     *
     * 全局变量 和 局部变量(面试题)
     *
     */
    private static final int SIMPLE_GET = 0;
    private static final int ASYNC_GET = 1;
    private static final int HEADER = 2;
    private static final int POST = 3;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            //网络请求结束了  进度 取消
            String s = (String) message.obj;
            dialog.dismiss();
            switch (message.what){
                case SIMPLE_GET:
                    textView.setText(s);
                    break;
                case ASYNC_GET:
                    textView.setText(s);
                    break;
                case HEADER:
                    textView.setText(s);
                    break;
                case POST:
                    textView.setText(s);
                    break;
            }
            return false;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOne = (Button) findViewById(R.id.simple_get_btn);
        btnTwo = (Button) findViewById(R.id.asynv_get_btn);
        btnThree = (Button) findViewById(R.id.header_get_btn);
        btnFour = (Button) findViewById(R.id.post_btn);
        textView = (TextView) findViewById(R.id.show_tv);
        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnFour.setOnClickListener(this);


        dialog = new ProgressDialog(this);
        dialog.setMessage("加载中~~~~~");
        dialog.setCancelable(false);
        initOK();
    }

    /**
     * 初始化OKHttp
     */
    private void initOK() {
        //设计模式 创造者模式(Builder 模式 )
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10L, TimeUnit.SECONDS)
                .writeTimeout(10L,TimeUnit.SECONDS)
                .readTimeout(10L,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                //关于缓存的东西 在文件下载是 再讲
                .build();

    }

    @Override
    public void onClick(View view) {
        dialog.show();
        switch (view.getId()){
            case R.id.simple_get_btn:
                simpleGet();

                break;
            case R.id.asynv_get_btn:
                asyncGet();
                break;
            case R.id.header_get_btn:
                //带请求头的请求
                withHandler();
                break;
            case R.id.post_btn:
                postHader();
                break;
        }
    }

    private void postHader() {

        final String postUrl = "http://appserver.jnwtv.com:8080/jnwtv-client/movie/getmoviedetail";
        final String key1 = "account";
        final String value1 = "26690576370";
        final String key2 = "episodeNo";
        final String value2 = "1";
        final String key3 = "mId";
        final String value3 = "1193";
        final String key4 = "token";
        final String value4 = "2016101715493688672507925614387226690576370";
        final String key5 = "piId";
        final String value5 = "10037";

        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestBody body = new FormBody.Builder().add(key1 ,value1).add(key2,value2).add(key3,value3).add(key4,value4).add(key5,value5).build();
                Request r = new Request.Builder().url(postUrl).post(body).build();
                okHttpClient.newCall(r).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Message m = Message.obtain();
                        m.what = POST;
                        m.obj = response.body().string();
                        handler.sendMessage(m);
                    }
                });
            }
        }).start();
    }


    private void withHandler() {
        final String hadeUrl = "http://mapiv2.yinyuetai.com/component/prefecture.json?&type=1";
        final String key1  = "App-Id";
        final String Value1 = "10201046";

        final String key2  = "Device-Id";
        final String Value2 = "1862d8a3966fa7e1b4083af0c9456680";

        final String key3  = "Device-V";
        final String Value3 = "QW5kcm9pZF81LjAuMl8xMDgwKjE5MjBfMTEwMDA5MDAwXyUyMlJlZG1pK05vdGUrMyUyMg==";
        new Thread(new Runnable() {
            @Override
            public void run() {
                //由于该网络请求需要的请求陶数据
                Request r = new Request.Builder().url(hadeUrl).addHeader(key1,Value1).addHeader(key2,Value2).addHeader(key3,Value3).build();
                okHttpClient.newCall(r).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Message m = Message.obtain();
                        m.what = HEADER;
                        m.obj = response.body().string();
                        handler.sendMessage(m);
                    }
                });



            }
        }).start();
    }

    private void asyncGet() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder().url("http://c.3g.163.com/nc/article/list/T1348648517839/0-20.html").build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Message m = Message.obtain();
                        m.what = ASYNC_GET;
                        m.obj = response.body().string();
                        handler.sendMessage(m);
                    }
                });
            }
        }).start();
    }

    /**
     * 普通的get请求
     */
    private void simpleGet() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder().url("http://mapiv2.yinyuetai.com/vchart/trend.json?area=ML&offset=0&size=20").build();
                try {
                   Response response  = okHttpClient.newCall(request).execute();
                    String str =  response.body().toString();
                    Message m = Message.obtain();
                    m.what = SIMPLE_GET;
                    m.obj = str;
                    handler.sendMessage(m);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
}
