package gift.songguijun.lanou.com.projectb.v.program;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import gift.songguijun.lanou.com.projectb.R;
import gift.songguijun.lanou.com.projectb.bean.ProgramBean;

/**
 * Created by dllo on 17/2/22.
 */

public class ProgramAdapter extends RecyclerView.Adapter {
    private List<ProgramBean.DataBeanX> data;
    private Context context;
    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private static final int THREAD = 2;
    private static final int FOUR = 3;
    private View first;
    private View second;
    private MyHolder myHolder;
    private MyViewHolder holdeFirst;

    public ProgramAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<ProgramBean.DataBeanX> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return FIRST;
        } else if (position == 1) {
            return SECOND;
        } else if (position == 2) {
            return THREAD;
        } else  {
            return FOUR;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType){
            case FIRST:
                first = LayoutInflater.from(context).inflate(R.layout.item_program_first,parent,false);
                holder = new MyViewHolder(first);
                break;
            case SECOND:
                second = LayoutInflater.from(context).inflate(R.layout.item_program,parent,false);
                holder = new MyHolder(second);
                break;
            case THREAD:
                first = LayoutInflater.from(context).inflate(R.layout.item_program_first,parent,false);
                holder = new MyViewHolder(first);
                break;
            case FOUR:
                second = LayoutInflater.from(context).inflate(R.layout.item_program,parent,false);
                holder = new MyHolder(second);
                break;

        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
               int TypeView = getItemViewType(position);
        switch (TypeView){
            case FIRST:
                holdeFirst = (MyViewHolder) holder;
                holdeFirst.tv_title.setText(data.get(0).getData().get(position).getTitle());
                holdeFirst.tv_count.setText(data.get(0).getData().get(0).getArtists().get(position).getArtistName());
                holdeFirst.tv_num.setText(data.get(0).getData().get(position).getTotalView()+"");
                holdeFirst.tv_left.setText(data.get(0).getTitle());
                holdeFirst.tv_right.setText(data.get(0).getMoreData().getTitle());
                Picasso.with(context).load(data.get(0).getData().get(position).getPosterPic()).into(holdeFirst.imageView);
                break;
            case SECOND:
                myHolder = (MyHolder) holder;
                ProgramFirstAdapter adapter = new ProgramFirstAdapter(context);
                adapter.setData(data.get(0).getData());
                myHolder.recyclerView.setAdapter(adapter);
                GridLayoutManager manager = new GridLayoutManager(context,2);
                myHolder.recyclerView.setLayoutManager(manager);
                break;
            case THREAD:
                holdeFirst = (MyViewHolder) holder;
                holdeFirst.tv_title.setText(data.get(1).getData().get(position).getTitle());
             //   holdeFirst.tv_count.setText(data.get(1).getData().get(1).getArtists().get(position).getArtistName());
                holdeFirst.tv_num.setText(data.get(1).getData().get(position).getTotalView()+"");
                holdeFirst.tv_left.setText(data.get(1).getTitle());
                holdeFirst.tv_right.setText(data.get(1).getMoreData().getTitle());
                Picasso.with(context).load(data.get(1).getData().get(position).getPosterPic()).into(holdeFirst.imageView);
                break;
            case FOUR:
                myHolder = (MyHolder) holder;
                ProgramFirstAdapter adapterSccond = new ProgramFirstAdapter(context);
                adapterSccond.setData(data.get(1).getData());
                myHolder.recyclerView.setAdapter(adapterSccond);
                GridLayoutManager managerSecond = new GridLayoutManager(context,2);
                myHolder.recyclerView.setLayoutManager(managerSecond);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView tv_left , tv_right;
        private TextView tv_title,tv_count,tv_num;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_left = (TextView) itemView.findViewById(R.id.tv_program_left);
            tv_right = (TextView) itemView.findViewById(R.id.tv_program_right);
            imageView = (ImageView) itemView.findViewById(R.id.iv_background_first);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title_first);
            tv_count = (TextView) itemView.findViewById(R.id.tv_count_first);
            tv_num = (TextView) itemView.findViewById(R.id.tv_num_first);
        }
    }
    class MyHolder extends RecyclerView.ViewHolder{
       private RecyclerView recyclerView;
        public MyHolder(View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.item_program_rv);
        }
    }
}
