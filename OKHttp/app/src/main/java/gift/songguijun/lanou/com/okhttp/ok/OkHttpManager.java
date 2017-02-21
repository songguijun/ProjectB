package gift.songguijun.lanou.com.okhttp.ok;

import android.os.Handler;

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
 * OKHttep 封装单利类
 */

public class OkHttpManager {
    /**
     * 私有构造方法
     * 定义当前类静态对象
     * 对外提供静态的获取对象方法在其中提供单利的写法
     */

    private static OkHttpManager instance;

    public static OkHttpManager getInstance() {
        if (instance == null) {
            instance = new OkHttpManager();

        }
        return instance;
    }

    private OkHttpClient client;
    private Handler handler;

    private OkHttpManager() {
        // 获取OKHttpClient
        getOkHttpClient();
        handler = new Handler();

    }

    private void getOkHttpClient() {
        client = new OkHttpClient.Builder().build();

    }

    //对内部
    private void _startGet(final String url,final OnNetResultListener lintener) {
        //网络请求类
        //将网络请求的代码写在网络操作类内部
        new Thread(new Runnable() {
            @Override
            public void run() {
                Request r = new Request.Builder().url(url).build();
                client.newCall(r).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, final IOException e) {
                     handler.post(new Runnable() {
                         @Override
                         public void run() {
                        lintener.onFailureListener(e.getMessage());
                         }
                     });
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        final String s = response.body().string();
                       handler.post(new Runnable() {
                           @Override
                           public void run() {
                            lintener.onSucceessListener(s);
                           }
                       });
                    }
                });
            }
        }).start();
    }

    private void _startHeader(final String url, final Map<String ,String> header, final OnNetResultListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
              //  Request r = new Request.Builder().url(url).addHeader().build();
                //遍历map
                Request.Builder buider = new Request.Builder();
                buider.url(url);
                Set set   = header.keySet();
                //迭代器
                Iterator iterator = set.iterator();
                while (iterator.hasNext()){
                    String Key = (String) iterator.next();
                    String value = header.get(Key);
                    buider.addHeader(Key,value);
                }
                final Request request =  buider.build();
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
                        final String s = response.body().string();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    listener.onSucceessListener(s);
                }
            });
                    }
                });
            }

        }).start();

    }
    private void _startPost(final String url, final Map<String , String>body, final OnNetResultListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                FormBody.Builder buider = new FormBody.Builder();
                RequestBody requestBody = buider.build();
                Set set = body.keySet();
                Iterator iterator = set.iterator();
                while (iterator.hasNext()){
                    String Key = (String) iterator.next();
                    String value = body.get(Key);
                    buider.add(Key,value);
                }
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
                                    listener.onSucceessListener(response.body().string());
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
    //对外
    //封装 对外提供使用方式 具体的实现细节封装起来
    public void startGet(String url , OnNetResultListener linstener) {
        _startGet(url,linstener);
    }
    public void startHeader(String url,Map<String,String>header,OnNetResultListener listener){
        _startHeader(url,header,listener);

    }
    public void startPost(String url,Map<String,String>body,OnNetResultListener listener){
        _startPost(url,body,listener);
    }
}
