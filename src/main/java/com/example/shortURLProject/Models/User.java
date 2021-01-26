package com.example.shortURLProject.Models;

public class User {

  //  private String name;
    private String short_url;
    private String full_url;

    public String getFull_url() {
        return full_url;
    }

    public void setFull_url(String full_url) {
        this.full_url = full_url;
    }

    public String getShort_url() {
        return short_url;
    }

    public void setShort_url(String short_url) {
        this.short_url = short_url;
    }

    @Override
    public String toString() {
        return "User{" +
                "short_url='" + short_url + '\'' +
                ", full_url='" + full_url + '\'' +
                '}';
    }
}