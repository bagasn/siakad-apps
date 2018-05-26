package com.bagasnasution.lecturesapp.scope.matkul;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bagasnasution.lecturesapp.R;
import com.bagasnasution.lecturesapp.app.config.Config;
import com.bagasnasution.lecturesapp.app.connect.ConnectRetrofit;
import com.bagasnasution.lecturesapp.app.engine.AppActivity;
import com.bagasnasution.lecturesapp.app.engine.AppFragment;
import com.bagasnasution.lecturesapp.app.engine.AppHelper;
import com.bagasnasution.lecturesapp.app.model.response.ResponseMatkul;

import retrofit2.Call;
import retrofit2.Response;


public class MatkulMainFragment extends AppFragment {

    private SwipeRefreshLayout swipe_refresh;
    private ListView list_content;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mata_kuliah, container, false);

        swipe_refresh = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh);
        list_content = (ListView) v.findViewById(R.id.list_content);

        swipeDefaultColor(swipe_refresh);
        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestData();
            }
        });

        requestData();

        return v;
    }

    private void requestData() {
        if (!swipe_refresh.isRefreshing()) {
            swipe_refresh.setRefreshing(true);
        }

        ConnectRetrofit.getMatakuliah(getContext(), new ConnectRetrofit.OnResponse<ResponseMatkul>() {
            @Override
            public void onResponse(Call<ResponseMatkul> call, Response<ResponseMatkul> response) {
                boolean isSuccess = false;
                if (response.isSuccessful()) {
                    if (response.body().getCode().equals(Config.API_CODE_SUCCESS)) {
                        setDataToShow(response.body());
                        isSuccess = true;
                    }
                }

                if (!isSuccess) {
                    AppHelper.showToast(getContext(), "Something problem!");
                }

                if (swipe_refresh.isRefreshing()) {
                    swipe_refresh.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<ResponseMatkul> call, Throwable throwable) {
                AppHelper.showToast(getContext(), throwable.toString());

                if (swipe_refresh.isRefreshing()) {
                    swipe_refresh.setRefreshing(false);
                }

            }
        });
    }

    private void setDataToShow(ResponseMatkul data) {
        if (data != null) {
            if (data.getListMatkul() != null) {
                if (data.getListMatkul().size() > 0) {

                    MataKuliahAdapter adapter =
                            new MataKuliahAdapter(getContext(), data.getListMatkul());

                    list_content.setAdapter(adapter);
                    return;
                }
            }
        }

        AppHelper.showToast(getContext(), "Tidak ada data!");
    }

}
