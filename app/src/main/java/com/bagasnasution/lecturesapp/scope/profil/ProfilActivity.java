package com.bagasnasution.lecturesapp.scope.profil;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bagasnasution.lecturesapp.R;
import com.bagasnasution.lecturesapp.app.config.Config;
import com.bagasnasution.lecturesapp.app.connect.ConnectRetrofit;
import com.bagasnasution.lecturesapp.app.engine.AppActivity;
import com.bagasnasution.lecturesapp.app.engine.AppHelper;
import com.bagasnasution.lecturesapp.app.model.response.ResponseProfile;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Response;

public class ProfilActivity extends AppActivity {

    private static final String TAG = "ProfilActivity";

    private ImageView image_profil;
    private TextView text_npm;
    private TextView text_nama;
    private TextView text_tempatL;
    private TextView text_tglL;
    private TextView text_jenisK;
    private TextView text_alamat;
    private TextView text_email;
    private TextView text_tahun;
    private TextView text_prodi;
    private TextView text_kelas;
    private TextView text_kelas_smt;
    private TextView text_dospem;

    private View view_loading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        image_profil = findViewById(R.id.image_profil);
        text_npm = findViewById(R.id.text_npm);
        text_nama = findViewById(R.id.text_nama);
        text_tempatL = findViewById(R.id.text_tempatLahir);
        text_tglL = findViewById(R.id.text_tglLahir);
        text_jenisK = findViewById(R.id.text_jenis);
        text_alamat = findViewById(R.id.text_alamat);
        text_email = findViewById(R.id.text_email);
        text_tahun = findViewById(R.id.text_tahun);
        text_prodi = findViewById(R.id.text_prodi);
        text_kelas = findViewById(R.id.text_nama_kelas);
        text_kelas_smt = findViewById(R.id.text_kelas_smt);
        text_dospem = findViewById(R.id.text_dospem);

        view_loading = findViewById(R.id.view_loading);
        view_loading.setVisibility(View.VISIBLE);


        requestData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    private void requestData() {

        ConnectRetrofit.getProfil(this, new ConnectRetrofit.OnResponse<ResponseProfile>() {
            @Override
            public void onResponse(Call<ResponseProfile> call, Response<ResponseProfile> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        setDataFrom(response.body().getData());

                    }
                }

                view_loading.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResponseProfile> call, Throwable throwable) {
                AppHelper.showToast(getApplicationContext(), throwable.toString());

                view_loading.setVisibility(View.GONE);
            }
        });

    }

    private void setDataFrom(ResponseProfile.DataProfile data) {
        if (data != null) {
            setDataProfil(data.getProfil());

            setDataKelas(data.getKelas());
        }
    }

    private void setDataProfil(ResponseProfile.Profil data) {
        if (data != null) {

            text_npm.setText(data.getNpm());
            text_nama.setText(data.getNama());
            text_tempatL.setText(data.getTempatLahir());

            try {
                String tanggal = data.getTanggalLahir();
                if (!tanggal.equalsIgnoreCase(Config.DEFAULT_VALUE_DATE_OF_BIRTH)) {
                    Date date = new SimpleDateFormat("yyyy-MM-dd", Locale.US)
                            .parse(tanggal);
                    tanggal = new SimpleDateFormat("d MMMM yyyy", Locale.getDefault())
                            .format(date);
                    text_tglL.setText(tanggal);
                }
            } catch (ParseException e) {
                Log.e(TAG, "setDataProfil: ", e);
            }

            String jenisKelamin = data.getJenisKelamin();
            if (jenisKelamin.equalsIgnoreCase("L")) {
                text_jenisK.setText("Laki-laki");
            } else if (jenisKelamin.equalsIgnoreCase("P")) {
                text_jenisK.setText("Perempuan");
            }

            text_alamat.setText(data.getAlamat());
            text_email.setText(data.getEmail());

            text_tahun.setText(data.getTahunMasuk());
            text_prodi.setText(data.getNamaProdi());

            String link = data.getPhoto();
            if (!link.isEmpty()) {
                Picasso.get()
                        .load(link)
                        .noFade()
                        .placeholder(R.drawable.ic_image_empty)
                        .error(R.drawable.ic_image_empty)
                        .memoryPolicy(MemoryPolicy.NO_STORE)
                        .into(image_profil);
            }

        }
    }

    private void setDataKelas(ResponseProfile.Kelas data) {
        if (data != null) {

            text_kelas.setText(data.getNamaKelompok());

            String semester = "Semester " + data.getSemester();
            text_kelas_smt.setText(semester);

            text_dospem.setText(data.getDpa());

        }
    }
}
