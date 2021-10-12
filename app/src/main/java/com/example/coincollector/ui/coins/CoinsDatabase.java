package com.example.coincollector.ui.coins;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DBDetails.class,DBCoin.class},version = 3, exportSchema = false)
public abstract class CoinsDatabase extends RoomDatabase {


    private static CoinsDatabase coinsDatabase;

    private static final String DB_NAME ="COINS_DATABASE.db";

    public synchronized static CoinsDatabase getInstance(Context context){
        if (coinsDatabase == null){
            coinsDatabase = Room.databaseBuilder(context,CoinsDatabase.class,DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return coinsDatabase;
    }

    public abstract DetailsDao detailsDao();
    public abstract CoinDao coinDao();

}
