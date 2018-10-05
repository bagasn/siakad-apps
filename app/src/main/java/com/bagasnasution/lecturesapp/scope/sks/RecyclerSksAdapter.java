package com.bagasnasution.lecturesapp.scope.sks;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bagasnasution.lecturesapp.R;
import com.bagasnasution.lecturesapp.app.model.ResponseSks;

import java.util.List;

/**
 * Created by bagas on 1/24/2018.
 */

public class RecyclerSksAdapter extends RecyclerView.Adapter<RecyclerSksAdapter.SksHolder> {
    private Context context;
    private List<ResponseSks.DataSks> data_sks;
    private OnSksClickListener listener = null;

    private static final int RESOURCE_LAYOUT = R.layout.row_sks_item;

    public RecyclerSksAdapter(Context context, List<ResponseSks.DataSks> objects) {
        this.context = context;
        this.data_sks = objects;
    }

    @Override
    public SksHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(RESOURCE_LAYOUT, viewGroup, false);
        return new SksHolder(v);
    }

    @Override
    public void onBindViewHolder(SksHolder sksHolder, int i) {

        final ResponseSks.DataSks data = data_sks.get(i);

        try {
            sksHolder.txvw_title.setText(data.getNamaMatkul());
            sksHolder.txvw_smt.setText(data.getSemester());
            sksHolder.txvw_nilai.setText(data.getNilaiAkdm());
            sksHolder.txvw_sks.setText(data.getSks());
        } catch (NullPointerException e) {
            Log.e(getClass().getSimpleName(), e.toString(), e);
        }

        if (listener != null) {
            sksHolder.container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onSksClicked(view, data);
                }
            });
        }
    }

    public void setOnSksClickListener(OnSksClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return data_sks.size();
    }

    class SksHolder extends RecyclerView.ViewHolder {
        private TextView txvw_title;
        private TextView txvw_smt;
        private TextView txvw_sks;
        private TextView txvw_nilai;

        private View container;

        public SksHolder(View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.container);

            txvw_title = itemView.findViewById(R.id.txvw_title);
            txvw_smt = itemView.findViewById(R.id.txvw_smt);
            txvw_sks = itemView.findViewById(R.id.txvw_sks);
            txvw_nilai = itemView.findViewById(R.id.txvw_nilai);
        }
    }

    public interface OnSksClickListener {
        void onSksClicked(View v, ResponseSks.DataSks data);
    }

}
