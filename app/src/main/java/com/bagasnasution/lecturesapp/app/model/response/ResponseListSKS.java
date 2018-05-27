package com.bagasnasution.lecturesapp.app.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bagas on 5/27/2018.
 */

public class ResponseListSKS extends ResponseDefault {
    @SerializedName("data")
    @Expose
    private DataSKS dataSks;

    public DataSKS getDataSks() {
        return dataSks;
    }

    public void setDataSks(DataSKS dataSks) {
        this.dataSks = dataSks;
    }

    public class DataSKS {
        @SerializedName("editable")
        @Expose
        private Boolean editable;
        @SerializedName("maka-kuliah")
        @Expose
        private List<MataKuliah> makaKuliah = null;

        public Boolean getEditable() {
            return editable;
        }

        public void setEditable(Boolean editable) {
            this.editable = editable;
        }

        public List<MataKuliah> getMakaKuliah() {
            return makaKuliah;
        }

        public void setMakaKuliah(List<MataKuliah> makaKuliah) {
            this.makaKuliah = makaKuliah;
        }
    }

    public class MataKuliah {
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
