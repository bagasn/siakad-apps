package com.bagasnasution.lecturesapp.app.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bagas on 5/23/2018.
 */

public class ResponseCurrentJadwal extends ResponseDefault {
    @SerializedName("data")
    @Expose
    private List<Matkul> listMatkul = null;

    public List<Matkul> getListMatkul() {
        return listMatkul;
    }

    public void setListMatkul(List<Matkul> listMatkul) {
        this.listMatkul = listMatkul;
    }

    public class Matkul {
        @SerializedName("kode")
        @Expose
        private String kode;
        @SerializedName("matkul")
        @Expose
        private String matkul;
        @SerializedName("waktu")
        @Expose
        private String waktu;
        @SerializedName("kelas")
        @Expose
        private String kelas;
        @SerializedName("dosen")
        @Expose
        private String dosen;

        public String getKode() {
            return kode;
        }

        public void setKode(String kode) {
            this.kode = kode;
        }

        public String getMatkul() {
            return matkul;
        }

        public void setMatkul(String matkul) {
            this.matkul = matkul;
        }

        public String getWaktu() {
            return waktu;
        }

        public void setWaktu(String waktu) {
            this.waktu = waktu;
        }

        public String getKelas() {
            return kelas;
        }

        public void setKelas(String kelas) {
            this.kelas = kelas;
        }

        public String getDosen() {
            return dosen;
        }

        public void setDosen(String dosen) {
            this.dosen = dosen;
        }
    }
}
