package com.example.coincollector.ui.coins;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface CoinAndDetailsDao {
    @Transaction
    @Query("SELECT * FROM details")
    List<CoinAndDetails> getCoinAndDetails();
}
