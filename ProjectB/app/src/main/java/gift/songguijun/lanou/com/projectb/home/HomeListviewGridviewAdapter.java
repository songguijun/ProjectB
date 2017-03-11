package gift.songguijun.lanou.com.projectb.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import gift.songguijun.lanou.com.projectb.R;
import gift.songguijun.lanou.com.projectb.bean.HomeBean;

/**
 * Created by dllo on 17/2/21.
 */

/**
 * 主页ListView嵌套GirdView适配器
 */
public class HomeListviewGridviewAdapter extends BaseAdapter{
    private List<HomeBean.DataBeanX.DataBean> data ;
    private Context context ;

    public HomeListviewGridviewAdapter(Context context) {
        this.context = context;
    }

    public HomeListviewGridviewAdapter setData(List<HomeBean.DataBeanX.DataBean> data) {
        this.data = data;
        notifyDataSetChanged();
        return this;
    }

    @Override
    public int getCount() {
        return data != null && data.size() > 0  ?data.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return data!=null ? data.get(i): null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        HomeListViewGridViewHolder gridViewHolder = null ;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_fragment_home_listview_gridview , viewGroup ,false);
            gridViewHolder = new HomeListViewGridViewHolder(view);
            view.setTag(gridViewHolder);
        }else {
            gridViewHolder = (HomeListViewGridViewHolder) view.getTag();
        }
        gridViewHolder.tvName.setText(data.get(i).getTitle());
        gridViewHolder.tvSinger.setText(data.get(i).getArtists().get(0).getArtistName());
        gridViewHolder.tvNum.setText(data.get(i).getTotalView()+"");
        Glide.with(context).load(data.get(i).getPosterPic()).into(gridViewHolder.ivPic);

        return view;
    }
    class HomeListViewGridViewHolder{
        private ImageView ivPic ;
        private TextView tvName , tvSinger , tvNum ;

        public HomeListViewGridViewHolder(View view) {
            ivPic = (ImageView) view.findViewById(R.id.iv_item_home_listview_gridview);
            tvName = (TextView) view.findViewById(R.id.tv_item_home_name);
            tvSinger = (TextView) view.findViewById(R.id.tv_item_home_singer);
            tvNum = (TextView) view.findViewById(R.id.tv_item_home_num);
        }
    }
}
