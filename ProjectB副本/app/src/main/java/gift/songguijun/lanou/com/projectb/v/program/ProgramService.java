package gift.songguijun.lanou.com.projectb.v.program;

import java.util.Map;

import gift.songguijun.lanou.com.projectb.bean.ProgramBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Url;

/**
 * Created by dllo on 17/2/22.
 */

public interface ProgramService {
    @GET
    Call<ProgramBean> startRequest(@Url String endUrl, @HeaderMap Map<String, String> headers);
}
