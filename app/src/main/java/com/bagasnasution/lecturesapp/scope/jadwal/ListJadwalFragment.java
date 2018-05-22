package com.bagasnasution.lecturesapp.scope.jadwal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.bagasnasution.lecturesapp.R;
import com.bagasnasution.lecturesapp.app.config.Config;
import com.bagasnasution.lecturesapp.app.connect.ConnectRetrofit;
import com.bagasnasution.lecturesapp.app.engine.AppFragment;
import com.bagasnasution.lecturesapp.app.engine.AppHelper;
import com.bagasnasution.lecturesapp.app.model.response.ResponseJadwal;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ListJadwalFragment extends AppFragment {

    private SwipeRefreshLayout swipe_refresh;
    private ListView list_content;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jadwal_kuliah, container, false);

        swipe_refresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        list_content = (ListView) view.findViewById(R.id.list_content);

        swipeDefaultColor(swipe_refresh);
        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestData();
            }
        });

        requestData();

        return view;
    }

    private void requestData() {
        if (!swipe_refresh.isRefreshing()) {
            swipe_refresh.setRefreshing(true);
        }

        ConnectRetrofit.getJadwal(getContext(), new ConnectRetrofit.OnResponse<ResponseJadwal>() {
            @Override
            public void onResponse(Call<ResponseJadwal> call, Response<ResponseJadwal> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode().equals(Config.API_CODE_SUCCESS)) {
                        setDataToShow(response.body().getDatum());
                    }
                }

                if (swipe_refresh.isRefreshing()) {
                    swipe_refresh.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<ResponseJadwal> call, Throwable throwable) {
                Toast.makeText(getContext(), throwable.toString(), Toast.LENGTH_SHORT).show();

                if (swipe_refresh.isRefreshing()) {
                    swipe_refresh.setRefreshing(false);
                }
            }
        });
    }

    private void setDataToShow(List<ResponseJadwal.ListJadwal> listData) {
        if (listData != null) {
            if (listData.size() > 0) {

                JadwalAdapter adapter = new JadwalAdapter(getContext(), listData);

                list_content.setAdapter(adapter);
                return;
            }
        }

        AppHelper.showToast(getContext(), "Tidak ada data!");
    }

}
