package gift.songguijun.lanou.com.projectb.v.china;

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
import gift.songguijun.lanou.com.projectb.bean.ChinaTopBean;

/**
 * Created by dllo on 17/2/23.
 */

public class ChinaListViewTopAdapter extends BaseAdapter {
    private List<ChinaTopBean.DataBean.VideosBean>data;
    private Context context;

    public void setData(List<ChinaTopBean.DataBean.VideosBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public ChinaListViewTopAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return data != null ? data.size():0;
    }

    @Override
    public Object getItem(int i) {
        return data != null ?data.get(i) :0;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder holder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_china_top,viewGroup,false);
            holder = new MyViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (MyViewHolder) view.getTag();
        }
        holder.tv_title.setText(data.get(i).getTitle());
        holder.tv_name.setText(data.get(i).getArtists().get(0).getArtistName());
        holder.tv_num.setText(data.get(i).getExtend().getNumber()+"");
        holder.tv_listnum.setText(data.get(i).getExtend().getScore()+"");
        Picasso.with(context).load(data.get(i).getAlbumImg()).into(holder.imageView);
        return view;
    }
    class MyViewHolder {
        private ImageView imageView;
        private TextView tv_title,tv_name,tv_num,tv_listnum;
        public MyViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.china_top_iv);
            tv_title = (TextView) view.findViewById(R.id.china_top_title);
            tv_name = (TextView) view.findViewById(R.id.china_top_name);
            tv_num = (TextView) view.findViewById(R.id.china_top_num);
            tv_listnum = (TextView) view.findViewById(R.id.chian_top_listnum);
        }
    }
}
