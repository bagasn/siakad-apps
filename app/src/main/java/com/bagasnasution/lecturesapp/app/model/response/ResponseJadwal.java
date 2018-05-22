package com.bagasnasution.lecturesapp.app.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bagas on 5/14/2018.
 */

public class ResponseJadwal extends ResponseDefault {
    @SerializedName("data")
    @Expose
    private List<JadwalModel> listJadwal = null;


    private class JadwalModel {
        @SerializedName("kode")
        @Expose
        private String kode;
    }
}
