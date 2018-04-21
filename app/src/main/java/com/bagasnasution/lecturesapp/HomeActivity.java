package com.bagasnasution.lecturesapp;

import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.bagasnasution.lecturesapp.app.db.DBHandler;
import com.bagasnasution.lecturesapp.app.engine.AppActivity;
import com.bagasnasution.lecturesapp.app.engine.AppFragment;
import com.bagasnasution.lecturesapp.app.engine.AppHelper;
import com.bagasnasution.lecturesapp.scope.jadwal.ListJadwalFragment;
import com.bagasnasution.lecturesapp.scope.sks.SksFragment;

public class HomeActivity extends AppActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int RESOURCE_FRAGMENT = R.id.fragment;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TextView textVersionName = (TextView) findViewById(R.id.txvw_versionName);
        textVersionName.setText("Version " + BuildConfig.VERSION_NAME);

        // Fragment Initiate
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(RESOURCE_FRAGMENT, new HomeFragment(), null);
//        fragmentTransaction.add(RESOURCE_FRAGMENT, new HomeFragment(), getResources().getString(R.string.fragment_home));
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Want to exit?");
            alert.setNegativeButton("Cancel", null);
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.itmn_about) {
            String versionName = BuildConfig.VERSION_NAME;
            Toast.makeText(this, "Version " + versionName, Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateSupportNavigateUpTaskStack(@NonNull TaskStackBuilder builder) {
        super.onCreateSupportNavigateUpTaskStack(builder);
    }

    @Override
    public void onCreateNavigateUpTaskStack(android.app.TaskStackBuilder builder) {
        super.onCreateNavigateUpTaskStack(builder);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        if (item.isChecked()) return false;

        AppFragment fragment = null;

        switch (item.getItemId()) {
            case R.id.nav_home:
                fragment = new HomeFragment();
                break;
            case R.id.nav_matkul:
                Toast.makeText(this, "matkul", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_jadwal:
                fragment = new ListJadwalFragment();
                break;
            case R.id.nav_nilai:
                fragment = new SksFragment();
                break;
            case R.id.nav_krs:
                Toast.makeText(this, "Bayaran", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout:
                onLogout();
                return false;
        }

        // Handle navigation view item clicks here.
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        if (fragment != null) {
            replaceFragmentTo(RESOURCE_FRAGMENT, fragment);
            return true;
        }

        return false;
    }

    private void onLogout() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Are you sure want to logout from this app?");
        alert.setNegativeButton("Cancel", null);
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteAllRow();
            }
        });
        alert.show();
    }

    private void deleteAllRow() {
        DBHandler handler = new DBHandler(this);
        int delId = 0;
        try {
            delId = handler.deleteDataUser();
        }
        catch (SQLiteException e) {
            Log.e("Logout", "---Logout Failed--->>> " + e.toString());
            Toast.makeText(this, "Error when delete data user", Toast.LENGTH_LONG).show();
        }
        finally {
            Log.e("Logout", "---Logout Success--->>> RowDelete: " + delId );
            try {
                AppHelper helper = new AppHelper().getInstance(this);
                helper.removeLoginInitiate();
            }
            catch (Exception e) {
                Log.e("SharedPreferences", "--Error-->> " + e.toString());
            }
            finally {
                finish();
            }
        }
    }




}
