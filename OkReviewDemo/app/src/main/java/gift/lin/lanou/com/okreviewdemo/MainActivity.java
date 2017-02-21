package gift.lin.lanou.com.okreviewdemo;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnGetSimple , btnGetAsynv , btnGetHeader , btnPost ;
    private TextView textView ;
    private ProgressDialog dialog ;
    private OkHttpClient okHttpClient ;

    /**
     * 1.OkHttp是和HttpURLConnection是一个级别的
     *   okHttp需要在线程中进行 , 因为Android4.0以后
     *   规定网络操作必须在线程中执行
     *
     * 2. 同步和异步
     *   使用同步的情况比较少 : 支付 , 登录 , 注册 必须获取到结果
     *   异步使用的情况极多 , 正常的界面数据请求 , 刷新加载
     */


    private static final int SIMPLE_GET = 0 ;
    private static final int ASYNC_GET = 1 ;
    private static final int HEADER = 2 ;
    private static final int POST = 3 ;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            // 网络请求结果传回 , 进度取消显示
            dialog.dismiss();
            String str = (String) message.obj;
            switch (message.what){
                case SIMPLE_GET :
                    textView.setText(str);
                    break;
                case ASYNC_GET :
                    textView.setText(str);
                    break;
                case HEADER :
                    textView.setText(str);
                    break;
                case POST :
                    textView.setText(str);
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGetSimple = (Button) findViewById(R.id.simple_get_btn);
        btnGetAsynv = (Button) findViewById(R.id.asynv_get_btn);
        btnGetHeader = (Button) findViewById(R.id.header_get_btn);
        btnPost = (Button) findViewById(R.id.post_btn);
        textView = (TextView) findViewById(R.id.show_tv);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading....");
        dialog.setCancelable(false);

        initOK();


        btnGetSimple.setOnClickListener(this);
        btnGetAsynv.setOnClickListener(this);
        btnGetHeader.setOnClickListener(this);
        btnPost.setOnClickListener(this);

    }

    /**
     * 初始化okHttp
     */
    private void initOK() {
        okHttpClient = new OkHttpClient();
        //设计模式
        okHttpClient = new OkHttpClient.Builder()
                //连接超时时长 参数1 : 时间 , 参数2 : 单位
                .connectTimeout(10L , TimeUnit.SECONDS)
                .writeTimeout(10L , TimeUnit.SECONDS)
                .readTimeout(10 , TimeUnit.SECONDS)
                // 重要 : 连接失败是否尝试连接
                .retryOnConnectionFailure(true)
                //关于缓存
//                .cache(new Cache())
                .build();

    }

    @Override
    public void onClick(View view) {
        dialog.show();
        switch (view.getId()){
            case R.id.simple_get_btn :
                simpleGet();
                break;
            case R.id.asynv_get_btn :
                asyncGet();
                break;
            case R.id.header_get_btn :
                withHeader();
               // withHeadAnother();
                break;
            case R.id.post_btn :
                Post();
                break;



        }
    }

    /**
     * post请求
     */
    private void Post() {
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
                RequestBody body = new FormBody.Builder()
                        .add(key1 ,value1)
                        .add(key2,value2)
                        .add(key3,value3)
                        .add(key4,value4)
                        .add(key5,value5)
                        .build();
                Request request = new Request.Builder().url(postUrl).post(body).build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Message message = Message.obtain();
                        message.what = POST ;
                        message.obj = response.body().string();
                        handler.sendMessage(message);
                    }
                });
            }
        }).start();
    }

    /**
     * 带请求头的练习2
     *  如何查看和使用一些免费的接口
     */
//    private void withHeadAnother() {
//    }

    /**
     * 带请求头的请求
     */
    private void withHeader() {
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
                Request request = new Request.Builder().url(hadeUrl)
                        //设置该网络请求需要的请求头数据
                        .addHeader(key1 , Value1)
                        .addHeader(key2 , Value2)
                        .addHeader(key3 , Value3)
                        .build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Message msg = Message.obtain();
                        msg.what = HEADER ;
                        msg.obj = response.body().string();
                        handler.sendMessage(msg);
                    }
                });
            }
        }).start();
    }

    /**
     * 异步的Get请求
     */
    private void asyncGet() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Request request = new Request.Builder().url("http://c.3g.163.com/nc/article/list/T1348648517839/0-20.html").build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Message msg = Message.obtain();
                        msg.what = ASYNC_GET ;
                        msg.obj = response.body().string();
                        handler.sendMessage(msg);

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
                Request request = new Request.Builder().url("http://c.3g.163.com/nc/article/list/T1348648517839/0-20.html").build();
                try {
                    Response respone = okHttpClient.newCall(request).execute() ;
                    String str = respone.body().string() ;
                    Message msg = Message.obtain();
                    msg.what = SIMPLE_GET;
                    msg.obj = str ;
                    handler.sendMessage(msg);

                    //把结果发回主线程
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();


    }
}
