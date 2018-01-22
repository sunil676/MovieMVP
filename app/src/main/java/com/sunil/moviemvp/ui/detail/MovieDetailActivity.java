package com.sunil.moviemvp.ui.detail;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.sunil.moviemvp.R;
import com.sunil.moviemvp.remote.model.MovieEntity;
import com.sunil.moviemvp.utils.Constant;

/**
 * Created by sunil on 21-01-2018.
 */

public class MovieDetailActivity extends AppCompatActivity {

    MovieEntity movieEntity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Bundle bundle = getIntent().getExtras();
        if (bundle!= null){
            movieEntity =  bundle.getParcelable(Constant.ARG_MOVIE_DETAIL);
        }

        if (savedInstanceState == null){
            Bundle bundleFrag = new Bundle();
            bundleFrag.putParcelable(Constant.ARG_MOVIE_DETAIL, movieEntity);
            addDetailFragment(bundleFrag);
        }
    }

    public void addDetailFragment(Bundle bundle) {
        MovieDetailFragment detailFragment = MovieDetailFragment.newInstance(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.main_container, detailFragment, MovieDetailFragment.TAG)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==android.R.id.home){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
