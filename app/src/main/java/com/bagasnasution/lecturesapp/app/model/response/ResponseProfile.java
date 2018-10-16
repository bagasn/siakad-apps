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
        @SerializedName("tempat_lahir")
        @Expose
        private String tempatLahir;
        @SerializedName("tanggal_lahir")
        @Expose
        private String tanggalLahir;
        @SerializedName("jenis_kelamin")
        @Expose
        private String jenisKelamin;
        @SerializedName("alamat")
        @Expose
        private String alamat;
        @SerializedName("agama")
        @Expose
        private String agama;
        @SerializedName("telepon")
        @Expose
        private String telepon;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("stat_mar")
        @Expose
        private String statMar;
        @SerializedName("photo")
        @Expose
        private String photo;
        @SerializedName("tahun_masuk")
        @Expose
        private String tahunMasuk;
        @SerializedName("nama_prodi")
        @Expose
        private String namaProdi;
        @SerializedName("handphone")
        @Expose
        private String handphone;
        @SerializedName("sekolah_asal")
        @Expose
        private String sekolahAsal;
        @SerializedName("alamat_sekolah")
        @Expose
        private String alamatSekolah;
        @SerializedName("pt_asal")
        @Expose
        private String ptAsal;
        @SerializedName("alamat_pt")
        @Expose
        private String alamatPt;
        @SerializedName("no_ijazah")
        @Expose
        private String noIjazah;

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

        public String getTempatLahir() {
            return tempatLahir;
        }

        public void setTempatLahir(String tempatLahir) {
            this.tempatLahir = tempatLahir;
        }

        public String getTanggalLahir() {
            return tanggalLahir;
        }

        public void setTanggalLahir(String tanggalLahir) {
            this.tanggalLahir = tanggalLahir;
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

        public String getAgama() {
            return agama;
        }

        public void setAgama(String agama) {
            this.agama = agama;
        }

        public String getTelepon() {
            return telepon;
        }

        public void setTelepon(String telepon) {
            this.telepon = telepon;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getStatMar() {
            return statMar;
        }

        public void setStatMar(String statMar) {
            this.statMar = statMar;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getTahunMasuk() {
            return tahunMasuk;
        }

        public void setTahunMasuk(String tahunMasuk) {
            this.tahunMasuk = tahunMasuk;
        }

        public String getNamaProdi() {
            return namaProdi;
        }

        public void setNamaProdi(String namaProdi) {
            this.namaProdi = namaProdi;
        }

        public String getHandphone() {
            return handphone;
        }

        public void setHandphone(String handphone) {
            this.handphone = handphone;
        }

        public String getSekolahAsal() {
            return sekolahAsal;
        }

        public void setSekolahAsal(String sekolahAsal) {
            this.sekolahAsal = sekolahAsal;
        }

        public String getAlamatSekolah() {
            return alamatSekolah;
        }

        public void setAlamatSekolah(String alamatSekolah) {
            this.alamatSekolah = alamatSekolah;
        }

        public String getPtAsal() {
            return ptAsal;
        }

        public void setPtAsal(String ptAsal) {
            this.ptAsal = ptAsal;
        }

        public String getAlamatPt() {
            return alamatPt;
        }

        public void setAlamatPt(String alamatPt) {
            this.alamatPt = alamatPt;
        }

        public String getNoIjazah() {
            return noIjazah;
        }

        public void setNoIjazah(String noIjazah) {
            this.noIjazah = noIjazah;
        }
    }

    public class Kelas {

        @SerializedName("nama_kelompok")
        @Expose
        private String namaKelompok;
        @SerializedName("tahun")
        @Expose
        private String tahun;
        @SerializedName("semester")
        @Expose
        private String semester;
        @SerializedName("dpa")
        @Expose
        private String dpa;
        @SerializedName("dpa_id")
        @Expose
        private String dpaId;

        public String getNamaKelompok() {
            return namaKelompok;
        }

        public void setNamaKelompok(String namaKelompok) {
            this.namaKelompok = namaKelompok;
        }

        public String getTahun() {
            return tahun;
        }

        public void setTahun(String tahun) {
            this.tahun = tahun;
        }

        public String getSemester() {
            return semester;
        }

        public void setSemester(String semester) {
            this.semester = semester;
        }

        public String getDpa() {
            return dpa;
        }

        public void setDpa(String dpa) {
            this.dpa = dpa;
        }

        public String getDpaId() {
            return dpaId;
        }

        public void setDpaId(String dpaId) {
            this.dpaId = dpaId;
        }
    }

}
