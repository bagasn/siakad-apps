package com.bagasnasution.lecturesapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bagasnasution.lecturesapp.app.engine.AppFragment;

/**
 * Created by Bagas on 23/08/2017.
 */

public class HomeFragment extends AppFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }
}
