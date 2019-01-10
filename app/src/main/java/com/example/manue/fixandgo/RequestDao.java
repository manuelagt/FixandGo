package com.example.manue.fixandgo;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public interface RequestDao {

    @Query("SELECT * FROM request")
    public LiveData<List<Request>> getAllRequests();

    @Insert
    public void insertRequest(Request request);

    @Delete
    public void deleteRequest(Request request);

    @Update
    public void updateRequest(Request request);
}
