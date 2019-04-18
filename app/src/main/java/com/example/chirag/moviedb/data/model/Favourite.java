package com.example.chirag.moviedb.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * MovieDB
 * Created by Chirag on 22/10/18.
 */
@Entity(tableName = "favourite")
public class Favourite {

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "primary_key")
        private Integer primary_key;

        @NonNull
        @ColumnInfo(name = "id")
        private Integer id;

        @NonNull
        @ColumnInfo(name = "email")
        private String email;

        @NonNull
        @ColumnInfo(name = "poster")
        private String poster;

        @NonNull
        @ColumnInfo(name = "title")
        private String title;

        @NonNull
        @ColumnInfo(name = "content_type")
        private String content_type;

        public Favourite(@NonNull Integer id, @NonNull String email, @NonNull String poster, @NonNull String title, @NonNull String content_type ) {
                this.id = id;
                this.email = email;
                this.poster = poster;
                this.title = title;
                this.content_type = content_type;
        }

        public Integer getPrimary_key() {
                return primary_key;
        }

        public void setPrimary_key(Integer primary_key) {
                this.primary_key = primary_key;
        }

        @NonNull
        public Integer getId() {
                return id;
        }

        public void setId(@NonNull Integer id) {
                this.id = id;
        }

        @NonNull
        public String getEmail() {
                return email;
        }

        public void setEmail(@NonNull String email) {
                this.email = email;
        }

        @NonNull
        public String getPoster() {
                return poster;
        }

        public void setPoster(@NonNull String poster) {
                this.poster = poster;
        }

        @NonNull
        public String getTitle() {
                return title;
        }

        public void setTitle(@NonNull String title) {
                this.title = title;
        }

        @NonNull
        public String getContent_type() {
                return content_type;
        }

        public void setContent_type(@NonNull String content_type) {
                this.content_type = content_type;
        }
}
