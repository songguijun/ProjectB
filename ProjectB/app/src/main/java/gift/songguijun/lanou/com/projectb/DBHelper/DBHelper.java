package gift.songguijun.lanou.com.projectb.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dllo on 17/3/9.
 */

public class DBHelper extends SQLiteOpenHelper{
    private Context context ;
    public static final String DB_NAME = "yinyuetai.db";
    public static final String TABLE_NAME = "yinyuetai";
    public static final String PIC_COLUMN = "pic";
    public static final String NAME_COLUMN = "name";
    public static final String SINGER_COLUMN = "singer";


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context ;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createStr = "crete table" + TABLE_NAME + "(_id integer primary key autoincrement not null," + PIC_COLUMN + " text," + NAME_COLUMN + "text," + SINGER_COLUMN + "text";
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
