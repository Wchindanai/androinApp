package com.example.dream.kucassava;

/**
 * Created by dream on 22/9/17.
 */

public class PlantationDetailModel {
    private String name, expect_product;

    public PlantationDetailModel(String name, String expect_product) {
        this.name = name;
        this.expect_product = expect_product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpect_product() {
        return expect_product;
    }

    public void setExpect_product(String expect_product) {
        this.expect_product = expect_product;
    }
}
