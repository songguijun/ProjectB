package gift.songguijun.lanou.com.projectb.navigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import gift.songguijun.lanou.com.projectb.volley.NavigationBean;
import gift.songguijun.lanou.com.projectb.R;

/**
 * Created by dllo on 17/2/21.
 */

/**
 * 导航页GiedView适配器
 */
public class NavigationAdapter extends BaseAdapter {
    private List<NavigationBean.DataBean.ContentsBean> data;
    private Context context;

    public NavigationAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<NavigationBean.DataBean.ContentsBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data != null ? data.size():0 ;
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
            view = LayoutInflater.from(context).inflate(R.layout.item_navigation,viewGroup,false);
            holder = new MyViewHolder(view);
            view.setTag(holder);
        }
        else {
            holder = (MyViewHolder) view.getTag();
        }
        holder.tv.setText(data.get(i).getTitle());
        Picasso.with(context).load(data.get(i).getIcon()).into(holder.iv);
        return view;
    }



    class MyViewHolder {
        private TextView tv ;
        private ImageView iv;
        public MyViewHolder(View view) {
            tv = (TextView) view.findViewById(R.id.gird_tv_novigation);
            iv = (ImageView) view.findViewById(R.id.gird_iv_novigation);
        }
    }
}
