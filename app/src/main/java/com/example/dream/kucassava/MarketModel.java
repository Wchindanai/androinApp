package com.example.dream.kucassava;

import java.io.Serializable;

/**
 * Created by dream on 22/9/17.
 */

public class MarketModel implements Serializable{
    private String id, marketName, price, daily;
    private int productIndex;

    public MarketModel(String id, String marketName, String price, String daily, int productIndex) {
        this.id = id;
        this.marketName = marketName;
        this.price = price;
        this.daily = daily;
        this.productIndex = productIndex;
    }

    public int getProductIndex() {
        return productIndex;
    }

    public void setProductIndex(int productIndex) {
        this.productIndex = productIndex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDaily() {
        return daily;
    }

    public void setDaily(String daily) {
        this.daily = daily;
    }
}
