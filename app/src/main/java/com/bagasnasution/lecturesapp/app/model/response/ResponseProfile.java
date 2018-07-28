package com.bagasnasution.lecturesapp.app.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseProfile extends ResponseDefault {
    @SerializedName("data")
    @Expose
    private DataProfile data;

    public DataProfile getData() {
        return data;
    }

    public void setData(DataProfile data) {
        this.data = data;
    }

    public class DataProfile {
        @SerializedName("profil")
        @Expose
        private Profil profil;
        @SerializedName("kelas")
        @Expose
        private Kelas kelas;

        public Profil getProfil() {
            return profil;
        }

        public void setProfil(Profil profil) {
            this.profil = profil;
        }

        public Kelas getKelas() {
            return kelas;
        }

        public void setKelas(Kelas kelas) {
            this.kelas = kelas;
        }
    }

    public class Profil {
        @SerializedName("npm")
        @Expose
        private String npm;
        @SerializedName("nama")
        @Expose
        private String nama;
        @SerializedName("tmpt_lahir")
        @Expose
        private String tmptLahir;
        @SerializedName("tgl_lahir")
        @Expose
        private String tglLahir;
        @SerializedName("jenis_kelamin")
        @Expose
        private String jenisKelamin;
        @SerializedName("alamat")
        @Expose
        private String alamat;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("link_foto")
        @Expose
        private String linkFoto;
        @SerializedName("tahun_akademik")
        @Expose
        private String tahunAkademik;
        @SerializedName("semester")
        @Expose
        private String semester;
        @SerializedName("fakultas")
        @Expose
        private String fakultas;
        @SerializedName("prodi")
        @Expose
        private String prodi;

        public String getNpm() {
            return npm;
        }

        public void setNpm(String npm) {
            this.npm = npm;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getTmptLahir() {
            return tmptLahir;
        }

        public void setTmptLahir(String tmptLahir) {
            this.tmptLahir = tmptLahir;
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

        public String getLinkFoto() {
            return linkFoto;
        }

        public void setLinkFoto(String linkFoto) {
            this.linkFoto = linkFoto;
        }

        public String getTahunAkademik() {
            return tahunAkademik;
        }

        public void setTahunAkademik(String tahunAkademik) {
            this.tahunAkademik = tahunAkademik;
        }

        public String getSemester() {
            return semester;
        }

        public void setSemester(String semester) {
            this.semester = semester;
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

    public class Kelas {
        @SerializedName("nama_kelas")
        @Expose
        private String namaKelas;
        @SerializedName("semester")
        @Expose
        private String semester;
        @SerializedName("pembimbing")
        @Expose
        private String pembimbing;
        @SerializedName("id-dosen")
        @Expose
        private String idDosen;

        public String getNamaKelas() {
            return namaKelas;
        }

        public void setNamaKelas(String namaKelas) {
            this.namaKelas = namaKelas;
        }

        public String getSemester() {
            return semester;
        }

        public void setSemester(String semester) {
            this.semester = semester;
        }

        public String getPembimbing() {
            return pembimbing;
        }

        public void setPembimbing(String pembimbing) {
            this.pembimbing = pembimbing;
        }

        public String getIdDosen() {
            return idDosen;
        }

        public void setIdDosen(String idDosen) {
            this.idDosen = idDosen;
        }
    }

}
