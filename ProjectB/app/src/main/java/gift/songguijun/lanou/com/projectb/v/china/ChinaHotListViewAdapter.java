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
import gift.songguijun.lanou.com.projectb.bean.ChinaHotBean;

/**
 * Created by dllo on 17/2/23.
 */

public class ChinaHotListViewAdapter extends BaseAdapter{
    private List<ChinaHotBean.DataBean.VideosBean>data;
    private Context context;

    public ChinaHotListViewAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<ChinaHotBean.DataBean.VideosBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data != null ?data.size():0;
    }

    @Override
    public Object getItem(int i) {
        return data != null ? data.get(i):0;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder holder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_china_hot,viewGroup,false);
            holder = new MyViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (MyViewHolder) view.getTag();
        }
        holder.tv_title.setText(data.get(i).getTitle());
        holder.tv_name.setText(data.get(i).getArtists().get(0).getArtistName());
        holder.tv_week.setText(data.get(i).getExtend().getPrePosition()+"");
        holder.tv_peak.setText(data.get(i).getExtend().getBestPosition()+"");
        holder.tv_wks.setText(data.get(i).getExtend().getHistoryCount()+"");
        holder.tv_num.setText(data.get(i).getExtend().getNumber()+"");
        Picasso.with(context).load(data.get(i).getAlbumImg()).into(holder.imageView);
        return view;
    }
    class MyViewHolder {
        private ImageView imageView;
        private TextView tv_name,tv_title,tv_week,tv_peak,tv_wks,tv_num;
        public MyViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.china_hot_iv);
            tv_name = (TextView) view.findViewById(R.id.china_hot_name);
            tv_title = (TextView) view.findViewById(R.id.china_hot_title);
            tv_week = (TextView) view.findViewById(R.id.china_hot_week);
            tv_peak = (TextView) view.findViewById(R.id.china_hot_peak);
            tv_wks = (TextView) view.findViewById(R.id.china_hot_wks);
            tv_num = (TextView) view.findViewById(R.id.china_hot_num);
        }
    }
}
