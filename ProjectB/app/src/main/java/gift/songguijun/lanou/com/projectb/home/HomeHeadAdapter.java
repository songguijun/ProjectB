package gift.songguijun.lanou.com.projectb.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import gift.songguijun.lanou.com.projectb.R;
import gift.songguijun.lanou.com.projectb.bean.HomeBean;

/**
 * Created by dllo on 17/3/7.
 */

public class HomeHeadAdapter extends BaseAdapter{
    private List<HomeBean.DataBeanX> data ;
    private Context context ;




    public HomeHeadAdapter(Context context) {
        this.context = context;
    }

    public HomeHeadAdapter setData(List<HomeBean.DataBeanX> data) {
        this.data = data;
        notifyDataSetChanged();
        return this;
    }

    @Override
    public int getCount() {
        return data != null && data.size() > 0 ? 3 : 0;
    }

    @Override
    public Object getItem(int i) {
        return data != null ? data.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        HomeHeadHolder headHolder = null ;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_home_head , viewGroup , false);
            headHolder = new HomeHeadHolder(view);
            view.setTag(headHolder);
        }else {
            headHolder = (HomeHeadHolder) view.getTag();
        }
       headHolder.tvHomeHead.setText(data.get(1).getData().get(i).getTitle());


       Glide.with(context).load(data.get(1).getData().get(i).getIcon()).into(headHolder.ivHomeHead);

        return view;
    }



    class HomeHeadHolder{
        private ImageView ivHomeHead ;
        private TextView tvHomeHead ;
        public HomeHeadHolder(View view) {
            ivHomeHead = (ImageView) view.findViewById(R.id.iv_item_home_head);
            tvHomeHead = (TextView) view.findViewById(R.id.tv_home_head);

        }
    }

}
