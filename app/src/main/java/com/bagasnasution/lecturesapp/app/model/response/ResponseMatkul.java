package com.bagasnasution.lecturesapp.app.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bagas on 5/23/2018.
 */

public class ResponseMatkul extends ResponseDefault {
    @SerializedName("data")
    @Expose
    private List<MatKul> listMatkul = null;

    public List<MatKul> getListMatkul() {
        return listMatkul;
    }

    public void setListMatkul(List<MatKul> listMatkul) {
        this.listMatkul = listMatkul;
    }

    public class MatKul {
        @SerializedName("data")
        @Expose
        private String kode;
        @SerializedName("nama")
        @Expose
        private String nama;
        @SerializedName("semester")
        @Expose
        private String semester;
        @SerializedName("sks")
        @Expose
        private String sks;

        public String getKode() {
            return kode;
        }

        public void setKode(String kode) {
            this.kode = kode;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getSemester() {
            return semester;
        }

        public void setSemester(String semester) {
            this.semester = semester;
        }

        public String getSks() {
            return sks;
        }

        public void setSks(String sks) {
            this.sks = sks;
        }
    }
}
