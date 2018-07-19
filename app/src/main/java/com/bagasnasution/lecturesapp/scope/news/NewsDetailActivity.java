package com.bagasnasution.lecturesapp.scope.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bagasnasution.lecturesapp.R;
import com.bagasnasution.lecturesapp.app.engine.AppActivity;
import com.bagasnasution.lecturesapp.app.model.response.ResponseNews;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NewsDetailActivity extends AppActivity {

    private static final String TAG = "NewssDetailActivity";

    private ImageView image_logo;
    private TextView text_title;
    private TextView text_waktu;
    private WebView web_content;

    private ResponseNews.News data_view = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        image_logo = (ImageView) findViewById(R.id.image_logo);
        text_title = (TextView) findViewById(R.id.text_title);
        text_waktu = (TextView) findViewById(R.id.text_waktu);
        web_content = (WebView) findViewById(R.id.web_content);

        try {
            data_view = (ResponseNews.News) getIntent().getSerializableExtra("data");
        } catch (Exception e) {
            Log.e(TAG, "onCreate: ", e);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (data_view != null) {
            Picasso.get()
                    .load(data_view.getImage())
                    .placeholder(R.drawable.ic_image_empty)
                    .error(R.drawable.ic_image_empty)
                    .into(image_logo);


            text_title.setText(data_view.getJudul());

            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(data_view.getTanggal());

                String tanggal = new SimpleDateFormat("dd MMMM yyyy").format(date);

                text_waktu.setText(tanggal);
            } catch (Exception e) {

            }

            web_content.loadData(data_view.getIsiNews(), "text/html", "UTF-8");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
