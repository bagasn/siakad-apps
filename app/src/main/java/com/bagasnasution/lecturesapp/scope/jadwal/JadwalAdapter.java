package com.bagasnasution.lecturesapp.scope.jadwal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bagasnasution.lecturesapp.R;
import com.bagasnasution.lecturesapp.app.model.response.ResponseJadwal;

import java.util.List;

public class JadwalAdapter extends ArrayAdapter<ResponseJadwal.ListJadwal> {

    private LayoutInflater mInflater;

    private static final int LAYOUT_RESOURCE = R.layout.row_jadwal_parent;

    public JadwalAdapter(@NonNull Context context, @NonNull List<ResponseJadwal.ListJadwal> objects) {
        super(context, LAYOUT_RESOURCE, objects);

        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View container = mInflater.inflate(R.layout.row_jadwal_parent, parent, false);

        ResponseJadwal.ListJadwal item = getItem(position);

        ((TextView) container.findViewById(R.id.text_title))
                .setText(item.getHari());

        LinearLayout view_content = container.findViewById(R.id.view_data);

//        for (ResponseJadwal.Matkul value : item.getMatkul()) {
//            addChlid(view_content, value, true);
//        }

        for (int i = 0; i < item.getMatkul().size(); i++) {
            if (i == (item.getMatkul().size() - 1)) {
                addChlid(view_content, item.getMatkul().get(i), false);
            } else {
                addChlid(view_content, item.getMatkul().get(i), true);
            }
        }

        return container;
    }

    private void addChlid(LinearLayout viewContent, ResponseJadwal.Matkul item, boolean divider) {
        View child = LayoutInflater.from(getContext()).inflate(R.layout.row_jadwal_child, viewContent, false);

        ((TextView) child.findViewById(R.id.text_jadwal_matkul))
                .setText(item.getMatkul());
        ((TextView) child.findViewById(R.id.text_jadwal_waktu))
                .setText(item.getWaktu());
        ((TextView) child.findViewById(R.id.text_jadwal_ruang))
                .setText(item.getKelas());
        ((TextView) child.findViewById(R.id.text_jadwal_dosen))
                .setText(item.getDosen());

        if (divider) {
            (child.findViewById(R.id.divider)).setVisibility(View.VISIBLE);
        } else {
            (child.findViewById(R.id.divider)).setVisibility(View.GONE);
        }
        viewContent.addView(child);
    }
}
