package gift.songguijun.lanou.com.projectb.v.china;

import java.util.Map;

import gift.songguijun.lanou.com.projectb.bean.ChinaActBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Url;

/**
 * Created by dllo on 17/3/10.
 */

public interface ChinaService  {
    @GET
    Call<ChinaActBean> startRequest(@Url String endUrl, @HeaderMap Map<String, String> headers);
}
