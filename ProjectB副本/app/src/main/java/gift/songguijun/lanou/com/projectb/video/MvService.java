package gift.songguijun.lanou.com.projectb.video;

import java.util.Map;

import gift.songguijun.lanou.com.projectb.home.MVBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Url;

/**
 * Created by dllo on 17/3/7.
 */

public interface MvService {
    @GET
    Call<MVBean> startRequest(@Url String endUrl, @HeaderMap Map<String, String> headers);
}
