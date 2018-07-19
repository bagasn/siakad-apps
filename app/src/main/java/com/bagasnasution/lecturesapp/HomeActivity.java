package com.bagasnasution.lecturesapp;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bagasnasution.lecturesapp.app.db.DBUser;
import com.bagasnasution.lecturesapp.app.engine.AppActivity;
import com.bagasnasution.lecturesapp.app.engine.AppFragment;
import com.bagasnasution.lecturesapp.app.model.SubMenuModel;
import com.bagasnasution.lecturesapp.scope.jadwal.ListJadwalFragment;
import com.bagasnasution.lecturesapp.scope.matkul.MatkulMainFragment;
import com.bagasnasution.lecturesapp.scope.news.NewsFragment;
import com.bagasnasution.lecturesapp.scope.nilai.NilaiFragment;

public class HomeActivity extends AppActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        HomeFragment.OnSubMenuClickFromHomeListener {

    private static final int RESOURCE_FRAGMENT = R.id.fragment;
    private FragmentTransaction fragmentTransaction;

    private NavigationView mNavigationView;
    private DrawerLayout mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        setContentNavigationHeader(mNavigationView);

        TextView textVersionName = (TextView) findViewById(R.id.txvw_versionName);
        textVersionName.setText("Version " + BuildConfig.VERSION_NAME);

        // Fragment Initiate
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setOnSubMenuClickListener(this);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(RESOURCE_FRAGMENT, homeFragment, null);
//        fragmentTransaction.add(RESOURCE_FRAGMENT, new HomeFragment(), getResources().getString(R.string.fragment_home));
        fragmentTransaction.commit();
    }

    private void setContentNavigationHeader(NavigationView rootView) {
        View header = rootView.getHeaderView(0);

        DBUser.User user = DBUser.getDataUser(this);

        String fullname = user.getNama();
        String[] names = fullname.split(" ");

        if (names.length > 2) {
            fullname = names[0] + " " + names[1];
            for (int i = 2; i < names.length; i++) {
                fullname += " " + names[i].toUpperCase().charAt(0);
            }
        }

        ((TextView) header.findViewById(R.id.text_namaUser))
                .setText(fullname);
        ((TextView) header.findViewById(R.id.text_emailUser))
                .setText(user.getEmail());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
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
                ((HomeFragment) fragment).setOnSubMenuClickListener(this);
                break;
            case R.id.nav_matkul:
                fragment = new MatkulMainFragment();
                break;
            case R.id.nav_jadwal:
                fragment = new ListJadwalFragment();
                break;
            case R.id.nav_nilai:
                fragment = new NilaiFragment();
                break;
            case R.id.nav_news:
                fragment = new NewsFragment();
                break;
            case R.id.nav_krs:
                Toast.makeText(this, "not available now!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout:
                onLogout();
                return true;
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

    @Override
    public void onSubMenuDiklik(int id) {
        @IdRes int idres = 0;
        switch (id) {
            case SubMenuModel.MENU_JADWAL:
                idres = R.id.nav_jadwal;
                break;
            case SubMenuModel.MENU_MATAKULIAH:
                idres = R.id.nav_matkul;
                break;
            case SubMenuModel.MENU_TRANSKRIP:
                idres = R.id.nav_nilai;
                break;
            case SubMenuModel.MENU_NEWS:
                idres = R.id.nav_news;
                break;
        }

        if (idres != 0) {
            MenuItem item = mNavigationView.getMenu().findItem(idres);
            boolean selected = onNavigationItemSelected(item);
            item.setChecked(selected);
        }
    }
}
