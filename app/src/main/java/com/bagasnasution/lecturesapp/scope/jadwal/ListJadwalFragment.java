package com.bagasnasution.lecturesapp.scope.jadwal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bagasnasution.lecturesapp.R;
import com.bagasnasution.lecturesapp.app.engine.AppCustomFragment;

public class ListJadwalFragment extends AppCustomFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
