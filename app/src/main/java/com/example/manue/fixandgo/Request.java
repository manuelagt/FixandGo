package com.example.manue.fixandgo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "request")
public class Request implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "title")
    public String mRequestTitle;

    @ColumnInfo (name = "description")
    public String mRequestDescription;

    @ColumnInfo (name = "name")
    public String mRequestName;

    @ColumnInfo (name = "email")
    public String mRequestEmail;

    public Request(String mRequestTitle, String mRequestDescription, String mRequestName, String mRequestEmail) {
        this.mRequestTitle = mRequestTitle;
        this.mRequestDescription = mRequestDescription;
        this.mRequestName = mRequestName;
        this.mRequestEmail = mRequestEmail;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRequestTitle() {
        return mRequestTitle;
    }

    public void setRequestTitle(String mRequestTitle) {
        this.mRequestTitle = mRequestTitle;
    }

    public String getRequestDescription() {
        return mRequestDescription;
    }

    public void setRequestDescription(String mRequestDescription) {
        this.mRequestDescription = mRequestDescription;
    }

    public String getRequestName() {
        return mRequestName;
    }

    public void setRequestName(String mRequestName) {
        this.mRequestName = mRequestName;
    }

    public String getRequestEmail() {
        return mRequestEmail;
    }

    public void setRequestEmail(String mRequestEmail) {
        this.mRequestEmail = mRequestEmail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.id);
        parcel.writeString(this.mRequestTitle);
        parcel.writeString(this.mRequestDescription);
        parcel.writeString(this.mRequestName);
        parcel.writeString(this.mRequestEmail);
    }

    protected Request(Parcel in){
        this.id = in.readLong();
        this.mRequestTitle = in.readString();
        this.mRequestDescription = in.readString();
        this.mRequestName = in.readString();
        this.mRequestEmail = in.readString();

    }

    public static final Creator<Request> CREATOR = new Creator<Request>() {
        @Override
        public Request createFromParcel(Parcel source) {
            return new Request(source);
        }

        @Override
        public Request[] newArray(int size) {
            return new Request[size];
        }
    };
}
