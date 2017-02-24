package gift.songguijun.lanou.com.projectb.v.special;

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
import gift.songguijun.lanou.com.projectb.bean.ImportBean;

/**
 * Created by dllo on 17/2/24.
 */

public class ImportListViewAdapter extends BaseAdapter {
    private List<ImportBean.DataBean.VideosBean>data;
    private Context context;

    public void setData(List<ImportBean.DataBean.VideosBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public ImportListViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return data != null ?data.size():0;
    }

    @Override
    public Object getItem(int i) {
        return data != null  ? data.get(i):0;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder holder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_special_hinterland,viewGroup,false);
            holder = new  MyViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (MyViewHolder) view.getTag();
        }
        holder.tv_title.setText(data.get(i).getTitle());
        holder.tv_name.setText(data.get(i).getArtistName());
        holder.tv_num.setText(data.get(i).getRank()+"");
        Picasso.with(context).load(data.get(i).getPosterPic()).into(holder.imageView);
        return view;
    }
    class MyViewHolder{

        private ImageView imageView;
        private TextView tv_title,tv_name,tv_num;
        public MyViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.speical_iv);
            tv_name = (TextView) view.findViewById(R.id.special_name);
            tv_title = (TextView) view.findViewById(R.id.special_title);
            tv_num = (TextView) view.findViewById(R.id.special_num);
        }
    }

}
