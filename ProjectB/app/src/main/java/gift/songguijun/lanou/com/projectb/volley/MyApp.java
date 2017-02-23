package gift.songguijun.lanou.com.projectb.volley;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 17/2/21.
 */

public class MyApp extends Application {
    private static Context context;
    public static Context getContext(){
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
