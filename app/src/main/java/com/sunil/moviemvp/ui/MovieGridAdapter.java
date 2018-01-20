package com.sunil.moviemvp.ui;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.sunil.moviemvp.R;
import com.sunil.moviemvp.remote.model.MovieEntity;
import com.sunil.moviemvp.utils.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunil on 20-01-2018.
 */

public class MovieGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static Context mContext;
    private static List<MovieEntity> mDatasetList;

    // Provide a suitable constructor (depends on the kind of dataset)
    public MovieGridAdapter(Context context, List<MovieEntity> datasetList) {
        mContext = context;
        mDatasetList = datasetList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_row, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        MovieViewHolder movieHolder = (MovieViewHolder) holder;
        movieHolder.mTVTitle.setText(mDatasetList.get(position).getTitle());
        movieHolder.mTVRating.setText(String.valueOf(mDatasetList.get(position).getVoteAverage()));
        String completePosterPath = Constant.BASE_URL_IMAGE_POSTER + mDatasetList.get(position).getPosterPath();
        Log.v("Adapter", "URL: "+completePosterPath);
        movieHolder.mIVThumbNail.setImageURI( Uri.parse(completePosterPath));
        movieHolder.mIVThumbNail.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return mDatasetList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView mTVTitle;
        @BindView(R.id.card_view)
        CardView mCardView;
        @BindView(R.id.iv_thumbnail)
        SimpleDraweeView mIVThumbNail;
        @BindView(R.id.tv_rating)
        TextView mTVRating;

        public MovieViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}
