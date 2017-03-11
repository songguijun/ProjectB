package gift.songguijun.lanou.com.projectb.mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.litesuits.orm.LiteOrm;

import java.util.List;

import gift.songguijun.lanou.com.projectb.DBHelper.PersonEntity;
import gift.songguijun.lanou.com.projectb.R;

public class CollectActivity extends AppCompatActivity {
    private ListView lvCollect ;
    private LiteOrm liteOrm ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        liteOrm = LiteOrm.newSingleInstance(this,"yinyuetai.db");
        lvCollect = (ListView) findViewById(R.id.lv_collect);
        List<PersonEntity> allData = liteOrm.query(PersonEntity.class);
        CollectAdapter collectAdapter = new CollectAdapter(this);
        collectAdapter.setData(allData);
        lvCollect.setAdapter(collectAdapter );


    }
}
