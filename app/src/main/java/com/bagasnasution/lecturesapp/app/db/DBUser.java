package com.bagasnasution.lecturesapp.app.db;



public class DBUser {

    public static final String TABLE_NAME = "tabol_user";

    public static final String FIELD_NAMA   = "nama";
    public static final String FIELD_EMAIL  = "email";
    public static final String FIELD_NPM    = "npm";

    public static final String QUERY_CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS `" + TABLE_NAME + "` ("
            + FIELD_NAMA    + " TEXT,"
            + FIELD_EMAIL   + "TEXT,"
            + FIELD_NPM     + "TEXT"
            + ")";
}
