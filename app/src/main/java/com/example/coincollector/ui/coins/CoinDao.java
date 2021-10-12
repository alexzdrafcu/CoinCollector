package com.example.coincollector.ui.coins;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CoinDao {

    @Query("SELECT * FROM coins")
    List<DBCoin> getAll();

    @Query("SELECT * FROM coins where coinId=:id")
    DBCoin getCoinsById(int id);

    @Query("UPDATE coins SET coin=:nume where coinId=:id")
    void updateById(int id,String nume);


    @Insert
    void insert(DBCoin dbcoin);

    @Update
    void update(DBCoin dbcoin);

    @Delete
    void delete(DBCoin dbcoin);

    @Delete
    void reset(List<DBCoin> dbCoinsList);

}
