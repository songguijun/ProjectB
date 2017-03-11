package gift.songguijun.lanou.com.projectb.v.special;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.litesuits.orm.LiteOrm;
import com.squareup.picasso.Picasso;

import java.util.List;

import gift.songguijun.lanou.com.projectb.DBHelper.PersonEntity;
import gift.songguijun.lanou.com.projectb.R;
import gift.songguijun.lanou.com.projectb.bean.SpecialBean;

/**
 * Created by dllo on 17/2/24.
 */

public class HinterlandListViewAdapter extends BaseAdapter {
    private List<SpecialBean.DataBean.VideosBean>data;
    private Context context;
    private LiteOrm liteOrm ;

    public HinterlandListViewAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<SpecialBean.DataBean.VideosBean> data) {
        this.data = data;
        notifyDataSetChanged();
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        MyViewHolder holder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_special_hinterland,viewGroup,false);
            holder = new MyViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (MyViewHolder) view.getTag();
        }
        holder.tv_title.setText(data.get(i).getTitle());
        holder.tv_name.setText(data.get(i).getArtistName());
        holder.tv_num.setText(data.get(i).getRank()+"");
        Glide.with(context).load(data.get(i).getPosterPic()).into(holder.imageView);
        holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                liteOrm = LiteOrm.newSingleInstance(context , "yinyuetai.db");
                PersonEntity personEntity = new PersonEntity();
                personEntity.setName(data.get(i).getTitle());
                personEntity.setSinger(data.get(i).getArtistName());
                personEntity.setPic(data.get(i).getPosterPic());
                liteOrm.insert(personEntity);
                Toast.makeText(context, "已收藏", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return view;
    }
    class MyViewHolder{

        private ImageView imageView;
        private LinearLayout linearLayout ;
        private TextView tv_title,tv_name,tv_num;
        public MyViewHolder(View view) {
            linearLayout = (LinearLayout) view.findViewById(R.id.speical_ll);
            imageView = (ImageView) view.findViewById(R.id.speical_iv);
            tv_name = (TextView) view.findViewById(R.id.special_name);
            tv_title = (TextView) view.findViewById(R.id.special_title);
            tv_num = (TextView) view.findViewById(R.id.special_num);
        }
    }
}
