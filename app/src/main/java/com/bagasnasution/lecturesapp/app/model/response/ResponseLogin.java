package com.bagasnasution.lecturesapp.app.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Batavianet on 10/12/2017.
 */

public class ResponseLogin extends ResponseDefault {

    @SerializedName("data")
    @Expose
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data implements Serializable {
        @SerializedName("nim")
        @Expose
        private String nim;
        @SerializedName("nama")
        @Expose
        private String nama;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("jenis-kelamin")
        @Expose
        private String jenisKelamin;
        @SerializedName("link_foto")
        @Expose
        private String linkFoto;

        public String getNim() {
            return nim;
        }

        public void setNim(String nim) {
            this.nim = nim;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getJenisKelamin() {
            return jenisKelamin;
        }

        public void setJenisKelamin(String jenisKelamin) {
            this.jenisKelamin = jenisKelamin;
        }

        public String getLinkFoto() {
            return linkFoto;
        }

        public void setLinkFoto(String linkFoto) {
            this.linkFoto = linkFoto;
        }
    }
}
