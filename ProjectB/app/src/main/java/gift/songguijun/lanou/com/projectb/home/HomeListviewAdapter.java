package gift.songguijun.lanou.com.projectb.home;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.List;

import gift.songguijun.lanou.com.projectb.R;
import gift.songguijun.lanou.com.projectb.video.VideoPlayerActivity;

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

    public void setData(List<HomeBean.DataBeanX> data) {
        this.data = data;
        notifyDataSetChanged();
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
    public View getView(final int position, View view, ViewGroup viewGroup) {

        HomeListViewHolder listViewHolder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_fragment_home_listview , viewGroup , false);
            listViewHolder = new HomeListViewHolder(view);
            view.setTag(listViewHolder);
        }else {
            listViewHolder = (HomeListViewHolder) view.getTag();
        }

        listViewHolder.tvTitle.setText(data.get(position+2).getTitle());
        gridviewAdapter = new HomeListviewGridviewAdapter(context);
        gridviewAdapter.setData(data.get(position+2).getData());
        listViewHolder.gridViewHome.setAdapter(gridviewAdapter);
        listViewHolder.gridViewHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(context, VideoPlayerActivity.class);
                String id = data.get(position+2).getData().get(i).getVideoId()+"";
            intent.putExtra("name",id);
            Log.d("傻逼", id);
            context.startActivity(intent);

        }
        });


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
