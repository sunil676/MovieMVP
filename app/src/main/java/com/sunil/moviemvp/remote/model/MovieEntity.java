package com.sunil.moviemvp.remote.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.sunil.moviemvp.utils.Constant;

/**
 * Created by sunil on 20-01-2018.
 */

@Entity(tableName = "MovieEntity")
public class MovieEntity implements Parcelable{

    @SerializedName("poster_path")
    private String posterPath;
    private boolean adult;
    private String overview;
    @SerializedName("release_date")
    private String releaseDate;
    @PrimaryKey
    private int id;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("original_language")
    private String originalLanguage;
    private String title;
    @SerializedName("backdrop_path")
    private String backdropPath;
    private double popularity;
    @SerializedName("vote_count")
    private int voteCount;
    private boolean video;
    @SerializedName("vote_average")
    private double voteAverage;

    public MovieEntity(int id, String title, String posterPath, String overview, double voteAverage, String releaseDate, String backdropPath) {
        this.id = id;
        this.title = title;
        this.posterPath = posterPath;
        this.overview = overview;
        this.voteAverage = voteAverage;
        this.releaseDate = releaseDate;
        this.backdropPath = backdropPath;

//        this.adult = adult;
//        this.originalTitle = originalTitle;
//        this.originalLanguage = originalLanguage;
//        this.popularity = popularity;
//        this.voteCount = voteCount;
//        this.video = video;

    }

    public MovieEntity(Parcel source) {
        this.posterPath = source.readString();
        this.adult = source.readByte() != 0;
        this.overview = source.readString();
        this.releaseDate = source.readString();
        this.id = source.readInt();
        this.originalTitle = source.readString();
        this.originalLanguage = source.readString();
        this.title = source.readString();
        this.backdropPath = source.readString();
        this.popularity = source.readDouble();
        this.voteCount = source.readInt();
        this.video = source.readByte() != 0;
        this.voteAverage = source.readDouble();
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getPosterPath() {
        return  posterPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getId() {
        return id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getTitle() {
        return title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public double getPopularity() {
        return popularity;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public boolean isVideo() {
        return video;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public static final Parcelable.Creator<MovieEntity> CREATOR = new Parcelable.Creator<MovieEntity>() {
        public MovieEntity createFromParcel(Parcel source) {
            MovieEntity movie = new MovieEntity(source);
            return movie;
        }

        public MovieEntity[] newArray(int size) {
            return new MovieEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(posterPath);
        dest.writeByte((byte) (adult ? 1 : 0));
        dest.writeString(overview);
        dest.writeString(releaseDate);
        dest.writeInt(id);
        dest.writeString(originalTitle);
        dest.writeString(originalLanguage);
        dest.writeString(title);
        dest.writeString(backdropPath);
        dest.writeDouble(popularity);
        dest.writeInt(voteCount);
        dest.writeByte((byte) (video ? 1 : 0));
        dest.writeDouble(voteAverage);
    }


    @Override
    public String toString() {
        return "MovieEntity {posterPath: " + posterPath + ", adult: " + adult
                + ", overview: " + overview + ", releaseDate: " + releaseDate + ", id: "
                + id + ", originalTitle: " + originalTitle + ", originalLanguage: "
                + originalLanguage + ", title: " + title + ", backdropPath: " + backdropPath
                + ", popularity: " + popularity + ", voteCount: "
                + voteCount + ", video: " + video
                + ", voteAverage: " + voteAverage + "}";
    }
}
