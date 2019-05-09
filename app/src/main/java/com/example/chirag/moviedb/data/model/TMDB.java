package com.example.chirag.moviedb.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * MovieDB
 * Created by Chirag on 18/10/18.
 */
@Entity(tableName = "movie_info")
public class TMDB implements TMDBInterface {
//
//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "primaryKey")
//    private int primaryKey;
//
//    @SerializedName("backdrop_path")
//    @Expose
//    @Nullable
//    @ColumnInfo(name = "backdrop_path")
//    private String backdropPath;
//
//    @SerializedName("genres")
//    @Expose
//    @Ignore
//    private List<Genre> genres = null;
//
//    @Nullable
//    @ColumnInfo(name = "genre")
//    private String genreInfo;
//
//    @SerializedName("id")
//    @Expose
//    @NonNull
//    @ColumnInfo(name = "id")
//    private Integer id;
//
//    @SerializedName("imdb_id")
//    @Expose
//    @Nullable
//    @ColumnInfo(name = "imdb_id")
//    private String imdbId;
//
//    @SerializedName("original_language")
//    @Expose
//    @Nullable
//    @ColumnInfo(name = "language")
//    private String originalLanguage;
//
//    @SerializedName("original_title")
//    @Expose
//    @Nullable
//    @ColumnInfo(name = "original_title")
//    private String originalTitle;
//
//    @SerializedName("overview")
//    @Expose
//    @Nullable
//    @ColumnInfo(name = "overview")
//    private String overview;
//
//    //    @SerializedName("popularity")
////    @Expose
////    private Double popularity;
//
//    @SerializedName("poster_path")
//    @Expose
//    @Nullable
//    @ColumnInfo(name = "poster_path")
//    private String posterPath;
//
//    @SerializedName("release_date")
//    @Expose
//    @Nullable
//    @ColumnInfo(name = "release_date")
//    private String releaseDate;
//
//    @SerializedName("runtime")
//    @Expose
//    @Nullable
//    @ColumnInfo(name = "runtime")
//    private Integer runtime;
//
//    @SerializedName("vote_average")
//    @Expose
//    @Nullable
//    @ColumnInfo(name = "vote_average")
//    private Double voteAverage;
//
//    @SerializedName("name")
//    @Expose
//    @Nullable
//    @ColumnInfo(name = "name")
//    private String name;
//    @SerializedName("first_air_date")
//    @Expose
//    @Nullable
//    @ColumnInfo(name = "first_air_date")
//    private String firstAirDate;
//
//    @SerializedName("original_name")
//    @Expose
//    @Nullable
//    @ColumnInfo(name = "original_name")
//    private String originalName;
//
//    @SerializedName("seasons")
//    @Expose
//    @Ignore
//    private List<Season> seasons = null;
//
//    @Nullable
//    @ColumnInfo(name = "userEmail")
//    private String userEmail;
//
//    /**
//     * No args constructor for use in serialization
//     */
//    public TMDB() {
//    }
//
//    @Override
//    public String getBackDropPath() {
//        return backdropPath;
//    }
//
//    @Override
//    public List<Genre> getGenre() {
//        return genres;
//    }
//
//    @Override
//    public String getGenreInfo() {
//        return genreInfo;
//    }
//
//    @Override
//    public Integer getID() {
//        return id;
//    }
//
//    @Override
//    public String getImdbId() {
//        return imdbId;
//    }
//
//    @Override
//    public String getOriginalLanguange() {
//        return originalLanguage;
//    }
//
//    @Override
//    public String getOriginalTitle() {
//        return originalTitle;
//    }
//
//    @Override
//    public String getOverView() {
//        return overview;
//    }
//
//    @Override
//    public String getPosterPath() {
//        return posterPath;
//    }
//
//    @Override
//    public String getReleaseDate() {
//        return releaseDate;
//    }
//
//    @Override
//    public Integer getRunTime() {
//        return runtime;
//    }
//
//    @Override
//    public Double getVoteAvg() {
//        return voteAverage;
//    }
//
//    @Override
//    public String getName() {
//        return name;
//    }
//
//    @Override
//    public String getFirstAirDate() {
//        return firstAirDate;
//    }
//
//    @Override
//    public String getOriginalName() {
//        return originalName;
//    }
//
//    @Override
//    public List<Season> getSeasons() {
//        return seasons;
//    }

//    public TMDB(@Nullable String backdropPath, List<Genre> genres, @Nullable String email,
//                @Nullable String genreInfo, @NonNull Integer id, @Nullable String imdbId,
//                @Nullable String originalLanguage, @Nullable String originalTitle,
//                @Nullable String overview, @Nullable String posterPath, @Nullable String releaseDate,
//                @Nullable Integer runtime, @Nullable Double voteAverage, @Nullable String name,
//                @Nullable String firstAirDate, @Nullable String originalName, List<Season> seasons) {
//        this.backdropPath = backdropPath;
//        this.genres = genres;
//        this.genreInfo = genreInfo;
//        this.id = id;
//        this.imdbId = imdbId;
//        this.originalLanguage = originalLanguage;
//        this.originalTitle = originalTitle;
//        this.overview = overview;
//        this.posterPath = posterPath;
//        this.releaseDate = releaseDate;
//        this.runtime = runtime;
//        this.voteAverage = voteAverage;
//        this.name = name;
//        this.firstAirDate = firstAirDate;
//        this.originalName = originalName;
//        this.seasons = seasons;
//        this.userEmail = email;
//    }

//    @Nullable
//    public String getUserEmail() {
//        return userEmail;
//    }
//
//    public void setUserEmail(@Nullable String userEmail) {
//        this.userEmail = userEmail;
//    }
//
//    public int getPrimaryKey() {
//        return primaryKey;
//    }
//
//    public void setPrimaryKey(int primaryKey) {
//        this.primaryKey = primaryKey;
//    }
//
//    @Nullable
//    public String getBackdropPath() {
//        return backdropPath;
//    }
//
//    public void setBackdropPath(@Nullable String backdropPath) {
//        this.backdropPath = backdropPath;
//    }
//
//    private List<Genre> getGenres() {
//        return genres;
//    }
//
//    public void setGenres(List<Genre> genres) {
//        this.genres = genres;
//    }
//
//    public void setGenreInfo(@Nullable String genreInfo) {
//        this.genreInfo = genreInfo;
//    }
//
//    @NonNull
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(@NonNull Integer id) {
//        this.id = id;
//    }
//
//    @Nullable
//    public String getImdbId() {
//        return imdbId;
//    }
//
//    public void setImdbId(@Nullable String imdbId) {
//        this.imdbId = imdbId;
//    }
//
//    @Nullable
//    public String getOriginalLanguage() {
//        return originalLanguage;
//    }
//
//    public void setOriginalLanguage(@Nullable String originalLanguage) {
//        this.originalLanguage = originalLanguage;
//    }
//
//    @Nullable
//    public String getOriginalTitle() {
//        return originalTitle;
//    }
//
//    public void setOriginalTitle(@Nullable String originalTitle) {
//        this.originalTitle = originalTitle;
//    }
//
//    @Nullable
//    public String getOverview() {
//        return overview;
//    }
//
//    public void setOverview(@Nullable String overview) {
//        this.overview = overview;
//    }
//
//    @Nullable
//    public String getPosterPath() {
//        return posterPath;
//    }
//
//    public void setPosterPath(@Nullable String posterPath) {
//        this.posterPath = posterPath;
//    }
//
//    @Nullable
//    public String getReleaseDate() {
//        return releaseDate;
//    }
//
//    public void setReleaseDate(@Nullable String releaseDate) {
//        this.releaseDate = releaseDate;
//    }
//
//    @Nullable
//    public Integer getRuntime() {
//        return runtime;
//    }
//
//    public void setRuntime(@Nullable Integer runtime) {
//        this.runtime = runtime;
//    }
//
//    @Nullable
//    public Double getVoteAverage() {
//        return voteAverage;
//    }
//
//    public void setVoteAverage(@Nullable Double voteAverage) {
//        this.voteAverage = voteAverage;
//    }
//
//    @Nullable
//    public String getName() {
//        return name;
//    }
//
//    public void setName(@Nullable String name) {
//        this.name = name;
//    }
//
//    @Nullable
//    public String getFirstAirDate() {
//        return firstAirDate;
//    }
//
//    public void setFirstAirDate(@Nullable String firstAirDate) {
//        this.firstAirDate = firstAirDate;
//    }
//
//    @Nullable
//    public String getOriginalName() {
//        return originalName;
//    }
//
//    public void setOriginalName(@Nullable String originalName) {
//        this.originalName = originalName;
//    }
//
//    public List<Season> getSeasons() {
//        return seasons;
//    }
//
//    public void setSeasons(List<Season> seasons) {
//        this.seasons = seasons;
//    }
//
//    @Nullable
//    public String getGenreInfo() {
//        return genreInfo;
//    }
//
//    public String getGenresDetail() {
//        StringBuilder genre = new StringBuilder();
//        if (!getGenres().isEmpty()) {
//            genre.append(getGenres().get(0).getName());
//            for (int i = 1; i < getGenres().size(); i++) {
//                genre.append(", ");
//                genre.append(getGenres().get(i).getName());
//            }
//        }
//        return genre.toString();
//    }
}
