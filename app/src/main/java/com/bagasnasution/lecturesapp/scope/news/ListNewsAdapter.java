package com.bagasnasution.lecturesapp.scope.news;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bagasnasution.lecturesapp.R;
import com.bagasnasution.lecturesapp.app.model.response.ResponseNews;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListNewsAdapter extends ArrayAdapter<ResponseNews.News> {

    public ListNewsAdapter(@NonNull Context context, @NonNull List<ResponseNews.News> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.row_list_item_news, parent, false);

        final ResponseNews.News item = getItem(position);

        if (item != null) {

            ImageView imageView = (ImageView) view.findViewById(R.id.image_icon);

            Picasso.get()
                    .load(item.getImage())
                    .placeholder(R.drawable.ic_image_empty)
                    .error(R.drawable.ic_image_empty)
                    .into(imageView);

            ((TextView) view.findViewById(R.id.text_title))
                    .setText(item.getJudul());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mNewsClickListener != null) {
                        mNewsClickListener.onNewsClicked(item);
                    }
                }
            });
        }
        return view;
    }


    private OnNewsItemClickListener mNewsClickListener = null;

    public void setOnNewsClickListener(OnNewsItemClickListener listener) {
        mNewsClickListener = listener;
    }

    public interface OnNewsItemClickListener {
        void onNewsClicked(ResponseNews.News data);
    }

}
