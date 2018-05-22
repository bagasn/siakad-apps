package com.bagasnasution.lecturesapp.app.engine;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;

import com.bagasnasution.lecturesapp.R;

public abstract class AppFragment extends Fragment {

    protected void swipeDefaultColor(SwipeRefreshLayout swipe) {
        swipe.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorPrimaryDark,
                R.color.primary_light
        );
    }

}
