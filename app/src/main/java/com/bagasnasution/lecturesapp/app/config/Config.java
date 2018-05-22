package com.bagasnasution.lecturesapp.app.config;


public class Config {

    /* App Configure */
    public static final String APP_NAME = "LecturesApp";

    /* DATABASE DECLARATION */
    public static final String DATABASE_NAME = "lecturesapp_db_master_lectapp.db";
    public static final int DATABASE_VERSION = 4;

    /** Backend Declaration */

    /* Real */
//    public static final String BASE_URL = "https://itandcrime.000webhostapp.com/api/";

    /* Dummy */

//  office
//    public static final String BASE_URL = "http://10.0.2.2:8080/b/demo_app/ta-api/";
//  home
//    public static final String BASE_URL = "http://10.0.2.2:8080/b/demo_app/ta-api/";
//  linux
    public static final String BASE_URL = "http://10.0.2.2/b/demo/ta-api/";


    public static final String API_TOKEN = "3add0f3378dc0fba4bbf2fab59a312cc";

    public static final String API_CODE_SUCCESS = "0000";

    /* Shared Preferences */
    public static final String PREFERENCES_MASTER = "SharedPreferencesMaster" + APP_NAME;
    public static final String PREFERENCES_HAS_LOGIN = "SharedPreferencesLoginInitiate" + APP_NAME;


}
