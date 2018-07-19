package com.bagasnasution.lecturesapp.app.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ResponseNews extends ResponseDefault {
    @SerializedName("data")
    @Expose
    private List<News> newsList = null;

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    public class News implements Serializable {
        @SerializedName("id-news")
        @Expose
        private Integer idNews;
        @SerializedName("judul")
        @Expose
        private String judul;
        @SerializedName("waktu")
        @Expose
        private String waktu;
        @SerializedName("tanggal")
        @Expose
        private String tanggal;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("isi-news")
        @Expose
        private String isiNews;

        public Integer getIdNews() {
            return idNews;
        }

        public void setIdNews(Integer idNews) {
            this.idNews = idNews;
        }

        public String getJudul() {
            return judul;
        }

        public void setJudul(String judul) {
            this.judul = judul;
        }

        public String getWaktu() {
            return waktu;
        }

        public void setWaktu(String waktu) {
            this.waktu = waktu;
        }

        public String getTanggal() {
            return tanggal;
        }

        public void setTanggal(String tanggal) {
            this.tanggal = tanggal;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getIsiNews() {
            return isiNews;
        }

        public void setIsiNews(String isiNews) {
            this.isiNews = isiNews;
        }
    }
}
