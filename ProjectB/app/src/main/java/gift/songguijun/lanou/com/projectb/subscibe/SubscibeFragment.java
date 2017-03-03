package gift.songguijun.lanou.com.projectb.subscibe;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import gift.songguijun.lanou.com.projectb.R;
import gift.songguijun.lanou.com.projectb.base.BaseFragment;

/**
 * Created by dllo on 17/2/20.
 */

public class SubscibeFragment extends Fragment implements View.OnClickListener {
    private TabLayout tabSubscibe ;
    private ViewPager vpSubscibe ;
    private int[] res = {R.id.imageview1 , R.id.imageview2 , R.id.imageview3 , R.id.imageview4 ,
            R.id.imageview5 , R.id.imageview6 , R.id.imageview7};
    private List<ImageView> imgs = new ArrayList<ImageView>();
    private boolean flag = true ;
    private ImageView ivMain ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subscibe , container , false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        for (int i = 0; i < res.length; i++) {
            ImageView iv = (ImageView) getActivity().findViewById(res[i]);
            iv.setOnClickListener(this);
            imgs.add(iv);
        }
        ivMain = (ImageView) getActivity().findViewById(R.id.main);
        ivMain.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main :
                if (flag){
                    flag = false ;
                    startAnim();
                }else {
                    flag = true ;
                    closeAnim();
                }
                break;
            case R.id.imageview4 :
                Intent intent = new Intent(getActivity() , VoiceActivity.class);
                startActivity(intent);
                break;
            case R.id.imageview5 :
                Intent intent1 = new Intent(getActivity() , MapActivity.class);
                startActivity(intent1);
            default:
                Toast.makeText(getContext(), "view.getId():" + view.getId(), Toast.LENGTH_SHORT).show();
                break;
        }
    }
    private void closeAnim() {
        float myroate = (float) (Math.PI / 2 / 6);
        for (int i = 0; i < res.length; i++) {
            float x = (float) (-500 * Math.cos(myroate * i));
            float y = (float) (-500 * Math.sin(myroate * i));
            //使用属性动画的平移动画，将坐标从现在的位置移回到原点
            ObjectAnimator animator = ObjectAnimator.ofFloat(imgs.get(i),
                    "translationY", y, 0);
            ObjectAnimator animator2 = ObjectAnimator.ofFloat(imgs.get(i),
                    "translationX", x, 0);
//使用AnimatorSet可以同时播放多个属性动画
            AnimatorSet set = new AnimatorSet();
//使用自由落体的差值器
            animator.setInterpolator(new BounceInterpolator());
            animator2.setInterpolator(new BounceInterpolator());
            //设定同时播放
            set.playTogether(animator, animator2);
            //设定播放时间
            set.setDuration(1000);
            //开始播放动画（千万不要忘记这一行）
            set.start();
        }



    }

    private void startAnim() {
        float myroate = (float) (Math.PI / 2 / 6);
        for (int i = 0; i < res.length; i++) {
            float x = (float) (-500 * Math.cos(myroate * i));
            float y = (float) (-500 * Math.sin(myroate * i));

//使用属性动画的平移动画，将坐标从原点移动到每个子菜单对应的位置
            ObjectAnimator animator = ObjectAnimator.ofFloat(imgs.get(i),
                    "translationY", 0, y);
            ObjectAnimator animator2 = ObjectAnimator.ofFloat(imgs.get(i),
                    "translationX", 0, x);
            AnimatorSet set = new AnimatorSet();
            animator.setInterpolator(new BounceInterpolator());
            animator2.setInterpolator(new BounceInterpolator());
            set.playTogether(animator, animator2);
            set.setDuration(1000);
            set.start();
        }
    }
}
