package com.bagasnasution.lecturesapp.scope.nilai;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bagasnasution.lecturesapp.R;
import com.bagasnasution.lecturesapp.app.connect.ConnectRetrofit;
import com.bagasnasution.lecturesapp.app.engine.AppFragment;
import com.bagasnasution.lecturesapp.app.engine.AppHelper;
import com.bagasnasution.lecturesapp.app.model.response.ResponseNilai;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Bagas on 07/10/2017.
 */

public class NilaiFragment extends AppFragment implements RecyclerNilaiAdapter.OnSksClickListener {

    private RecyclerView rclr_sks;
    private RecyclerNilaiAdapter adapter_sks;
    private View view_loading;
    private TextView txvw_errorMessage;
    private TextView txvw_totalSks;
    private TextView txvw_totalNilai;
    private ProgressBar prg_loading;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sks, container, false);
        rclr_sks = view.findViewById(R.id.rclr_sks);
        rclr_sks.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        view_loading = view.findViewById(R.id.view_loading);
        txvw_totalNilai = view.findViewById(R.id.txvw_totalNilai);
        txvw_totalSks = view.findViewById(R.id.txvw_totalSks);
        txvw_errorMessage = view.findViewById(R.id.txvw_errorMessage);
        prg_loading = view.findViewById(R.id.prg_loading);

        getDataSks();

        return view;
    }

    private void setContent(ResponseNilai data) {
        int totalSks = 0;
        int iEvaluasi = 0;
        int countMk = data.getData().size();

        for (int i = 0; i < countMk; i++) {
            try {
                ResponseNilai.DataSks item = data.getData().get(i);

                String temp_akdm = item.getNilaiAkdm();

                int value_akdm = akdmGetValue(temp_akdm);

                iEvaluasi += value_akdm;

                int temp_sks = item.getSks();

                if (item.getStatusLulus().equals(1)) {
                    totalSks += temp_sks;
                }

            } catch (Exception e) {
                Log.e(getTag(), e.toString(), e);
            }
        }

        double evaluasi = ((double) iEvaluasi) / ((double) countMk);

        String eva = String.format("%.2f", evaluasi);

        txvw_totalNilai.setText(eva);
        txvw_totalSks.setText(String.valueOf(totalSks));
    }

    private int akdmGetValue(String nilanAkdm) {
        switch (nilanAkdm.toUpperCase()) {
            case "A":
                return 4;
            case "B":
                return 3;
            case "C":
                return 2;
            case "D":
                return 1;
            default:
                return 0;
        }
    }

    private void getDataSks() {
        startLoading();
        ConnectRetrofit.getNilai(getActivity().getApplicationContext(), new ConnectRetrofit.OnResponse<ResponseNilai>() {
            @Override
            public void onResponse(Call<ResponseNilai> call, Response<ResponseNilai> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        adapter_sks = new RecyclerNilaiAdapter(getActivity().getApplicationContext(), response.body().getData());
                        adapter_sks.setOnSksClickListener(NilaiFragment.this);
                        rclr_sks.setAdapter(adapter_sks);

                        if (!response.body().getCode().equals("0000")) {
                            Toast.makeText(getActivity().getApplicationContext(), response.body().getMessage() +
                                    " [" + response.body().getCode() + "]", Toast.LENGTH_SHORT).show();
                            endLoadingWithError();
                            return;
                        }

                        endLoadingWithSuccess();

                        if (response.body().getData().size() > 0) {
                            setContent(response.body());
                        } else {
                            AppHelper.showToast(getActivity().getApplicationContext(), "Data tidak ada");
                        }
                    } else {
                        endLoadingWithError();
                    }
                } else {
                    AppHelper.showToast(getActivity().getApplicationContext(), "Request failed");
                    endLoadingWithError();
                }
            }

            @Override
            public void onFailure(Call<ResponseNilai> call, Throwable throwable) {
                AppHelper.showToast(getActivity().getApplication(), throwable.toString());
                Log.e(getTag(), throwable.toString(), throwable);
                endLoadingWithError();
            }
        });
    }

    private void startLoading() {
        view_loading.setVisibility(View.VISIBLE);
        prg_loading.setVisibility(View.VISIBLE);
        txvw_errorMessage.setVisibility(View.GONE);
    }

    private void endLoadingWithSuccess() {
        view_loading.setVisibility(View.GONE);
        prg_loading.setVisibility(View.GONE);
        txvw_errorMessage.setVisibility(View.GONE);
    }

    private void endLoadingWithError() {
        view_loading.setVisibility(View.VISIBLE);
        prg_loading.setVisibility(View.GONE);
        txvw_errorMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSksClicked(View v, ResponseNilai.DataSks data) {

    }
}
