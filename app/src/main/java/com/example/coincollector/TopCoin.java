package com.example.coincollector;

public class TopCoin {
    private String topCoinId;
    private String topCoinNume;
    private String topCoinAn;
    private String topCoinValoareNominala;
    private String topCoinMaterial;
    private String topCoinGreutate;
    private String topCoinDiametru;
    private String topCoinMargine;
    private String topCoinDetalii1;
    private String topCoinDetalii2;
    private String topCoinDetalii3;
    private String topCoinPret;
    private String topCoinImageURL;

    public TopCoin(){

    }

    public TopCoin(String topCoinNume,String topCoinAn,String topCoinMaterial,String topCoinPret, String topCoinImageURL){
        this.topCoinNume = topCoinNume;
        this.topCoinAn = topCoinAn;
        this.topCoinMaterial = topCoinMaterial;
        this.topCoinPret = topCoinPret;
        this.topCoinImageURL = topCoinImageURL;
    }

    public TopCoin(String topCoinNume,String topCoinAn,String topCoinMaterial,String topCoinPret, String topCoinImageURL,String topCoinDetalii1,String topCoinDetalii2,String topCoinDetalii3){
        this.topCoinNume = topCoinNume;
        this.topCoinAn = topCoinAn;
        this.topCoinMaterial = topCoinMaterial;
        this.topCoinPret = topCoinPret;
        this.topCoinImageURL = topCoinImageURL;
        this.topCoinDetalii1 = topCoinDetalii1;
        this.topCoinDetalii1 = topCoinDetalii2;
        this.topCoinDetalii1 = topCoinDetalii3;


    }

    public TopCoin(String topCoinId, String topCoinNume, String topCoinAn, String topCoinValoareNominala, String topCoinMaterial, String topCoinGreutate,String topCoinDiametru,String topCoinMargine,String topCoinDetalii1,String topCoinDetalii2,String topCoinDetalii3, String topCoinPret, String topCoinImageURL) {
        this.topCoinId = topCoinId;
        this.topCoinNume = topCoinNume;
        this.topCoinAn = topCoinAn;
        this.topCoinValoareNominala = topCoinValoareNominala;
        this.topCoinMaterial = topCoinMaterial;
        this.topCoinGreutate = topCoinGreutate;
        this.topCoinDiametru = topCoinDiametru;
        this.topCoinMargine = topCoinMargine;
        this.topCoinDetalii1 = topCoinDetalii1;
        this.topCoinDetalii2 = topCoinDetalii2;
        this.topCoinDetalii3 = topCoinDetalii3;
        this.topCoinPret = topCoinPret;
        this.topCoinImageURL = topCoinImageURL;
    }

    public String getTopCoinId() {
        return topCoinId;
    }

    public String getTopCoinNume() {
        return topCoinNume;
    }

    public String getTopCoinAn() {
        return topCoinAn;
    }

    public String getTopCoinValoareNominala() {
        return topCoinValoareNominala;
    }

    public String getTopCoinMaterial() {
        return topCoinMaterial;
    }

    public String getTopCoinGreutate() {
        return topCoinGreutate;
    }

    public String getTopCoinDiametru() {
        return topCoinDiametru;
    }

    public String getTopCoinMargine() {
        return topCoinMargine;
    }

    public String getTopCoinDetalii1() {
        return topCoinDetalii1;
    }

    public String getTopCoinDetalii2() {
        return topCoinDetalii2;
    }

    public String getTopCoinDetalii3() {
        return topCoinDetalii3;
    }

    public String getTopCoinPret() {
        return topCoinPret;
    }

    public String getTopCoinImageURL() {
        return topCoinImageURL;
    }

    public void setTopCoinId(String topCoinId) {
        this.topCoinId = topCoinId;
    }

    public void setTopCoinNume(String topCoinNume) {
        this.topCoinNume = topCoinNume;
    }

    public void setTopCoinAn(String topCoinAn) {
        this.topCoinAn = topCoinAn;
    }

    public void setTopCoinValoareNominala(String topCoinValoareNominala) {
        this.topCoinValoareNominala = topCoinValoareNominala;
    }

    public void setTopCoinMaterial(String topCoinMaterial) {
        this.topCoinMaterial = topCoinMaterial;
    }

    public void setTopCoinGreutate(String topCoinGreutate) {
        this.topCoinGreutate = topCoinGreutate;
    }

    public void setTopCoinDiametru(String topCoinDiametru) {
        this.topCoinDiametru = topCoinDiametru;
    }

    public void setTopCoinMargine(String topCoinMargine) {
        this.topCoinMargine = topCoinMargine;
    }

    public void setTopCoinDetalii1(String topCoinDetalii1) {
        this.topCoinDetalii1 = topCoinDetalii1;
    }

    public void setTopCoinDetalii2(String topCoinDetalii2) {
        this.topCoinDetalii2 = topCoinDetalii2;
    }

    public void setTopCoinDetalii3(String topCoinDetalii3) {
        this.topCoinDetalii3 = topCoinDetalii3;
    }

    public void setTopCoinPret(String topCoinPret) {
        this.topCoinPret = topCoinPret;
    }

    public void setTopCoinImageURL(String topCoinImageURL) {
        this.topCoinImageURL = topCoinImageURL;
    }


}
