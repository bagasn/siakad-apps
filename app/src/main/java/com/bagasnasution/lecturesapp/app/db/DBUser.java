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
    public static final String FIELD_TEMPAT_LAHIR = "tempat_lahir";
    public static final String FIELD_TANGGAL_LAHIR = "tgl_lahir";
    public static final String FIELD_JENIS_KELAMIN = "jenis_kel";
    public static final String FIELD_ALAMAT = "alamat";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_TAHUN_AKADEMIK = "tahun_akdm";
    public static final String FIELD_FAKULTAS = "fakultas";
    public static final String FIELD_PRODI = "prodi";

    public static final String QUERY_CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS `" + TABLE_NAME + "` ("
            + FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FIELD_NPM + " TEXT, "
            + FIELD_NAMA + " TEXT, "
            + FIELD_TEMPAT_LAHIR + " TEXT, "
            + FIELD_TANGGAL_LAHIR + " TEXT, "
            + FIELD_JENIS_KELAMIN + " TEXT, "
            + FIELD_ALAMAT + " TEXT, "
            + FIELD_EMAIL + " TEXT, "
            + FIELD_TAHUN_AKADEMIK + " TEXT, "
            + FIELD_FAKULTAS + " TEXT, "
            + FIELD_PRODI + " TEXT "
            + ")";

    public static final String[] FIELDS = new String[]{
            FIELD_ID,
            FIELD_NPM,
            FIELD_NAMA,
            FIELD_TEMPAT_LAHIR,
            FIELD_TANGGAL_LAHIR,
            FIELD_JENIS_KELAMIN,
            FIELD_ALAMAT,
            FIELD_EMAIL,
            FIELD_TAHUN_AKADEMIK,
            FIELD_FAKULTAS,
            FIELD_PRODI
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
        cv.put("tempat_lahir", user.getTempatLahir());
        cv.put("tgl_lahir", user.getTanggalLahir());
        cv.put("jenis_kel", user.getJenisKelamin());
        cv.put("alamat", user.getAlamat());
        cv.put("email", user.getEmail());
        cv.put("tahun_akdm", user.getTahunAkademik());
        cv.put("fakultas", user.getFakultas());
        cv.put("prodi", user.getProdi());

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
        private String tempatLahir;
        private String tanggalLahir;
        private String jenisKelamin;
        private String alamat;
        private String email;
        private String tahunAkademik;
        private String fakultas;
        private String prodi;

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

        public String getTempatLahir() {
            return tempatLahir;
        }

        public void setTempatLahir(String tempatLahir) {
            this.tempatLahir = tempatLahir;
        }

        public String getTanggalLahir() {
            return tanggalLahir;
        }

        public void setTanggalLahir(String tanggalLahir) {
            this.tanggalLahir = tanggalLahir;
        }

        public String getJenisKelamin() {
            return jenisKelamin;
        }

        public void setJenisKelamin(String jenisKelamin) {
            this.jenisKelamin = jenisKelamin;
        }

        public String getAlamat() {
            return alamat;
        }

        public void setAlamat(String alamat) {
            this.alamat = alamat;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTahunAkademik() {
            return tahunAkademik;
        }

        public void setTahunAkademik(String tahunAkademik) {
            this.tahunAkademik = tahunAkademik;
        }

        public String getFakultas() {
            return fakultas;
        }

        public void setFakultas(String fakultas) {
            this.fakultas = fakultas;
        }

        public String getProdi() {
            return prodi;
        }

        public void setProdi(String prodi) {
            this.prodi = prodi;
        }
    }

}
