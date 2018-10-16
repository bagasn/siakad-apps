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
        @SerializedName("kode")
        @Expose
        private String kode;
        @SerializedName("nama")
        @Expose
        private String nama;
        @SerializedName("semester")
        @Expose
        private Integer semester;
        @SerializedName("sks")
        @Expose
        private Integer sks;

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

        public Integer getSemester() {
            return semester;
        }

        public void setSemester(Integer semester) {
            this.semester = semester;
        }

        public Integer getSks() {
            return sks;
        }

        public void setSks(Integer sks) {
            this.sks = sks;
        }
    }
}
