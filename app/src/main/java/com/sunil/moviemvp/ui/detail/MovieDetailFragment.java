package com.sunil.moviemvp.ui.detail;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunil.moviemvp.R;
import com.sunil.moviemvp.remote.model.MovieEntity;
import com.sunil.moviemvp.utils.Constant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunil on 21-01-2018.
 */

public class MovieDetailFragment extends Fragment {

    public static final String TAG = MovieDetailFragment.class.getSimpleName();

    @BindView(R.id.coordinate_layout)
    CoordinatorLayout mCoordinatorLayout;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolBar;
    @BindView(R.id.iv_backdrop)
    ImageView mIvBackDrop;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_release)
    TextView mTvReleaseDate;
    @BindView(R.id.tv_rating)
    TextView mTvRating;
    @BindView(R.id.tv_movie_overview)
    TextView mTvOverview;
    @BindView(R.id.iv_poster)
    ImageView mIvPoster;
    @BindView(R.id.fab_favorite)
    FloatingActionButton mButtonFavorite;
    @BindColor(R.color.colorPrimaryDark)
    int primaryDark;
    @BindView(R.id.fab_trailer)
    FloatingActionButton mButtonTrailer;
    @BindView(R.id.fab_share)
    FloatingActionButton mButtonShare;
    @BindView(R.id.review_layout0)
    CardView mReviewLayout0;
    @BindView(R.id.review_layout1)
    CardView mReviewLayout1;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private MovieEntity mMovie;


    public static MovieDetailFragment newInstance(Bundle args) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public MovieDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            Bundle arg = getArguments();
            mMovie = arg.getParcelable(Constant.ARG_MOVIE_DETAIL);

            Log.d(TAG, "Received movie from getArguments() :  " + mMovie.toString());
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        ButterKnife.bind(this, view);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mCollapsingToolBar.setTitle(mMovie.getOriginalTitle());
        mCollapsingToolBar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        mTvTitle.setText(mMovie.getOriginalTitle());

        String sourceDateStr = mMovie.getReleaseDate();
        SimpleDateFormat sourceDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        Date sourceDate = null;
        try {
            sourceDate = sourceDateFormat.parse(sourceDateStr);
        } catch (ParseException e) {
            Log.e(TAG, e.getMessage());
        }

        SimpleDateFormat finalDateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
        String finalDateStr = finalDateFormat.format(sourceDate);

        mTvReleaseDate.setText(finalDateStr);
        mTvRating.setText(String.valueOf(mMovie.getVoteAverage()));
        mTvOverview.setText(mMovie.getOverview());

        mIvPoster.setImageURI(Uri.parse(Constant.BASE_URL_IMAGE_POSTER + mMovie.getPosterPath()));
        mIvBackDrop.setImageURI(Uri.parse(Constant.BASE_URL_IMAGE_BACKDROP + mMovie.getBackdropPath()));

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
