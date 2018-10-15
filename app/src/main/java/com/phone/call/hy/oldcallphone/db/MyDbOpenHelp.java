package com.phone.call.hy.oldcallphone.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hy on 2018/8/15.
 */
public class MyDbOpenHelp extends SQLiteOpenHelper {

    // 数据库版本号
    private static int Version = 1;
    public static final String PEOPLE = "people_info";
    private static final String DB_NAME = "hysqlite";

    public static final String PEOPLE_NAME = "name";
    public static final String PEOPLE_PHONE = "phone";
    public static final String PEOPLE_IMGURL = "imgurl";
    public static final String ID = "_id";

    /**
     * 构造函数
     * 在SQLiteOpenHelper的子类中，必须有该构造函数
     */
    public MyDbOpenHelp(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        // 参数说明
        // context：上下文对象
        // name：数据库名称
        // param：一个可选的游标工厂（通常是 Null）
        // version：当前数据库的版本，值必须是整数并且是递增的状态

        // 必须通过super调用父类的构造函数

        super(context, name, factory, version);
    }

    public MyDbOpenHelp(Context context){
        super(context,DB_NAME,null,Version);

    }


    /**
     * 复写onCreate（）
     * 调用时刻：当数据库第1次创建时调用
     * 作用：创建数据库 表 & 初始化数据
     * SQLite数据库创建支持的数据类型： 整型数据、字符串类型、日期类型、二进制
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        createPeople(db);
    }

    private void createPeople(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+PEOPLE + "("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+PEOPLE_NAME+" VARCHAR,"+PEOPLE_PHONE+" VARCHAR," +
                ""+PEOPLE_IMGURL+" VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
