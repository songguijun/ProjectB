package gift.songguijun.lanou.com.projectb.video;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import gift.songguijun.lanou.com.projectb.R;
import gift.songguijun.lanou.com.projectb.home.MVBean;
import gift.songguijun.lanou.com.projectb.utils.DateTools;
import gift.songguijun.lanou.com.projectb.utils.DensityUtil;
import gift.songguijun.lanou.com.projectb.v.vmv.MvMostAdapter;
import gift.songguijun.lanou.com.projectb.v.vmv.MvOtherAdapter;
import gift.songguijun.lanou.com.projectb.v.vmv.MvPlayListAdapter;
import gift.songguijun.lanou.com.projectb.v.vmv.MvRelatedAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dllo on 17/3/7.
 */

public class MvVideoActivity extends FragmentActivity implements GestureDetector.OnGestureListener, View.OnClickListener, View.OnTouchListener {
    private SurfaceView surfaceView;
    private ImageView btnPause, btnPlayUrl, btn,btn_resylt;
    private ImageView btn_back;
    private SeekBar skbProgress;
    private TextView title_textView, time_tv, time_tv_video;
    private Player player;
    /**
     * 视频窗口的宽和高
     */
    private int playerWidth, playerHeight;
    /**
     * 视频播放时间,视频播放的总时长
     */
    private int playingTime, videoTotalTime;

    /**
     * 视频播放控件
     */
    /**
     * 手势改变视频进度,音量,亮度
     */

    private RelativeLayout root_layout;// 根布局
    private RecyclerView mostPeople_rv;
    private GridView artistOther_girdView,relatedPlayList_gidView,related_girdView;
    private RelativeLayout gesture_volume_layout, gesture_bright_layout,video_rl,video_lr;// 音量控制布局,亮度控制布局
    private LinearLayout linearLayout,inc_ll;
    private TextView geture_tv_volume_percentage, geture_tv_bright_percentage;// 音量百分比,亮度百分比
    private ImageView gesture_iv_player_volume, gesture_iv_player_bright;// 音量图标,亮度图标
    private RelativeLayout gesture_progress_layout;// 进度图标
    private TextView geture_tv_progress_time,tv_video_name,tv_second_like,tv_second_count,tv_second_author,tv_second_time;// 播放时间进度
    private ImageView gesture_iv_progress,iv_video_head;// 快进或快退标志
    private GestureDetector gestureDetector;
    private AudioManager audiomanager;
    private int maxVolume, currentVolume;
    private float mBrightness = -1f; // 亮度
    private static final float STEP_PROGRESS = 2f;// 设定进度滑动时的步长，避免每次滑动都改变，导致改变过快
    private static final float STEP_VOLUME = 2f;// 协调音量滑动时的步长，避免每次滑动都改变，导致改变过快
    private boolean firstScroll = false;// 每次触摸屏幕后，第一次scroll的标志
    private int GESTURE_FLAG = 0;// 1,调节进度，2，调节音量,3.调节亮度
    private static final int GESTURE_MODIFY_PROGRESS = 1;
    private static final int GESTURE_MODIFY_VOLUME = 2;
    private static final int GESTURE_MODIFY_BRIGHT = 3;
    /**
     * Called when the activity_test is first created.
     */
    private Retrofit retrofit;
    final String key1 = "App-Id";
    final String value1 = "10101045";

    final String key2 = "Device-Id";
    final String value2 = "f1ef0a488a13203667bed4e6565403e1";

