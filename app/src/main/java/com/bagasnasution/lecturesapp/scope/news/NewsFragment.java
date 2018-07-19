package com.bagasnasution.lecturesapp.scope.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.bagasnasution.lecturesapp.R;
import com.bagasnasution.lecturesapp.app.connect.ConnectRetrofit;
import com.bagasnasution.lecturesapp.app.engine.AppFragment;
import com.bagasnasution.lecturesapp.app.engine.AppHelper;
import com.bagasnasution.lecturesapp.app.model.response.ResponseNews;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class NewsFragment extends AppFragment implements ListNewsAdapter.OnNewsItemClickListener {

    private ListView list_content;
    private SwipeRefreshLayout swipe_refresh;

    private List<ResponseNews.News> LIST_DATA = new ArrayList<>();
    private ListNewsAdapter listAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        list_content = (ListView) view.findViewById(R.id.list_content);

        listAdapter = new ListNewsAdapter(getContext(), LIST_DATA);
        listAdapter.setOnNewsClickListener(this);
        list_content.setAdapter(listAdapter);


        swipe_refresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestNews();
            }
        });

        requestNews();

        return view;
    }

    @Override
    public void onNewsClicked(ResponseNews.News data) {
        if (data != null) {
            Intent intent = new Intent(getContext(), NewsDetailActivity.class);
            intent.putExtra("data", data);
            startActivity(intent);
        }
    }

    private void requestNews() {

        ConnectRetrofit.getNews(new ConnectRetrofit.OnResponse<ResponseNews>() {
            @Override
            public void onResponse(Call<ResponseNews> call, Response<ResponseNews> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (!response.body().getCode().equals("0000")) {
                            Toast.makeText(getActivity().getApplicationContext(), response.body().getMessage() +
                                    " [" + response.body().getCode() + "]", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        setDataToList(response.body().getNewsList());
                    }
                }

                if (swipe_refresh.isRefreshing()) {
                    swipe_refresh.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<ResponseNews> call, Throwable throwable) {
                AppHelper.showToast(getActivity().getApplication(), throwable.toString());
                Log.e(getTag(), throwable.toString(), throwable);

                if (swipe_refresh.isRefreshing()) {
                    swipe_refresh.setRefreshing(false);
                }
            }
        });

    }

    private void setDataToList(List<ResponseNews.News> datum) {
        if (datum != null) {
            if (datum.size() > 0) {

                LIST_DATA.clear();

                for (ResponseNews.News val : datum) {
                    LIST_DATA.add(val);
                }

                listAdapter.notifyDataSetChanged();

                return;
            }
        }

        AppHelper.showToast(getActivity().getApplicationContext(), "Data tidak ada");
    }

}
