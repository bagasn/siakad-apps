package com.bagasnasution.lecturesapp.scope.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bagasnasution.lecturesapp.R;
import com.bagasnasution.lecturesapp.app.model.SlideHomeModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bagas on 5/7/2018.
 */

public class PagerHomeAdapter extends FragmentPagerAdapter {

    private List<PagerFragment> fragments = new ArrayList<>();

    public PagerHomeAdapter(FragmentManager fm, List<SlideHomeModel> listSlideHome) {
        super(fm);

        for (SlideHomeModel content : listSlideHome) {
            PagerFragment fragment = new PagerFragment();

            Bundle bundle = new Bundle();
            bundle.putString("link", content.getLink());

            fragment.setArguments(bundle);

            fragments.add(fragment);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public static class PagerFragment extends Fragment {
        public static final String TAG = "PagerFragment";

        private String mLink = "";

        private ImageView imv_content;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            try {
                mLink = getArguments().getString("link");
            } catch (Exception e) {
                Log.e(TAG, "onCreate: ", e);
            }
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.layout_slide_image, container, false);

            imv_content = (ImageView) view.findViewById(R.id.imv_content);

            Glide.with(getContext())
                    .load(mLink)
                    .into(imv_content);

            return view;
        }

        @Override
        public void onResume() {
            super.onResume();
        }
    }
}
