package gift.songguijun.lanou.com.okhttp.ok;

/**
 * Created by dllo on 17/2/17.
 * 网路请求结果接口
 */

public interface OnNetResultListener {

    void  onSucceessListener(String succeessStr);
    void onFailureListener(String errMsg);
}
