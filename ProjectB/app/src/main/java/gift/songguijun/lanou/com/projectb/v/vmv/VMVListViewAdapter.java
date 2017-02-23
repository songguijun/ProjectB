package gift.songguijun.lanou.com.projectb.v.vmv;

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
import gift.songguijun.lanou.com.projectb.bean.VMVBean;

/**
 * Created by dllo on 17/2/22.
 */

public class VMVListViewAdapter extends BaseAdapter {
    private List<VMVBean.DataBean.VideosBean>data;
    private Context context;

    public VMVListViewAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<VMVBean.DataBean.VideosBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data != null ?data.size():null ;
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
            view = LayoutInflater.from(context).inflate(R.layout.item_v_mv_selected,viewGroup,false);
            holder = new MyViewHolder(view);
            view.setTag(holder);
        }
        else {
            holder = (MyViewHolder) view.getTag();
        }
        Picasso.with(context).load(data.get(i).getAlbumImg()).into(holder.iv);
        holder.tv_num.setText(data.get(i).getExtend().getNumber()+"");
        holder.tv_blackNum.setText(data.get(i).getExtend().getScore()+"");
        holder.tv_redNum.setText(data.get(i).getExtend().getTrendScore()+"");
        holder.tv_sink.setText(data.get(i).getTitle()+"");
        holder.tv_name.setText(data.get(i).getArtists().get(0).getArtistName()+"");
        return view;
    }
    class MyViewHolder{
        private ImageView iv;
        private TextView tv_num,tv_blackNum,tv_redNum,tv_sink,tv_name;
        public MyViewHolder(View view) {
            iv = (ImageView) view.findViewById(R.id.v_mv_iv);
            tv_num = (TextView) view.findViewById(R.id.v_mv_num);
            tv_blackNum = (TextView) view.findViewById(R.id.v_mv_blacknum);
            tv_redNum = (TextView) view.findViewById(R.id.v_mv_radnum);
            tv_sink = (TextView) view.findViewById(R.id.v_mv_skin);
            tv_name = (TextView) view.findViewById(R.id.v_mv_name);
        }
    }
}
