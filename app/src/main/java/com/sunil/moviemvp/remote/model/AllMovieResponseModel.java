package com.sunil.moviemvp.remote.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sunil on 20-01-2018.
 */

public class AllMovieResponseModel {

    private int page;
    private ArrayList<MovieEntity> results;
    @SerializedName("total_results")
    private long totalResults;
    @SerializedName("total_pages")
    private long totalPages;

    public int getPage() {
        return page;
    }

    public ArrayList<MovieEntity> getResults() {
        return results;
    }

    public long getTotalResults() {
        return totalResults;
    }

    public long getTotalPages() {
        return totalPages;
    }
}
