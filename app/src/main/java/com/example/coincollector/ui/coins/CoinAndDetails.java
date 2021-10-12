package com.example.coincollector.ui.coins;

import androidx.room.Embedded;
import androidx.room.Relation;


public class CoinAndDetails {
    @Embedded public DBDetails dbdetails;
    @Relation(
            parentColumn = "detailsId",
            entityColumn = "fk_detailsId"
    )
    public DBCoin dbcoin;
}
