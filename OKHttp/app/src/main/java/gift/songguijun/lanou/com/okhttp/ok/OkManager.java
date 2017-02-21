package gift.songguijun.lanou.com.okhttp.ok;

/**
 * Created by dllo on 17/2/17.
  OkHttp 封装类
 此类要单列
 */

/**
 * 什么是单列
 *
 */
public class OkManager {
    /**
     * 单利的写法 私有构造方法
     * 既然构造方法私有了 外部无法创建对象 Java 一个 对象使用的语言 我们还得需要一个对外提供对象的方法 在这个方法中对象单利进行处理
     * 对外提供获取单利对象的方法
     * - 定义当前类的静态对象
     * - 对外提供静态获取的方法
     */
    private OkManager(){

    }

    private static OkManager inistance;

    public static OkManager getinistance(){
        //判断当前类对象是否为空
        if (inistance == null){
            //同步扫描内存判断对象是否为空
            //线程同步锁
            synchronized (OkManager.class){
                if (inistance == null){
                    inistance = new OkManager();
                }
            }
        }
        return inistance;
    }
}
