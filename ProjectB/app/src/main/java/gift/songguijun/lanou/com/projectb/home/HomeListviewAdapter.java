package gift.songguijun.lanou.com.projectb.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.List;

import gift.songguijun.lanou.com.projectb.R;

/**
 * Created by dllo on 17/2/21.
 */

public class HomeListviewAdapter extends BaseAdapter{
    private List<HomeBean.DataBeanX> data ;
    private Context context ;
    private HomeListviewGridviewAdapter gridviewAdapter ;

    public HomeListviewAdapter(Context context) {
        this.context = context;
    }

    public HomeListviewAdapter setData(List<HomeBean.DataBeanX> data) {
        this.data = data;
        notifyDataSetChanged();
        return this;
    }

    @Override
    public int getCount() {
        if (data.size()>0){
            return data.size()-2 ;
        }else {
            return 0 ;
        }
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

        HomeListViewHolder listViewHolder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_fragment_home_listview , viewGroup , false);
            listViewHolder = new HomeListViewHolder(view);
            view.setTag(listViewHolder);
        }else {
            listViewHolder = (HomeListViewHolder) view.getTag();
        }

        listViewHolder.tvTitle.setText(data.get(i+2).getTitle());
        gridviewAdapter = new HomeListviewGridviewAdapter(context);
        gridviewAdapter.setData(data.get(i+2).getData());
        listViewHolder.gridViewHome.setAdapter(gridviewAdapter);



        return view;
    }
    class HomeListViewHolder{
        private TextView tvTitle ;
        private GridView gridViewHome ;
        public HomeListViewHolder(View view) {
            tvTitle = (TextView) view.findViewById(R.id.tv_item_home_title);
            gridViewHome = (GridView) view.findViewById(R.id.gridview_item_home);
        }
    }

}
