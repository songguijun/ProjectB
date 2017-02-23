package gift.songguijun.lanou.com.projectb.v.program;

import android.content.Context;
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

public class ProgramFirstAdapter extends RecyclerView.Adapter<ProgramFirstAdapter.MyViewHolder> {
    private List<ProgramBean.DataBeanX.DataBean>data;
    private Context context;

    public void setData(List<ProgramBean.DataBeanX.DataBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public ProgramFirstAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_program_second,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_title.setText(data.get(position).getTitle());
        holder.tv_count.setText(data.get(position).getDesc());
        holder.tv_num.setText(data.get(position).getTotalView()+"");
        Picasso.with(context).load(data.get(position).getPosterPic()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data != null ?4:0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView tv_title , tv_count,tv_num;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_item_program_name);
            tv_count = (TextView) itemView.findViewById(R.id.tv_item_program_singer);
            tv_num = (TextView) itemView.findViewById(R.id.tv_item_program_num);
            imageView = (ImageView) itemView.findViewById(R.id.iv_item_program_listview_gridview);
        }
    }
}
