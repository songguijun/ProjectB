package gift.lin.lanou.com.okreviewdemo.ok;

/**
 * Created by dllo on 17/2/17.
 */

/**
 * 单例类
 * 此类要单例
 */

/**
 *  1.什么是单例
 *
 */
public class OkManager {
    /**
     * 单例模式写法 : (双重校验锁)
     * 1.私有构造方法 : 不允许外部调用new的方法
     * (外部无法创建当前类对象 , java一个对象使用语言还需要对外提供获取对象的方法
     *  - 在这个方法中对对象单例进行处理)
     * 2.对外提供获取单例对象的方法
     *  - 定义当前类的静态类对象
     *  - 对外提供静态获取方法
     *
     */
    //1
    private OkManager(){

    }
    // 2-1
    private static OkManager instance ;
    // 2-2
    public static OkManager getInstance(){
        //单例写法之 : 双重校验锁
        // 判断当前类对象是否为null
        if (instance == null){
            // 同步扫描内存 , 判断当前对象是否为null
            // 线程同步锁
            synchronized (OkManager.class){
                if (instance == null){
                    instance = new OkManager();
                }
            }
        }
        return instance ;
    }



}
