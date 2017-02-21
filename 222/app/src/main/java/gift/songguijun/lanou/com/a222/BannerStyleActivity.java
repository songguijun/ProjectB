package gift.songguijun.lanou.com.a222;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

public class BannerStyleActivity extends AppCompatActivity {
    Banner banner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_style);
        banner = (Banner) findViewById(R.id.banner);

        //默认是CIRCLE_INDICATOR
        banner.setImages(App.images)
                .setBannerTitles(App.titles)
                .setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
                .setImageLoader(new GlideImageLoader())
                .start();
    }


}
