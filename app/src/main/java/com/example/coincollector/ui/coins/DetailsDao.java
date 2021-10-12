package com.example.coincollector.ui.coins;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DetailsDao {

    @Query("SELECT * FROM details")
    List<DBDetails> getAll();

    @Query("SELECT * FROM details where detailsId=:id")
    DBDetails getDetailsById(int id);

    @Query("UPDATE details SET material=:material, diametru=:diametru, greutate=:greutate, muchie=:muchie where detailsId=:id")
    void updateById(int id,String material, String diametru, String greutate, String muchie);

    @Insert
    void insert(DBDetails dbdetails);

    @Update
    void update(DBDetails dbdetails);

    @Delete
    void delete(DBDetails dbdetails);

    @Delete
    void reset(List<DBDetails> dbDetailsList);
}
