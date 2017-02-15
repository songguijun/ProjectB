package gift.songguijun.lanou.com.projectb.util;

import android.util.Log;

/**
 * Created by dllo on 17/2/15.
 */

public final class L  {
    private L(){}
    private static final boolean IS_DEBUG = true;
    public static void i(String info){
        if (IS_DEBUG){
            Log.i("test",info);
        }
    }
}
