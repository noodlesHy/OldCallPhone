package com.phone.call.hy.oldcallphone.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.phone.call.hy.oldcallphone.javabean.PeopleInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hy on 2018/8/15.
 */
public class DbHelpManager {
    private static final String TAG = "DbHelpManager";
    private MyDbOpenHelp myDbOpenHelp;

    public DbHelpManager(Context context) {
        myDbOpenHelp = MyDbOpenHelp.getInstance(context.getApplicationContext());
        //第一次调用执行help的oncrete创建表
        myDbOpenHelp.getWritableDatabase();
    }


    public void addPeople(final PeopleInfo info) {
        packDoing(new CallBack() {
            @Override
            public void doit(SQLiteDatabase db) {
                ContentValues cv = new ContentValues();
                cv.put(MyDbOpenHelp.PEOPLE_NAME, info.getName());
                cv.put(MyDbOpenHelp.PEOPLE_PHONE, info.getPhone());
                cv.put(MyDbOpenHelp.PEOPLE_IMGURL, info.getImgurl());
               long log =  db.insert(MyDbOpenHelp.PEOPLE, null,cv);
                Log.i(TAG, "doit: "+ log);
                db.setTransactionSuccessful();
            }
        });
    }

    public void deletePeople(final PeopleInfo info) {
        packDoing(new CallBack() {
            @Override
            public void doit(SQLiteDatabase db) {
                db.delete(MyDbOpenHelp.PEOPLE, MyDbOpenHelp.ID + "=?", new String[]{info.getId() + ""});
                db.setTransactionSuccessful();
            }
        });
    }

    private SQLiteDatabase getDatabase() {
        SQLiteDatabase db = myDbOpenHelp.getWritableDatabase();
        return db;
    }

    public void updatePeople(final PeopleInfo info) {

        packDoing(new CallBack() {
            @Override
            public void doit(SQLiteDatabase db) {
                ContentValues cv = new ContentValues();
                cv.put(MyDbOpenHelp.PEOPLE_NAME, info.getName());
                cv.put(MyDbOpenHelp.PEOPLE_PHONE, info.getPhone());
                cv.put(MyDbOpenHelp.PEOPLE_IMGURL, info.getImgurl());
                db.update(MyDbOpenHelp.PEOPLE, cv, MyDbOpenHelp.ID + "=?", new String[]{info.getId() + ""});
                db.setTransactionSuccessful();
                closeDatabase(db);
            }
        });

    }

    public List<PeopleInfo> queryAllPeople() {
       final List<PeopleInfo> infos = new ArrayList<>();
        packDoing(new CallBack() {
            @Override
            public void doit(SQLiteDatabase db) {

                Cursor cursor = db.rawQuery("SELECT * FROM "+MyDbOpenHelp.PEOPLE+"", null);
                if(cursor!=null&&cursor.getCount()!=0){
                    while (cursor.moveToNext()){
                        PeopleInfo info = new PeopleInfo();
                        info.setId(cursor.getInt(cursor.getColumnIndex(MyDbOpenHelp.ID)));
                        info.setName(cursor.getString(cursor.getColumnIndex(MyDbOpenHelp.PEOPLE_NAME)));
                        info.setPhone(cursor.getString(cursor.getColumnIndex(MyDbOpenHelp.PEOPLE_PHONE)));
                        info.setImgurl(cursor.getString(cursor.getColumnIndex(MyDbOpenHelp.PEOPLE_IMGURL)));
                        infos.add(info);
                        Log.i(TAG, "doit: infos" + info.getImgurl());
                    }
                }
            }
        });

        return infos;
    }

    public void packDoing(CallBack callBack){
        synchronized (TAG) {
            SQLiteDatabase db = getDatabase();
            db.beginTransaction();
            try {
                callBack.doit(db);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                db.endTransaction();
                Log.i(TAG, "packDoing: 结束事务");
            }
        }
    }

    private interface CallBack{
        void doit(SQLiteDatabase db);
    }

    private void closeDatabase(SQLiteDatabase db) {
        db.endTransaction();
    }

}
