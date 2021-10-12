package com.example.coincollector;

public class Coin {
    private int coinId;
    private String coinTitle;
    private String coinMaterial;
    private String coinDiameter;
    private String coinWeight;
    private String coinEdge;
    private String coinImageUrl;

    public Coin() {
    }

    public Coin(int coinId, String coinTitle, String coinMaterial, String coinDiameter, String coinWeight, String coinEdge, String coinImageUrl) {
        this.coinId = coinId;
        this.coinTitle = coinTitle;
        this.coinMaterial = coinMaterial;
        this.coinDiameter = coinDiameter;
        this.coinWeight = coinWeight;
        this.coinEdge = coinEdge;
        this.coinImageUrl = coinImageUrl;
    }

    public Coin(String coinTitle, String coinMaterial, String coinDiameter, String coinWeight, String coinEdge, String coinImageUrl) {
        this.coinTitle = coinTitle;
        this.coinMaterial = coinMaterial;
        this.coinDiameter = coinDiameter;
        this.coinWeight = coinWeight;
        this.coinEdge = coinEdge;
        this.coinImageUrl = coinImageUrl;
    }

    public int getCoinId() {
        return coinId;
    }

    public void setCoinId(int coinId) {
        this.coinId = coinId;
    }

    public String getCoinTitle() {
        return coinTitle;
    }

    public String getCoinMaterial() {
        return coinMaterial;
    }

    public String getCoinDiameter() {
        return coinDiameter;
    }

    public String getCoinWeight() {
        return coinWeight;
    }

    public String getCoinEdge() {
        return coinEdge;
    }

    public String getCoinImageUrl() {
        return coinImageUrl;
    }

    public void setCoinTitle(String coinTitle) {
        this.coinTitle = coinTitle;
    }

    public void setCoinMaterial(String coinMaterial) {
        this.coinMaterial = coinMaterial;
    }

    public void setCoinDiameter(String coinDiameter) {
        this.coinDiameter = coinDiameter;
    }

    public void setCoinWeight(String coinWeight) {
        this.coinWeight = coinWeight;
    }

    public void setCoinEdge(String coinEdge) {
        this.coinEdge = coinEdge;
    }

    public void setCoinImageUrl(String coinImageUrl) {
        this.coinImageUrl = coinImageUrl;
    }

    @Override
    public String toString() {
        return "Coin{" +
                "coinId=" + coinId +
                ", coinTitle='" + coinTitle + '\'' +
                ", coinMaterial='" + coinMaterial + '\'' +
                ", coinDiameter='" + coinDiameter + '\'' +
                ", coinWeight='" + coinWeight + '\'' +
                ", coinEdge='" + coinEdge + '\'' +
                ", coinImageUrl='" + coinImageUrl + '\'' +
                '}';
    }
}
