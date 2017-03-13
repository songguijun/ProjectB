package gift.songguijun.lanou.com.projectb.v.china;

import java.util.Map;

import gift.songguijun.lanou.com.projectb.bean.ChinaHotBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Url;

/**
 * Created by dllo on 17/2/23.
 */

public interface HotService {
    @GET
    Call<ChinaHotBean> startRequest(@Url String endUrl, @HeaderMap Map<String, String> headers);
}
