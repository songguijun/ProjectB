package gift.songguijun.lanou.com.projectb.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

/**
 * Created by dllo on 17/2/21.
 */

public class VolleyManager {
    private  static  VolleyManager instance;
    private RequestQueue queue;
    private VolleyManager(){
        queue = getQueue();
    }
    public static VolleyManager getInstance(){
        if (instance == null){
            synchronized (VolleyManager.class){
                if (instance == null){
                    instance = new VolleyManager();
                }
            }

        }
        return instance;
    }
    private RequestQueue getQueue(){
        if (queue == null){
            queue = Volley.newRequestQueue(MyApp.getContext());
        }
        return queue;
    }
    private void addQueue(Request request){
        queue.add(request);
    }
    private void _startRequestWithHeader(String url, final Map<String, String> headers, final OnNetResultListener listener){
        StringRequest sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onSucceess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onFailure(error.getMessage());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers;
            }
        };

        addQueue(sr);
    }


    public void startRequestWithHeader(String url, Map<String,String> headers, OnNetResultListener listener){
        _startRequestWithHeader(url,headers,listener);
    }


}
