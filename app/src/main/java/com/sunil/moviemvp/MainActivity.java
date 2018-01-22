package com.sunil.moviemvp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.sunil.moviemvp.remote.model.MovieEntity;
import com.sunil.moviemvp.ui.PopularMovieFragment;
import com.sunil.moviemvp.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunil on 21-01-2018.
 */

public class MainActivity extends AppCompatActivity implements PopularMovieFragment.FragmentCommunicator {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.main_grid_container)
    FrameLayout mainGridContainer;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.search_view)
    MaterialSearchView searchView;
    @BindView(R.id.toolbar_container)
    FrameLayout toolbarContainer;
    private String MAIN_FRAGMENT_TAG = "MFTAG";
    private String currentMenuSelected;
    private List<MovieEntity> movieEntities = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);

        if (navView != null) {
            setupDrawerContent(navView);
        }

        if (savedInstanceState != null) {
            return;
        } else {
            // Add the Main Fragment to the 'main_grid_container' FrameLayout
            navView.getMenu().getItem(0).setChecked(true);
            currentMenuSelected = Constant.POPULAR;
            callFragment(Constant.POPULAR);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }


    private void callFragment(String movieType) {
        addFragmentToActivity(getFragmentManager(), PopularMovieFragment.newInstance(movieType), R.id.main_grid_container, MAIN_FRAGMENT_TAG);
    }

    public void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                      @NonNull Fragment fragment, int frameId, String fragmentTag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment, fragmentTag);
        transaction.commit();
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        if (menuItem.getItemId() == R.id.nav_popular) {
                            if (!currentMenuSelected.equalsIgnoreCase(Constant.POPULAR)) {
                                currentMenuSelected = Constant.POPULAR;
                                callFragment(Constant.POPULAR);
                            }
                        } else if (menuItem.getItemId() == R.id.nav_toprated) {
                            if (!currentMenuSelected.equalsIgnoreCase(Constant.TOPRATED)) {
                                currentMenuSelected = Constant.TOPRATED;
                                callFragment(Constant.TOPRATED);
                            }
                            else if (menuItem.getItemId() == R.id.nav_search){
                                Toast.makeText(MainActivity.this, "Work Progress", Toast.LENGTH_LONG).show();
                            }

                        }
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        } else if (id == R.id.action_search) {
            searchViewInit();
        }

        return super.onOptionsItemSelected(item);
    }

    private void searchViewInit() {
        searchView.setVoiceSearch(false);
        searchView.setCursorDrawable(R.drawable.custom_cursor);
        searchView.setEllipsize(true);
        searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Snackbar.make(toolbarContainer, "Query: " + query, Snackbar.LENGTH_LONG)
                        .show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });
    }

    @Override
    public void passDataToFragment(List<MovieEntity> movieEntities) {
        this.movieEntities = movieEntities;
    }
}
