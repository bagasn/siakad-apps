package com.bagasnasution.lecturesapp.app.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class DBUser {

    public static final String TABLE_NAME = "tabol_user";

    public static final String FIELD_ID = "_id";
    public static final String FIELD_NPM = "npm";
    public static final String FIELD_NAMA = "nama";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_JENIS_KELAMIN = "jenis_kel";
    public static final String FIELD_LINK_FOTO = "link_foto";

    public static final String QUERY_CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS `" + TABLE_NAME + "` ("
            + FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FIELD_NPM + " TEXT, "
            + FIELD_NAMA + " TEXT, "
            + FIELD_EMAIL + " TEXT, "
            + FIELD_JENIS_KELAMIN + " TEXT, "
            + FIELD_LINK_FOTO + " TEXT "
            + ")";

    public static final String[] FIELDS = new String[]{
            FIELD_ID,
            FIELD_NPM,
            FIELD_NAMA,
            FIELD_EMAIL,
            FIELD_JENIS_KELAMIN,
            FIELD_LINK_FOTO
    };


    public static boolean insertDataUser(Context context, User user) {

        DBHandler handler = new DBHandler(context);
        long rowId = handler.insertDataUser(getContentValues(user));

        if (rowId != -1) {
            return true;
        }
        Log.e("DBUser", "---Inset into db user--->> Row id: " + rowId);

        return false;
    }

    private static ContentValues getContentValues(User user) {
        ContentValues cv = new ContentValues();

        cv.put("npm", user.getNpm());
        cv.put("nama", user.getNama());
        cv.put("email", user.getEmail());
        cv.put("jenis_kel", user.getJenisKelamin());
        cv.put("link_foto", user.getLinkFoto());

        return cv;
    }

    public static User getDataUser(Context context) {
        try {
            DBHandler handler = new DBHandler(context);
            User user = handler.getDataUser();
            return user;
        } catch (SQLiteException e) {
            Log.e("DBUser", e.toString(), e);
        }
        return null;
    }

    public static class User {
        private String id;
        private String npm;
        private String nama;
        private String email;
        private String jenisKelamin;
        private String linkFoto;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNpm() {
            return npm;
        }

        public void setNpm(String npm) {
            this.npm = npm;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getJenisKelamin() {
            return jenisKelamin;
        }

        public void setJenisKelamin(String jenisKelamin) {
            this.jenisKelamin = jenisKelamin;
        }

        public String getLinkFoto() {
            return linkFoto;
        }

        public void setLinkFoto(String linkFoto) {
            this.linkFoto = linkFoto;
        }
    }
}
