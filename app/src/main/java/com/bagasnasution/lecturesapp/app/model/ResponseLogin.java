package com.bagasnasution.lecturesapp.app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Batavianet on 10/12/2017.
 */

public class ResponseLogin extends ResponseDefault {

    @SerializedName("Data")
    @Expose
    private List<Data> dataList;

    public List<Data> getDataList() {
        return dataList;
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
    }

    public class Data implements Serializable {
        @SerializedName("NPM")
        @Expose
        private String nPM;
        @SerializedName("Nama")
        @Expose
        private String nama;
        @SerializedName("TempatLahir")
        @Expose
        private String tempatLahir;
        @SerializedName("TglLahir")
        @Expose
        private String tglLahir;
        @SerializedName("JenisKelamin")
        @Expose
        private String jenisKelamin;
        @SerializedName("Alamat")
        @Expose
        private String alamat;
        @SerializedName("Email")
        @Expose
        private String email;
        @SerializedName("TahunAkademik")
        @Expose
        private String tahunAkademik;
        @SerializedName("Fakultas")
        @Expose
        private String fakultas;
        @SerializedName("Prodi")
        @Expose
        private String prodi;

        public String getnPM() {
            return nPM;
        }

        public void setnPM(String nPM) {
            this.nPM = nPM;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getTempatLahir() {
            return tempatLahir;
        }

        public void setTempatLahir(String tempatLahir) {
            this.tempatLahir = tempatLahir;
        }

        public String getTglLahir() {
            return tglLahir;
        }

        public void setTglLahir(String tglLahir) {
            this.tglLahir = tglLahir;
        }

        public String getJenisKelamin() {
            return jenisKelamin;
        }

        public void setJenisKelamin(String jenisKelamin) {
            this.jenisKelamin = jenisKelamin;
        }

        public String getAlamat() {
            return alamat;
        }

        public void setAlamat(String alamat) {
            this.alamat = alamat;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTahunAkademik() {
            return tahunAkademik;
        }

        public void setTahunAkademik(String tahunAkademik) {
            this.tahunAkademik = tahunAkademik;
        }

        public String getFakultas() {
            return fakultas;
        }

        public void setFakultas(String fakultas) {
            this.fakultas = fakultas;
        }

        public String getProdi() {
            return prodi;
        }

        public void setProdi(String prodi) {
            this.prodi = prodi;
        }
    }

}
