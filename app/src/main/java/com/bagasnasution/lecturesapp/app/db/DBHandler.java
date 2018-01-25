package com.bagasnasution.lecturesapp.app.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
                onCreate(db);
                Log.e(TAG, "---->>> Delete Table Succes");
            }
        }
    }

    public long insertDataUser(ContentValues values) throws SQLiteException {
        db = helper.getWritableDatabase();
        long rowId = db.insert(DBUser.TABLE_NAME, null, values);
        if (rowId == -1)
            throw new SQLiteException("Fucking Insert is Failed");
        Log.e(TAG, "----->> Insert DB User Berhasil");
        db.close();
        return rowId;
    }

    public DBUser.User getDataUser() throws SQLiteException {
        db = helper.getReadableDatabase();
        Cursor cursor = db.query(DBUser.TABLE_NAME, DBUser.FIELDS, null, null, null, null, null);

        DBUser.User user = new DBUser.User();

        if (cursor.moveToFirst()) {
            user.setNpm(cursor.getString(1));
            user.setNama(cursor.getString(2));
            user.setTempatLahir(cursor.getString(3));
            user.setTanggalLahir(cursor.getString(4));
            user.setJenisKelamin(cursor.getString(5));
            user.setAlamat(cursor.getString(6));
            user.setEmail(cursor.getString(7));
            user.setTahunAkademik(cursor.getString(8));
            user.setFakultas(cursor.getString(9));
            user.setProdi(cursor.getString(10));
        }

        cursor.close();
        db.close();

        return user;
    }

    public int deleteDataUser() throws SQLiteException {
        db = helper.getWritableDatabase();
        int delId = db.delete(DBUser.TABLE_NAME, null, null);
        db.close();
        return delId;
    }

}
