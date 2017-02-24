package gift.songguijun.lanou.com.projectb.v.special;

import java.util.Map;

import gift.songguijun.lanou.com.projectb.bean.SpecialBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Url;

/**
 * Created by dllo on 17/2/24.
 */

public interface HinterlandService  {
    @GET
    Call<SpecialBean> startRequest(@Url String endUrl, @HeaderMap Map<String, String> headers);
}
