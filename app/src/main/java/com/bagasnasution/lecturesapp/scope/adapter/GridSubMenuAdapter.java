package com.bagasnasution.lecturesapp.scope.adapter;

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
import com.bagasnasution.lecturesapp.app.model.SubMenuModel;

import java.util.List;

public class GridSubMenuAdapter extends ArrayAdapter<SubMenuModel> {

    public GridSubMenuAdapter(@NonNull Context context) {
        super(context, R.layout.row_sub_menu, SubMenuModel.getInstance());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.row_sub_menu, parent, false);

        SubMenuModel item = getItem(position);

        if (item != null) {
            ((ImageView) view.findViewById(R.id.image_icon))
                    .setImageResource(item.getIcon());
            ((TextView) view.findViewById(R.id.text_content))
                    .setText(item.getTitle());
        }

        return view;
    }
}
