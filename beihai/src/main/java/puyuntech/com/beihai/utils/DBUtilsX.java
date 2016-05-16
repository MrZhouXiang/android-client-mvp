package puyuntech.com.beihai.utils;

import org.xutils.DbManager;
import org.xutils.x;

import java.io.File;

/**
 * 作者：zx
 * 时间：2016-03-01 下午 17:11
 * 描述：数据库工具类
 */
public class DBUtilsX {
    public static DbManager db;
    public static int version = 1;//数据库版本

    public static void init() {
        if (DBUtilsX.db == null) {
            DBUtilsX.db = x.getDb(daoConfig);
        }
    }

    /**
     * xutilsDB初始化
     */
    static DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
            .setDbName("beihai.db")
                    // 不设置dbDir时, 默认存储在app的私有目录.
            .setDbDir(new File("/sdcard")) // "sdcard"的写法并非最佳实践, 这里为了简单, 先这样写了.
            .setDbVersion(version)
            .setDbOpenListener(new DbManager.DbOpenListener() {
                @Override
                public void onDbOpened(DbManager db) {
                    // 开启WAL, 对写入加速提升巨大
                    db.getDatabase().enableWriteAheadLogging();
                }
            })
            .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                @Override
                public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                    // TODO: ...
                    // db.addColumn(...);
                    // db.dropTable(...);
                    // ...
                    // or
                    // db.dropDb();
                }
            });
}