    final String key3 = "Device-V";
    final String value3 = "aU9TXzEwLjEuMV8xMjQyKjIyMDhfMTAwMDAxMDAwX2lQaG9uZTcsMQ==";
    private String mvUlr;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);

        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        Window window = this.getWindow();
        window.setFlags(flag, flag);
        btn_resylt = (ImageView) findViewById(R.id.btn_result);
        related_girdView = (GridView) findViewById(R.id.related_girdView);
        relatedPlayList_gidView = (GridView) findViewById(R.id.relatedPlayList_girdView);
        artistOther_girdView = (GridView) findViewById(R.id.artistOther_girdView);
        mostPeople_rv = (RecyclerView) findViewById(R.id.rv_second);
        tv_second_time = (TextView) findViewById(R.id.tv_second_time);
        tv_second_author = (TextView) findViewById(R.id.tv_second_author);
        tv_second_count = (TextView) findViewById(R.id.tv_second_count);
        tv_second_like = (TextView) findViewById(R.id.tv_second_like);
        tv_video_name = (TextView) findViewById(R.id.tv_video_second_name);
        iv_video_head = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.iv_video_second_head);
        video_rl = (RelativeLayout) findViewById(R.id.video_relative);
        linearLayout = (LinearLayout) findViewById(R.id.video_ll);
        time_tv_video = (TextView) findViewById(R.id.video_tv_time);
        time_tv = (TextView) findViewById(R.id.time_tv);
        title_textView = (TextView) findViewById(R.id.video_title);
        surfaceView = (SurfaceView) this.findViewById(R.id.surfaceView1);
        btnPlayUrl = (ImageView) this.findViewById(R.id.btnPlayUrl);
        btnPlayUrl.setOnClickListener(new ClickEvent());
        btnPause = (ImageView) this.findViewById(R.id.btn_puse);
        btnPause.setOnClickListener(new ClickEvent());
        btn_back = (ImageView) findViewById(R.id.video_back);
        btn_back.setOnClickListener(this);
        btn = (ImageView) findViewById(R.id.btn);
        btn.setOnClickListener(this);
        btn_resylt.setOnClickListener(this);
        skbProgress = (SeekBar) this.findViewById(R.id.skbProgress);
        skbProgress.setOnSeekBarChangeListener(new SeekBarChangeEvent());
        player = new Player(surfaceView, skbProgress);
        btnPlayUrl.setVisibility(View.INVISIBLE);
        btn_resylt.setVisibility(View.INVISIBLE);
        initView();
        Intent intent = getIntent();
        String videoId = intent.getStringExtra("我的天");
        retrofit = new Retrofit.Builder().baseUrl("http://mapiv2.yinyuetai.com/").addConverterFactory(GsonConverterFactory.create()).build();
        MvService serviceMv = retrofit.create(MvService.class);
        Map<String, String> mapMv = new HashMap<>();
        mapMv.put(key1, value1);
        mapMv.put(key2, value2);
        mapMv.put(key3, value3);
        Call<MVBean> callMv = serviceMv.startRequest("video/detail.json?videoId=" + videoId, mapMv);
        callMv.enqueue(new Callback<MVBean>() {
            @Override
            public void onResponse(Call<MVBean> call, Response<MVBean> response) {
                MVBean m = response.body();
                mvUlr = m.getData().getHdUrl();
                title_textView.setText(m.getData().getTitle());
                tv_video_name.setText(m.getData().getArtists().get(0).getArtistName());
                tv_second_like.setText(m.getData().getTotalView()+"");
                tv_second_count.setText(m.getData().getDesc());
                tv_second_time.setText(m.getData().getRegdate());
                tv_second_author.setText(m.getData().getCreator().getNickName());
                Picasso.with(MvVideoActivity.this).load(m.getData().getArtists().get(0).getArtistAvatar()).into(iv_video_head);
                Log.d("我的天", mvUlr);
                MvMostAdapter adapter = new MvMostAdapter(MvVideoActivity.this);
                adapter.setData(m.getData().getMostPeopleVideos());
                mostPeople_rv.setAdapter(adapter);
                GridLayoutManager layoutManager = new GridLayoutManager(MvVideoActivity.this,1, LinearLayoutManager.HORIZONTAL,false);
                mostPeople_rv.setLayoutManager(layoutManager);
                MvOtherAdapter artistOtherAdapter = new MvOtherAdapter(MvVideoActivity.this);
                artistOtherAdapter.setData(m.getData().getArtistOtherVideos());
                artistOther_girdView.setAdapter(artistOtherAdapter);
                MvPlayListAdapter adapterList = new MvPlayListAdapter(MvVideoActivity.this);
                adapterList.setData(m.getData().getRelatedPlayList());
                relatedPlayList_gidView.setAdapter(adapterList);
                MvRelatedAdapter relatedAdapter = new MvRelatedAdapter(MvVideoActivity.this);
                relatedAdapter.setData(m.getData().getRelatedVideos());
                related_girdView.setAdapter(relatedAdapter);
                initDates();


            }


            @Override
            public void onFailure(Call<MVBean> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn:
                this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                btn_resylt.setVisibility(View.VISIBLE);
                btn.setVisibility(View.INVISIBLE);
                break;
            case R.id.video_back:
                onBackPressed();
                player.stop();
                break;
            case R.id.btn_result:
                this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                btn_resylt.setVisibility(View.INVISIBLE);
                btn.setVisibility(View.VISIBLE);
                break;
        }
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {//横屏
            setVideoParams(player.mediaPlayer,true);
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {//竖屏
            setVideoParams(player.mediaPlayer,false);
        }
    }
    public void setVideoParams(MediaPlayer mediaPlayer, boolean isLand) {
        //获取surfaceView父布局的参数
        ViewGroup.LayoutParams rl_paramters = root_layout.getLayoutParams();
        //获取SurfaceView的参数
        ViewGroup.LayoutParams sv_paramters = surfaceView.getLayoutParams();
        //设置宽高比为16/9
        float screen_widthPixels = getResources().getDisplayMetrics().widthPixels;
        float screen_heightPixels = getResources().getDisplayMetrics().widthPixels * 9f / 16f;
        //取消全屏
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (isLand) {
            screen_heightPixels = getResources().getDisplayMetrics().heightPixels;
            //设置全屏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        rl_paramters.width = (int) screen_widthPixels;
        rl_paramters.height = (int) screen_heightPixels;

        //获取MediaPlayer的宽高
        int videoWidth = mediaPlayer.getVideoWidth();
        int videoHeight = mediaPlayer.getVideoHeight();

        float video_por = videoWidth / videoHeight;
        float screen_por = screen_widthPixels / screen_heightPixels;
        //16:9    16:12
        if (screen_por > video_por) {
            sv_paramters.height = (int) screen_heightPixels;
            sv_paramters.width = (int) (screen_heightPixels * screen_por);
        } else {
            //16:9  19:9
            sv_paramters.width = (int) screen_widthPixels;
            sv_paramters.height = (int) (screen_widthPixels / screen_por);
        }
        root_layout.setLayoutParams(rl_paramters);
        surfaceView.setLayoutParams(sv_paramters);
    }
    private void initView() {
        root_layout = (RelativeLayout) findViewById(R.id.root_layout);
        gesture_volume_layout = (RelativeLayout) findViewById(R.id.gesture_volume_layout);
        gesture_bright_layout = (RelativeLayout) findViewById(R.id.gesture_bright_layout);
        gesture_progress_layout = (RelativeLayout) findViewById(R.id.gesture_progress_layout);
        geture_tv_progress_time = (TextView) findViewById(R.id.geture_tv_progress_time);
        geture_tv_volume_percentage = (TextView) findViewById(R.id.geture_tv_volume_percentage);
        geture_tv_bright_percentage = (TextView) findViewById(R.id.geture_tv_bright_percentage);
        gesture_iv_progress = (ImageView) findViewById(R.id.gesture_iv_progress);
        gesture_iv_player_volume = (ImageView) findViewById(R.id.gesture_iv_player_volume);
        gesture_iv_player_bright = (ImageView) findViewById(R.id.gesture_iv_player_bright);
        gestureDetector = new GestureDetector(this, this);
        gestureDetector.setIsLongpressEnabled(true);
        root_layout.setLongClickable(true);
        gestureDetector.setIsLongpressEnabled(true);
        root_layout.setOnTouchListener(this);
        audiomanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        maxVolume = audiomanager.getStreamMaxVolume(AudioManager.STREAM_MUSIC); // 获取系统最大音量
        currentVolume = audiomanager.getStreamVolume(AudioManager.STREAM_MUSIC); // 获取当前值
        ViewTreeObserver viewObserver = root_layout.getViewTreeObserver();
        viewObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                root_layout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                playerWidth = root_layout.getWidth();
                playerHeight = root_layout.getHeight();
            }
        });
    }

    private void initDates() {
        player.playUrl(mvUlr);
        Log.d("zzzd", "getDuration():" + player.mediaPlayer.getDuration());
        int secondTime = player.mediaPlayer.getDuration() / 1000;
        skbProgress.setMax(secondTime);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            GESTURE_FLAG = 0;// 手指离开屏幕后，重置调节音量或进度的标志
            gesture_volume_layout.setVisibility(View.GONE);
            gesture_bright_layout.setVisibility(View.GONE);
            gesture_progress_layout.setVisibility(View.GONE);
        }
        return gestureDetector.onTouchEvent(motionEvent);
    }
    class ClickEvent implements View.OnClickListener {

        @Override
        public void onClick(View arg0) {
            if (arg0 == btnPlayUrl) {
                player.play();
                btnPlayUrl.setVisibility(View.INVISIBLE);
                btnPause.setVisibility(View.VISIBLE);

            } else if (arg0 == btnPause) {
                player.pause();
                btnPlayUrl.setVisibility(View.VISIBLE);
                btnPause.setVisibility(View.INVISIBLE);
            }
        }
    }
    class SeekBarChangeEvent implements SeekBar.OnSeekBarChangeListener {
        int progress;
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            // 原本是(progress/seekBar.getMax())*player.mediaPlayer.getDuration()
            this.progress = progress * player.mediaPlayer.getDuration() / seekBar.getMax();
            initTime();
            initTimes();


        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // seekTo()的参数是相对与影片时间的数字，而不是与seekBar.getMax()相对的数字
            player.mediaPlayer.seekTo(progress);

        }
    }
    private void initTime() {
        String time = player.mediaPlayer.getDuration() + "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        Date date = new Date(Long.valueOf(time));
        String newTime = simpleDateFormat.format(date);
        time_tv.setText(newTime);
    }
    private void initTimes() {
        String time = player.mediaPlayer.getCurrentPosition() + "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        Date date = new Date(Long.valueOf(time));
        String newTime = simpleDateFormat.format(date);
        time_tv_video.setText(newTime);
    }
    @Override
    public boolean onDown(MotionEvent e) {
        firstScroll = true;// 设定是触摸屏幕后第一次scroll的标志
        return false;
    }
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        float mOldX = e1.getX(), mOldY = e1.getY();
        videoTotalTime = player.mediaPlayer.getDuration()/1000;
        playingTime = player.mediaPlayer.getCurrentPosition()/1000;
        int y = (int) e2.getRawY();
        if (firstScroll) {// 以触摸屏幕后第一次滑动为标准，避免在屏幕上操作切换混乱
            // 横向的距离变化大则调整进度，纵向的变化大则调整音量
            if (Math.abs(distanceX) >= Math.abs(distanceY)) {
                gesture_progress_layout.setVisibility(View.VISIBLE);
                gesture_volume_layout.setVisibility(View.GONE);
                gesture_bright_layout.setVisibility(View.GONE);
                GESTURE_FLAG = GESTURE_MODIFY_PROGRESS;
            } else {
                if (mOldX > playerWidth * 3.0 / 5) {// 音量
                    gesture_volume_layout.setVisibility(View.VISIBLE);
                    gesture_bright_layout.setVisibility(View.GONE);
                    gesture_progress_layout.setVisibility(View.GONE);
                    GESTURE_FLAG = GESTURE_MODIFY_VOLUME;
                } else if (mOldX < playerWidth * 2.0 / 5) {// 亮度
                    gesture_bright_layout.setVisibility(View.VISIBLE);
                    gesture_volume_layout.setVisibility(View.GONE);
                    gesture_progress_layout.setVisibility(View.GONE);
                    GESTURE_FLAG = GESTURE_MODIFY_BRIGHT;
                }
            }
        }
        // 如果每次触摸屏幕后第一次scroll是调节进度，那之后的scroll事件都处理音量进度，直到离开屏幕执行下一次操作
        if (GESTURE_FLAG == GESTURE_MODIFY_PROGRESS) {
            playingTime = player.mediaPlayer.getCurrentPosition();
            // distanceX=lastScrollPositionX-currentScrollPositionX，因此为正时是快进
            if (Math.abs(distanceX) > Math.abs(distanceY)) {// 横向移动大于纵向移动
                if (distanceX >= DensityUtil.dip2px(this, STEP_PROGRESS)) {// 快退，用步长控制改变速度，可微调
                    gesture_iv_progress.setImageResource(R.mipmap.souhu_player_backward);
                    if (playingTime > 3) {// 避免为负
                        playingTime -= 3;// scroll方法执行一次快退3秒
                    } else {
                        playingTime = 0;
                    }
                } else if (distanceX <= -DensityUtil.dip2px(this, STEP_PROGRESS)) {// 快进
                    gesture_iv_progress.setImageResource(R.mipmap.souhu_player_forward);
                    if (playingTime < videoTotalTime - 16) {// 避免超过总时长
                        playingTime += 3;// scroll执行一次快进3秒
                    } else {
                        playingTime = videoTotalTime - 10;
                    }
                }
                if (playingTime < 0) {
                    playingTime = 0;
                }
                player.mediaPlayer.seekTo(playingTime);
                geture_tv_progress_time.setText(DateTools.getTimeStr(playingTime) + "/" + DateTools.getTimeStr(videoTotalTime));
            }
        }
        // 如果每次触摸屏幕后第一次scroll是调节音量，那之后的scroll事件都处理音量调节，直到离开屏幕执行下一次操作
        else if (GESTURE_FLAG == GESTURE_MODIFY_VOLUME) {
            currentVolume = audiomanager.getStreamVolume(AudioManager.STREAM_MUSIC); // 获取当前值
            if (Math.abs(distanceY) > Math.abs(distanceX)) {// 纵向移动大于横向移动
                if (distanceY >= DensityUtil.dip2px(this, STEP_VOLUME)) {// 音量调大,注意横屏时的坐标体系,尽管左上角是原点，但横向向上滑动时distanceY为正
                    if (currentVolume < maxVolume) {// 为避免调节过快，distanceY应大于一个设定值
                        currentVolume++;
                    }
                    gesture_iv_player_volume.setImageResource(R.mipmap.souhu_player_volume);
                } else if (distanceY <= -DensityUtil.dip2px(this, STEP_VOLUME)) {// 音量调小
                    if (currentVolume > 0) {
                        currentVolume--;
                        if (currentVolume == 0) {// 静音，设定静音独有的图片
                            gesture_iv_player_volume.setImageResource(R.mipmap.souhu_player_silence);
                        }
                    }
                }
                int percentage = (currentVolume * 100) / maxVolume;
                geture_tv_volume_percentage.setText(percentage + "%");
                audiomanager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume, 0);
            }
        }
        // 如果每次触摸屏幕后第一次scroll是调节亮度，那之后的scroll事件都处理亮度调节，直到离开屏幕执行下一次操作
        else if (GESTURE_FLAG == GESTURE_MODIFY_BRIGHT) {
            gesture_iv_player_bright.setImageResource(R.mipmap.souhu_player_bright);
            if (mBrightness < 0) {
                mBrightness = getWindow().getAttributes().screenBrightness;
                if (mBrightness <= 0.00f)
                    mBrightness = 0.50f;
                if (mBrightness < 0.01f)
                    mBrightness = 0.01f;
            }
            WindowManager.LayoutParams lpa = getWindow().getAttributes();
            lpa.screenBrightness = mBrightness + (mOldY - y) / playerHeight;
            if (lpa.screenBrightness > 1.0f)
                lpa.screenBrightness = 1.0f;
            else if (lpa.screenBrightness < 0.01f)
                lpa.screenBrightness = 0.01f;
            getWindow().setAttributes(lpa);
            geture_tv_bright_percentage.setText((int) (lpa.screenBrightness * 100) + "%");
        }

        firstScroll = false;// 第一次scroll执行完成，修改标志
        return false;
    }
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
    @Override
    public void onLongPress(MotionEvent e) {
    }
    @Override
    public void onShowPress(MotionEvent e) {
    }


}
