package gift.songguijun.lanou.com.projectb.retrofit;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by dllo on 17/2/24.
 */

public interface RetrofitHttpService {
    @GET()
    Call<String> get(@Url String url , @QueryMap Map<String , String> parms , @Header("Cache-Time") String time);
    @FormUrlEncoded

    @POST()
    Call<String> post(@Url String url , @FieldMap Map<String , String> parms , @Header("Cache-Time") String time);

}
