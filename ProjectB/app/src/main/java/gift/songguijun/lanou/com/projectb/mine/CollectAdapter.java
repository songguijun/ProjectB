package gift.songguijun.lanou.com.projectb.mine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import gift.songguijun.lanou.com.projectb.DBHelper.PersonEntity;
import gift.songguijun.lanou.com.projectb.R;

/**
 * Created by dllo on 17/3/11.
 */

public class CollectAdapter extends BaseAdapter{
    private List<PersonEntity>data ;
    private Context context ;

    public CollectAdapter setData(List<PersonEntity> data) {
        this.data = data;
        notifyDataSetChanged();
        return this;
    }

    public CollectAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return data != null && data.size() > 0 ? data.size() : 0;
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
        CollectViewHolder collectViewHolder = null ;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_collect , viewGroup , false);
            collectViewHolder = new CollectViewHolder(view);
            view.setTag(collectViewHolder);
        }else {
            collectViewHolder = (CollectViewHolder) view.getTag();
        }
        collectViewHolder.textTitle.setText(data.get(i).getName());
        collectViewHolder.textSinger.setText(data.get(i).getSinger());
        Glide.with(context).load(data.get(i).getPic()).into(collectViewHolder.imageView);

        return view;
    }
    class CollectViewHolder{
        private TextView textTitle , textSinger;
        private ImageView imageView ;
        public CollectViewHolder(View view) {
            textTitle = (TextView) view.findViewById(R.id.tv_title_collect);
            textSinger = (TextView) view.findViewById(R.id.tv_singer_collect);
            imageView = (ImageView) view.findViewById(R.id.iv_collect);
        }
    }
}
