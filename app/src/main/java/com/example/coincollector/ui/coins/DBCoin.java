package com.example.coincollector.ui.coins;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName = "coins")
public class DBCoin implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int coinId;



    @ColumnInfo(name = "coin")
    private String coin;

    @ColumnInfo(name = "fk_detailsId", index = true)
    private int fk_detailsId;

    public DBCoin() {
    }

    public void setFk_detailsId(int fk_detailsId) {
        this.fk_detailsId = fk_detailsId;
    }

    public int getCoinId() {
        return coinId;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public void setCoinId(int coinId) {
        this.coinId = coinId;
    }

    public int getFk_detailsId() {
        return fk_detailsId;
    }

    @Override
    public String toString() {
        return "DBCoin{" +
                "coinId=" + coinId +
                ", coin='" + coin + '\'' +
                ", fk_detailsId='" + fk_detailsId + '\'' +
                '}';
    }
}
