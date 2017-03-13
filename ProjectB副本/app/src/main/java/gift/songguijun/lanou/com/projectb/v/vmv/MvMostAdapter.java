package gift.songguijun.lanou.com.projectb.v.vmv;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import gift.songguijun.lanou.com.projectb.R;
import gift.songguijun.lanou.com.projectb.home.MVBean;

/**
 * Created by dllo on 17/3/7.
 */

public class MvMostAdapter extends RecyclerView.Adapter<MvMostAdapter.MyViewHoder> {
    private List<MVBean.DataBean.MostPeopleVideosBean> data;
    private Context context;

    public MvMostAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<MVBean.DataBean.MostPeopleVideosBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mospeople,parent,false);
        MyViewHoder hoder = new MyViewHoder(view);
        return hoder;
    }

    @Override
    public void onBindViewHolder(MyViewHoder holder, int position) {
        holder.tv_name.setText(data.get(position).getArtists().get(0).getArtistName());
        holder.tv_titile.setText(data.get(position).getTitle());
        holder.tv_like.setText(data.get(position).getTotalView()+"");
        Picasso.with(context).load(data.get(position).getPosterPic()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data != null  && data.size()>0 ? data.size():0;
    }

    class MyViewHoder extends RecyclerView.ViewHolder{
        private TextView tv_titile,tv_name,tv_like;
        private ImageView imageView;
        public MyViewHoder(View itemView) {
            super(itemView);
            tv_titile = (TextView) itemView.findViewById(R.id.most_people_tv_title);
            tv_like = (TextView) itemView.findViewById(R.id.most_people_tv_like);
            tv_name = (TextView) itemView.findViewById(R.id.most_people_tv_name);
            imageView = (ImageView) itemView.findViewById(R.id.most_people_iv);
        }
    }
}
