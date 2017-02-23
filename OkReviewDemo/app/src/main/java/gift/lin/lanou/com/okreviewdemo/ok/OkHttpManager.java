package gift.lin.lanou.com.okreviewdemo.ok;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by dllo on 17/2/17.
 * OkHttp封装单例类
 */

public class OkHttpManager {
    // 1.私有构造方法
    // 2.1.定义当前类静态对象
    // 2.2.对外提供金泰的获取对象
    // 在其中提供单例的写法

    private static OkHttpManager instance;

    public static OkHttpManager getInstance() {
        if (instance == null) {
            instance = new OkHttpManager();
        }
        return instance;
    }

    private OkHttpClient client ;
    private Handler handler ;

    private OkHttpManager() {
        // 获取okHttpClient

        getOkHttpClient();
        handler = new Handler();


    }

    private void getOkHttpClient() {
        client = new OkHttpClient.Builder().build();

    }

    //封装在内部的操作方法
    private void _startGet(final String url  , final  OnNetResultListener listener){
        //网络get请求
        //将网络请求的代码写在网络操作类内部
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Request request = new Request.Builder().url(url).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, final IOException e) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                listener.onFailureListener(e.getMessage());
                            }
                        });

                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    listener.onSuccessListener(response.body().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });
            }
        }).start();

    }

    private void _startHeader(final String url ,final Map<String , String> headers , final OnNetResultListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
              //  Request request = new Request.Builder().url(url).addHeader().build();
                Request.Builder builder = new Request.Builder();
                builder.url(url);
                //遍历map
                Set set = headers.keySet();
                // 迭代器
                Iterator iterator = set.iterator();
                while (iterator.hasNext()){
                    String key = (String) iterator.next();
                    String valye = headers.get(key);
                    builder.addHeader(key , valye);

                }
                final Request request = builder.build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, final IOException e) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                listener.onFailureListener(e.getMessage());
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        final String str = response.body().string();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                listener.onSuccessListener(str);
                            }
                        });
                    }
                });
            }
        }).start();

    }

    private void _startPost(final String url  ,final Map<String , String> body, final  OnNetResultListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                FormBody.Builder builder = new FormBody.Builder();
                Set set = body.keySet();
                Iterator iterator = set.iterator();
                while (iterator.hasNext()){
                    String key = (String) iterator.next();
                    String value = body.get(key);
                    builder.add(key , value);
                }
                RequestBody requestBody = builder.build();
                Request request = new Request.Builder().url(url).post(requestBody).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, final IOException e) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                listener.onFailureListener(e.getMessage());
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    listener.onSuccessListener(response.body().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });
            }
        }).start();

    }
    //提供给外部的操作方法
    public void  startGet(String url , OnNetResultListener listener){
        _startGet(url , listener);
    }
    public void startHeader(String url , Map<String , String>headers , OnNetResultListener listener){
        _startHeader(url , headers , listener);

    }
    public void startPost(String url , Map<String , String>body , OnNetResultListener listener){
        _startPost(url , body , listener);
    }



}
