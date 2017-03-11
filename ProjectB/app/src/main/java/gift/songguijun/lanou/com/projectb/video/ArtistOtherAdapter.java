package gift.songguijun.lanou.com.projectb.video;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import gift.songguijun.lanou.com.projectb.R;
import gift.songguijun.lanou.com.projectb.bean.VideoBean;

/**
 * Created by dllo on 17/3/2.
 */

public class ArtistOtherAdapter extends BaseAdapter {
    private List<VideoBean.DataBean.ArtistOtherVideosBean>data;
    private Context context;

    public ArtistOtherAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<VideoBean.DataBean.ArtistOtherVideosBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data != null && data.size()>0 ?data.size():0;
    }

    @Override
    public Object getItem(int i) {
        return data != null  ? data.size():0;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder holder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_mospeople,viewGroup,false);
            holder = new MyViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (MyViewHolder) view.getTag();
        }
        holder.tv_tiele.setText(data.get(i).getTitle());
        holder.tv_count.setText(data.get(i).getArtists().get(0).getArtistName());
        holder.tv_like.setText(data.get(i).getTotalView()+"");
        Picasso.with(context).load(data.get(i).getPosterPic()).into(holder.imageView);
        return view;
    }
    class MyViewHolder {
        private TextView tv_tiele,tv_count,tv_like;
        private ImageView imageView;
        public MyViewHolder(View view) {
            tv_tiele = (TextView) view.findViewById(R.id.most_people_tv_title);
            tv_count = (TextView) view.findViewById(R.id.most_people_tv_name);
            tv_like = (TextView) view.findViewById(R.id.most_people_tv_like);
            imageView = (ImageView) view.findViewById(R.id.most_people_iv);
        }
    }
}