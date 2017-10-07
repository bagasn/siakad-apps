package com.bagasnasution.lecturesapp.app.engine;

import android.app.FragmentTransaction;
import android.support.annotation.IdRes;
import android.app.FragmentManager;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Bagas on 24/08/2017.
 */

public abstract class AppActivity extends AppCompatActivity {



    /*
    * Code To Replace Framgent
    *
    * */

    protected void replaceFragmentTo(@IdRes int layoutRes, AppFragment fragment){
        replaceFragmentTo(layoutRes, fragment, 0, 0, false);

    }

    protected void replaceFragmentTo(@IdRes int layoutRes, AppFragment fragment, @StringRes int toBackStack){
        replaceFragmentTo(layoutRes, fragment, toBackStack, 0, false);

    }

    protected void replaceFragmentTo(@IdRes int layoutRes, AppFragment fragment, @StringRes int toBackStack,
                                     @StringRes int nameTag){
        replaceFragmentTo(layoutRes, fragment, toBackStack, nameTag, false);

    }

    protected void replaceFragmentTo(@IdRes int layoutRes, AppFragment fragment, @StringRes int nameTag,
                                     @StringRes int toBackStack , boolean animation) {

        FragmentManager manager = getFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();

        if (toBackStack != 0)
            transaction.addToBackStack(getResources().getString(toBackStack));
        else
            transaction.addToBackStack(null);

        if (animation)
            transaction.setCustomAnimations(FragmentTransaction.TRANSIT_FRAGMENT_OPEN, FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);

        if (nameTag != 0)
            transaction.replace(layoutRes, fragment, getResources().getString(nameTag));
        else
            transaction.replace(layoutRes, fragment);

        transaction.commit();
    }

}
