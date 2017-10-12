package com.bagasnasution.lecturesapp.app.db;



public class DBUser {

    public static final String TABLE_NAME = "tabol_user";

    public static final String FIELD_ID            = "_id";
    public static final String FIELD_NPM            = "npm";
    public static final String FIELD_NAMA           = "nama";
    public static final String FIELD_TEMPAT_LAHIR   = "tempat_lahir";
    public static final String FIELD_TANGGAL_LAHIR  = "tgl_lahir";
    public static final String FIELD_JENIS_KELAMIN  = "jenis_kel";
    public static final String FIELD_ALAMAT         = "alamat";
    public static final String FIELD_EMAIL          = "email";
    public static final String FIELD_TAHUN_AKADEMIK = "tahun_akdm";
    public static final String FIELD_FAKULTAS       = "fakultas";
    public static final String FIELD_PRODI          = "prodi";

    public static final String QUERY_CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS `" + TABLE_NAME + "` ("
            + FIELD_ID               + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FIELD_NPM              + " TEXT, "
            + FIELD_NAMA             + " TEXT, "
            + FIELD_TEMPAT_LAHIR     + " TEXT, "
            + FIELD_TANGGAL_LAHIR    + " TEXT, "
            + FIELD_JENIS_KELAMIN    + " TEXT, "
            + FIELD_ALAMAT           + " TEXT, "
            + FIELD_EMAIL            + " TEXT, "
            + FIELD_TAHUN_AKADEMIK   + " TEXT, "
            + FIELD_FAKULTAS         + " TEXT, "
            + FIELD_PRODI            + " TEXT "
            + ")";
}
