package com.bagasnasution.lecturesapp.scope.matkul;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bagasnasution.lecturesapp.R;
import com.bagasnasution.lecturesapp.app.model.response.ResponseMatkul;

import java.util.List;

/**
 * Created by bagas on 5/27/2018.
 */

public class MataKuliahAdapter extends ArrayAdapter<ResponseMatkul.MatKul> {

    private static final int RESOURCE_LAYOUT = R.layout.row_mata_kuliah;

    public MataKuliahAdapter(@NonNull Context context, @NonNull List<ResponseMatkul.MatKul> objects) {
        super(context, RESOURCE_LAYOUT, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = LayoutInflater.from(getContext())
                .inflate(RESOURCE_LAYOUT, parent, false);

        ResponseMatkul.MatKul data = getItem(position);

        ((TextView) v.findViewById(R.id.text_kode))
                .setText(data.getKode());
        ((TextView) v.findViewById(R.id.text_name))
                .setText(data.getNama());
        ((TextView) v.findViewById(R.id.text_sks))
                .setText(data.getSks());
        ((TextView) v.findViewById(R.id.text_semester))
                .setText(data.getSemester());

        return v;
    }
}
