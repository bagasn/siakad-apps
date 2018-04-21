package com.bagasnasution.lecturesapp.app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bagas on 1/24/2018.
 */

public class ResponseSks extends ResponseDefault {
    @SerializedName("Data")
    @Expose
    private List<DataSks> data;

    public List<DataSks> getData() {
        return data;
    }

    public void setData(List<DataSks> data) {
        this.data = data;
    }

    public class DataSks implements Serializable {
        @SerializedName("KodeMatkul")
        @Expose
        private String kodeMatkul;
        @SerializedName("NamaMatkul")
        @Expose
        private String namaMatkul;
        @SerializedName("sks")
        @Expose
        private String sks;
        @SerializedName("nilai")
        @Expose
        private String nilai;
        @SerializedName("nilaiAkdm")
        @Expose
        private String nilaiAkdm;
        @SerializedName("Semester")
        @Expose
        private String semester;
        @SerializedName("StatusLulus")
        @Expose
        private String statusLulus;
        @SerializedName("Mengulan")
        @Expose
        private String mengulan;

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

        public String getSks() {
            return sks;
        }

        public void setSks(String sks) {
            this.sks = sks;
        }

        public String getNilai() {
            return nilai;
        }

        public void setNilai(String nilai) {
            this.nilai = nilai;
        }

        public String getNilaiAkdm() {
            return nilaiAkdm;
        }

        public void setNilaiAkdm(String nilaiAkdm) {
            this.nilaiAkdm = nilaiAkdm;
        }

        public String getSemester() {
            return semester;
        }

        public void setSemester(String semester) {
            this.semester = semester;
        }

        public String getStatusLulus() {
            return statusLulus;
        }

        public void setStatusLulus(String statusLulus) {
            this.statusLulus = statusLulus;
        }

        public String getMengulan() {
            return mengulan;
        }

        public void setMengulan(String mengulan) {
            this.mengulan = mengulan;
        }
    }
}
