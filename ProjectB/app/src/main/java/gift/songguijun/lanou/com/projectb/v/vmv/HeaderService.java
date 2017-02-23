package gift.songguijun.lanou.com.projectb.v.vmv;

import java.util.Map;

import gift.songguijun.lanou.com.projectb.bean.VMVBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Url;

/**
 * Created by dllo on 17/2/22.
 */

public interface HeaderService {
    @GET
    Call<VMVBean> startRequest(@Url String endUrl, @HeaderMap Map<String, String> headers);
}
