package gift.songguijun.lanou.com.projectb.video;

import java.util.Map;

import gift.songguijun.lanou.com.projectb.bean.VideoBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Url;

/**
 * Created by dllo on 17/2/28.
 */

public interface VideoService {
    @GET
    Call<VideoBean> startRequest(@Url String endUrl, @HeaderMap Map<String, String> headers);
}
