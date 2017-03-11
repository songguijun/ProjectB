package gift.songguijun.lanou.com.projectb.DBHelper;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.Default;
import com.litesuits.orm.db.annotation.Ignore;
import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by dllo on 17/3/9.
 */
@Table("yinyuetai")
public class PersonEntity {
@PrimaryKey(AssignType.AUTO_INCREMENT)
private int id ;
    @NotNull
    private String name ;
    private String singer ;
    private String pic ;

    public PersonEntity( String name, String singer, String pic) {
        this.name = name;
        this.singer = singer;
        this.pic = pic;
    }

    public int getId() {
        return id;
    }

    public PersonEntity setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PersonEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getSinger() {
        return singer;
    }

    public PersonEntity setSinger(String singer){
        this.singer = singer;
        return this;
    }

    public String getPic() {
        return pic;
    }

    public PersonEntity setPic(String pic) {
        this.pic = pic;
        return this;
    }
}
