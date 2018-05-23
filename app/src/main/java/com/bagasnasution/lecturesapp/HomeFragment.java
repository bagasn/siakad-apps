package com.bagasnasution.lecturesapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bagasnasution.lecturesapp.app.config.Config;
import com.bagasnasution.lecturesapp.app.connect.ConnectRetrofit;
import com.bagasnasution.lecturesapp.app.db.DBUser;
import com.bagasnasution.lecturesapp.app.engine.AppFragment;
import com.bagasnasution.lecturesapp.app.engine.AppHelper;
import com.bagasnasution.lecturesapp.app.model.response.GetSlideHomeResponse;
import com.bagasnasution.lecturesapp.app.model.response.ResponseCurrentJadwal;
import com.bagasnasution.lecturesapp.scope.adapter.PagerHomeAdapter;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Bagas on 23/08/2017.
 */

public class HomeFragment extends AppFragment {
    private static final String TAG = "HomeFragment";

    private TextView text_hello;
    private ViewPager pager_slideImage;
    private CircleIndicator pager_indicator;

    private PagerHomeAdapter adapter_pagerHome = null;

    private DBUser.User mDataUser = null;

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mThreadPager.start();
//    }

    // For Jadwal Hari Ini
    private ProgressBar prg_loading_jadwal;
    private TextView text_no_data_jadwal;
    private LinearLayout view_contentJadwal;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        try {
            mDataUser = DBUser.getDataUser(getActivity());
        } catch (Exception e) {
            Log.e(TAG, "onCreateView: ", e);
        }

        text_hello = (TextView) view.findViewById(R.id.text_hello);
        pager_slideImage = (ViewPager) view.findViewById(R.id.pager_slide);
        pager_indicator = (CircleIndicator) view.findViewById(R.id.pager_indicator);

        view_contentJadwal = (LinearLayout) view.findViewById(R.id.view_contentJadwal);
        prg_loading_jadwal = (ProgressBar) view.findViewById(R.id.prg_loading_jadwal);
        text_no_data_jadwal = (TextView) view.findViewById(R.id.text_no_data_jadwal);

        setcontentValue();

        return view;
    }

    private void setcontentValue() {
        if (mDataUser != null) {

            text_hello.setText("Hai, " + AppHelper.getFirstName(mDataUser.getNama()) + "!");

            getSlideShow();

            getCurrentJadwal();
        }
    }

    private void getCurrentJadwal() {

        ConnectRetrofit.getCurrentJadwal(getContext(), new ConnectRetrofit.OnResponse<ResponseCurrentJadwal>() {
            @Override
            public void onResponse(Call<ResponseCurrentJadwal> call, Response<ResponseCurrentJadwal> response) {
                boolean isDataValid = false;

                if (response.isSuccessful()) {
                    if (response.body().getCode().equals(Config.API_CODE_SUCCESS)) {
                        if (response.body() != null) {
                            if (response.body().getListMatkul() != null) {
                                if (response.body().getListMatkul().size() > 0) {
                                    for (int i = 0; i < response.body().getListMatkul().size(); i++) {
                                        View viewChild = getLayoutInflater().inflate(R.layout.row_home_current_jadwal, view_contentJadwal, false);

                                        ((TextView) viewChild.findViewById(R.id.text_jadwal_matkul))
                                                .setText(response.body().getListMatkul().get(i).getMatkul());
                                        ((TextView) viewChild.findViewById(R.id.text_jadwal_waktu))
                                                .setText(response.body().getListMatkul().get(i).getWaktu());
                                        ((TextView) viewChild.findViewById(R.id.text_jadwal_kelas))
                                                .setText(response.body().getListMatkul().get(i).getKelas());
                                        ((TextView) viewChild.findViewById(R.id.text_jadwal_dosen))
                                                .setText(response.body().getListMatkul().get(i).getDosen());

                                        if (i == (response.body().getListMatkul().size() - 1)) {
                                            (viewChild.findViewById(R.id.divider)).setVisibility(View.GONE);
                                        }

                                        view_contentJadwal.addView(viewChild);
                                    }

                                    isDataValid = true;
                                }
                            }
                        }
                    }
                }

                if (!isDataValid) {
                    text_no_data_jadwal.setVisibility(View.VISIBLE);
                }

                prg_loading_jadwal.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResponseCurrentJadwal> call, Throwable throwable) {
                AppHelper.showToast(getContext(), throwable.getMessage());

                prg_loading_jadwal.setVisibility(View.GONE);

                text_no_data_jadwal.setText("Connection Problem");
            }
        });

    }

    private void getSlideShow() {

        ConnectRetrofit.getSlideHome(getActivity(), new ConnectRetrofit.OnResponse<GetSlideHomeResponse>() {
            @Override
            public void onResponse(Call<GetSlideHomeResponse> call, Response<GetSlideHomeResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode().equals(Config.API_CODE_SUCCESS)) {
                        setDataToShow(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<GetSlideHomeResponse> call, Throwable throwable) {
                AppHelper.showToast(getActivity(), throwable.toString());
            }
        });
    }

    private void setDataToShow(GetSlideHomeResponse data) {
        if (data != null) {

            adapter_pagerHome = new PagerHomeAdapter(getFragmentManager(), data.getListSlideHome());

            pager_slideImage.setAdapter(adapter_pagerHome);
            pager_indicator.setViewPager(pager_slideImage);
//
//            if (!mThreadPager.isAlive()) {
//                mThreadPager.start();
//            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mThreadPager.isAlive()) {
            mThreadPager.interrupt();
        }
    }

    private Thread mThreadPager = new Thread(new PagerSlideRunnable());

    private class PagerSlideRunnable implements Runnable {
        @Override
        public void run() {
            try {
                Log.i(TAG, "run: Thread is start");

                Thread.sleep(1000);
                if (pager_slideImage != null) {
                    int count = pager_slideImage.getChildCount();

                    int current = pager_slideImage.getCurrentItem();

                    if (current == (count - 1)) {
                        pager_slideImage.setCurrentItem(0, true);
                    } else {
                        pager_slideImage.setCurrentItem(current + 1, true);
                    }

                }
            } catch (InterruptedException e) {
                Log.e(TAG, "run: ", e);
            } finally {
                run();
            }
        }
    }
}
