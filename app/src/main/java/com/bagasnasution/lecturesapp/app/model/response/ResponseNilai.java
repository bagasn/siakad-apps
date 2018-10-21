package com.bagasnasution.lecturesapp.app.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bagas on 1/24/2018.
 */

public class ResponseNilai extends ResponseDefault {
    @SerializedName("data")
    @Expose
    private List<DataNilai> data;

    public List<DataNilai> getData() {
        return data;
    }

    public void setData(List<DataNilai> data) {
        this.data = data;
    }

    public class DataNilai implements Serializable {
        @SerializedName("kode-matkul")
        @Expose
        private String kodeMatkul;
        @SerializedName("nama-makul")
        @Expose
        private String namaMatkul;
        @SerializedName("sks")
        @Expose
        private Integer sks;
        @SerializedName("nilai")
        @Expose
        private Double nilai;
        @SerializedName("nilai-akdm")
        @Expose
        private String nilaiAkdm;
        @SerializedName("semester")
        @Expose
        private Integer semester;
        @SerializedName("status")
        @Expose
        private Integer statusLulus;

        public String getKodeMatkul() {
            return kodeMatkul;
        }

        public void setKodeMatkul(String kodeMatkul) {
            this.kodeMatkul = kodeMatkul;
        }

        public String getNamaMatkul() {
            return namaMatkul;
        }

        public void setNamaMatkul(String namaMatkul) {
            this.namaMatkul = namaMatkul;
        }

        public Integer getSks() {
            return sks;
        }

        public void setSks(Integer sks) {
            this.sks = sks;
        }

        public Double getNilai() {
            return nilai;
        }

        public void setNilai(Double nilai) {
            this.nilai = nilai;
        }

        public String getNilaiAkdm() {
            return nilaiAkdm;
        }

        public void setNilaiAkdm(String nilaiAkdm) {
            this.nilaiAkdm = nilaiAkdm;
        }

        public Integer getSemester() {
            return semester;
        }

        public void setSemester(Integer semester) {
            this.semester = semester;
        }

        public Integer getStatusLulus() {
            return statusLulus;
        }

        public void setStatusLulus(Integer statusLulus) {
            this.statusLulus = statusLulus;
        }
    }
}
