package com.msht.mshtlpgmaster.sqlite;

/*
package com.msht.mshtLpg.mshtLpgMaster.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.msht.mshtLpgMaster.application.LPGApplication;
import com.msht.mshtLpgMaster.util.LogUtils;

public class SqliteBaseOpenHelper extends SQLiteOpenHelper {


    private static volatile SqliteBaseOpenHelper sqliteBaseOpenHelper;

    public static SqliteBaseOpenHelper getInstance() {
        if (null == sqliteBaseOpenHelper) {
            synchronized (SqliteBaseOpenHelper.class) {
                if (null == sqliteBaseOpenHelper) {
                    sqliteBaseOpenHelper = new SqliteBaseOpenHelper(LPGApplication.getLPGApplicationContext());
                }
            }
        }
        return sqliteBaseOpenHelper;
    }


    private   SqliteBaseOpenHelper(Context context) {
        super(context, SqliteBaseConstans.CLIPBOARD_DATABASE_NAME, null, SqliteBaseConstans.CLIPBOARD_DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createAllTable(db);
        LogUtils.d(SqliteBaseOpenHelper.class.getSimpleName(), "create  " + SqliteBaseConstans.CLIPBOARD_DATABASE_NAME + "   database ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    //创建所有表
    private void createAllTable(SQLiteDatabase db) {
        String pinsSql = "CREATE TABLE IF NOT EXISTS " + SqliteBaseConstans.CLIPBOARD_PINS_TABLE + " (" + SqliteBaseConstans._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SqliteBaseConstans.CLIPBOARD_PINS_CONTENT_COLUMN_NAME + " TEXT NOT NULL DEFAULT '' ," + "UNIQUE (" + SqliteBaseConstans.CLIPBOARD_PINS_CONTENT_COLUMN_NAME + ")" + ");";
        String recentSql = "CREATE TABLE IF NOT EXISTS " + SqliteBaseConstans.CLIPBOARD_RECENT_TABLE + " (" + SqliteBaseConstans._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SqliteBaseConstans.CLIPBOARD_RECENT_CONTENT_COLUMN_NAME + " TEXT NOT NULL DEFAULT '' ,"
                + SqliteBaseConstans.CLIPBOARD_RECENT_ISPINED_COLUMN_NAME + " INTEGER NOT NULL DEFAULT 0 ," + "UNIQUE (" + SqliteBaseConstans.CLIPBOARD_RECENT_CONTENT_COLUMN_NAME + ")" + ");";
        try {
            db.execSQL(pinsSql);
            db.execSQL(recentSql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogUtils.d(SqliteBaseOpenHelper.class.getSimpleName(), "create recent table and pins table");
    }
}
*/
public class SqliteBaseOpenHelper{}