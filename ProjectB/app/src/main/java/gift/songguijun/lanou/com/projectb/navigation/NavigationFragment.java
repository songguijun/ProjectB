package gift.songguijun.lanou.com.projectb.navigation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import gift.songguijun.lanou.com.projectb.volley.NavigationBean;
import gift.songguijun.lanou.com.projectb.R;

/**
 * Created by dllo on 17/2/20.
 */

public class NavigationFragment extends Fragment {
    private GridView gridView;
    final String headUrl = "http://mapiv2.yinyuetai.com/navigation/config.json";
    final String key1 = "App-Id";
    final String value1 = "10101045";
    final String key2 = "Device-Id";
    final String value2 = "f1ef0a488a13203667bed4e6565403e1";
    final String key3 = "Device-V";
    final String value3 = "aU9TXzEwLjEuMV8xMjQyKjIyMDhfMTAwMDAxMDAwX2lQaG9uZTcsMQ==";
    RequestQueue queue;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_novigation,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        queue = Volley.newRequestQueue(getContext());
        gridView = (GridView) view.findViewById(R.id.gird_one);
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, headUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String s = response.toString();
                Gson gson = new Gson();
                NavigationBean bean = gson.fromJson(s,NavigationBean.class);
                NavigationAdapter adapter = new NavigationAdapter(getContext());
                adapter.setData(bean.getData().getContents());
                gridView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String , String>map = new HashMap<>();
                map.put(key1,value1);
                map.put(key2,value2);
                map.put(key3,value3);
                return map;
            }
        };
        queue.add(jor);
    }
}
