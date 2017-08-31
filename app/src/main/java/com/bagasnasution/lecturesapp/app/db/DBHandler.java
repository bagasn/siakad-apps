package com.bagasnasution.lecturesapp.app.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.bagasnasution.lecturesapp.app.config.Config;

/**
 * Created by Bagas on 24/08/2017.
 */

public class DBHandler {
    private static final String TAG = "DBHandler";
    private Context context;
    private SQLiteDatabase db;
    private SQLiteHelper helper;

    public DBHandler(Context context) {
        this.context = context;
        helper = new SQLiteHelper(context);
    }

    private class SQLiteHelper extends SQLiteOpenHelper {

        public SQLiteHelper(Context context) {
            super(context, Config.DATABASE_NAME, null, Config.DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(DBUser.QUERY_CREATE_TABLE_USER);
            } catch (SQLiteException e) {
                Log.e(TAG, "---->> Error : " + e.toString());
            } finally {
                Log.e(TAG, "---->>> Create Table Succes");
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL("DROP TABLE IF EXISTS " + DBUser.TABLE_NAME);
            } catch (SQLiteException e) {
                Log.e(TAG, "---->> Error : " + e.toString());
            } finally {
                Log.e(TAG, "---->>> Delete Table Succes");
            }
        }
    }

    public void insertDataUser(ContentValues values) throws SQLiteException {
        db = helper.getWritableDatabase();
        db.insert(DBUser.TABLE_NAME, null, values);
        Log.e(TAG, "----->> Insert DB User Berhasil");
        db.close();
    }
}
