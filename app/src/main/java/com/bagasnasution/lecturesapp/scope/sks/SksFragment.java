package com.bagasnasution.lecturesapp.scope.sks;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bagasnasution.lecturesapp.R;
import com.bagasnasution.lecturesapp.app.engine.AppFragment;

/**
 * Created by Bagas on 07/10/2017.
 */

public class SksFragment extends AppFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sks, container, false);
        return view;
    }
}
