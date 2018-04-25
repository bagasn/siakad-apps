package com.bagasnasution.lecturesapp.app.engine;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.support.annotation.IdRes;
import android.app.FragmentManager;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.bagasnasution.lecturesapp.HomeFragment;
import com.bagasnasution.lecturesapp.LoginActivity;
import com.bagasnasution.lecturesapp.R;
import com.bagasnasution.lecturesapp.app.db.DBHandler;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Created by Bagas on 24/08/2017.
 */

public abstract class AppActivity extends AppCompatActivity {

    private static final String TAG = "AppActivity";

    protected void addFragment(@IdRes int resId, Fragment fragment, @Nullable @StringRes int tag) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(resId, new HomeFragment(), tag == 0 ? null : getString(tag));
        fragmentTransaction.commit();
    }

    protected void replaceFragmentTo(@IdRes int layoutRes, AppFragment fragment) {
        replaceFragmentTo(layoutRes, fragment, 0, 0, false);

    }

    protected void replaceFragmentTo(@IdRes int layoutRes, AppFragment fragment, @StringRes int toBackStack) {
        replaceFragmentTo(layoutRes, fragment, toBackStack, 0, false);

    }

    protected void replaceFragmentTo(@IdRes int layoutRes, AppFragment fragment, @StringRes int toBackStack,
                                     @StringRes int nameTag) {
        replaceFragmentTo(layoutRes, fragment, toBackStack, nameTag, false);

    }

    protected void replaceFragmentTo(@IdRes int layoutRes, AppFragment fragment, @StringRes int nameTag,
                                     @StringRes int toBackStack, boolean animation) {

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

    protected String toMD5(String plaintext) {
        String temp = "";
        try {
            MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(plaintext.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            temp = sb.toString();
        } catch (Exception | Error e) {
            Log.e("AppActivity", "--md5-->> " + Log.getStackTraceString(e));
        }
        return temp;
    }

    protected void onLogout() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Are you sure want to logout from this app?");
        alert.setNegativeButton("Cancel", null);
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                performLogout();
            }
        });
        alert.show();
    }

    protected void performLogout() {
        try {
            deleteAllRow();
        } catch (Exception e) {
            Log.e(TAG, "performLogout: ", e);
        }
    }

    private void deleteAllRow() {
        DBHandler handler = new DBHandler(this);
        int delId = 0;
        try {
            delId = handler.deleteDataUser();
        } catch (SQLiteException e) {
            Log.e("Logout", "---Logout Failed--->>> " + e.toString());
            Toast.makeText(this, "Error when delete data user", Toast.LENGTH_LONG).show();
        } finally {
            Log.e("Logout", "---Logout Success--->>> RowDelete: " + delId);
            try {
                AppHelper helper = new AppHelper().getInstance(this);
                helper.removeLoginInitiate();

                finish();
            } catch (Exception e) {
                Log.e("SharedPreferences", "--Error-->> " + e.toString());
            } finally {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        }
    }

}
