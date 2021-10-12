package com.example.coincollector.ui.coins;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "details")
public class DBDetails implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "detailsId")
    private int detailsId;

    @ColumnInfo(name = "material")
    private String material;

    @ColumnInfo(name = "diametru")
    private String diametru;

    @ColumnInfo(name = "greutate")
    private String greutate;

    @ColumnInfo(name = "muchie")
    private String muchie;

    @ColumnInfo(name = "imgUrl")
    private String imgUrl;

    public DBDetails() {
    }

    public int getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(int detailsId) {
        this.detailsId = detailsId;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDiametru() {
        return diametru;
    }

    public void setDiametru(String diametru) {
        this.diametru = diametru;
    }

    public String getGreutate() {
        return greutate;
    }

    public void setGreutate(String greutate) {
        this.greutate = greutate;
    }

    public String getMuchie() {
        return muchie;
    }

    public void setMuchie(String muchie) {
        this.muchie = muchie;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "DBDetails{" +
                "detailsId=" + detailsId +
                ", material='" + material + '\'' +
                ", diametru='" + diametru + '\'' +
                ", greutate='" + greutate + '\'' +
                ", muchie='" + muchie + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
