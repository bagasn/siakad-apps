package com.bagasnasution.lecturesapp.app.config;


import com.bagasnasution.lecturesapp.BuildConfig;

public class Config {

    /* App Configure */
    public static final String APP_NAME = "LecturesApp";

    /* DATABASE DECLARATION */
    public static final String DATABASE_NAME = "lecturesapp_db_master_lectapp.db";
    public static final int DATABASE_VERSION = 7;

    /** Backend Declaration */

    /* Real */
//    public static final String BASE_URL = "https://siakad-skripsi.000webhostapp.com/";

    /* Dummy */

//  office
    public static final String BASE_URL = "http://10.0.2.2:8080/siakad/";
//  home
//    public static final String BASE_URL = "http://10.0.2.2:8080/siakad/";
//    public static final String BASE_URL = "http://192.168.0.2/siakad/";
//  linux
//    public static final String BASE_URL = "http://192.168.0.2/b/demo/ta-api/";


    public static final String API_TOKEN = "3add0f3378dc0fba4bbf2fab59a312cc";

    public static final String API_CODE_SUCCESS = "0000";

    /* Shared Preferences */
    public static final String PREFERENCES_MASTER = "SharedPreferencesMaster." + BuildConfig.APPLICATION_ID;
    public static final String PREFERENCES_HAS_LOGIN = "SharedPreferencesLoginInitiate" + BuildConfig.APPLICATION_ID;

    public static final String DEFAULT_VALUE_DATE_OF_BIRTH = "1970-01-01";


}
