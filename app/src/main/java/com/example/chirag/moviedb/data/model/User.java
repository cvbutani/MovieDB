package com.example.chirag.moviedb.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * MovieDB
 * Created by Chirag on 21/10/18.
 */
@Entity(tableName = "user")
public class User {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "primary_key")
    private int primaryKey;

    @NonNull
    @ColumnInfo(name = "pass_word")
    private String passWord;

    @NonNull
    @ColumnInfo(name = "first_name")
    private String firstName;

    @NonNull
    @ColumnInfo(name = "last_name")
    private String lastName;

    @NonNull
    @ColumnInfo(name = "email_address")
    private String emailAddress;

    public User() {
    }
    @Ignore
    public User(@NonNull String passWord, @NonNull String firstName, @NonNull String lastName, @NonNull String emailAddress) {
        this.passWord = passWord;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
    }

    @NonNull
    public int getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(@NonNull int primaryKey) {
        this.primaryKey = primaryKey;
    }

    @NonNull
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(@NonNull String passWord) {
        this.passWord = passWord;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
    }

    @NonNull
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(@NonNull String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
