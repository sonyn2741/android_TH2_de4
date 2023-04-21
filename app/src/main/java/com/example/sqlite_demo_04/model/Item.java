package com.example.sqlite_demo_04.model;

import java.io.Serializable;

public class Item implements Serializable {
    private int id;
    private String name, author, range, oop;
    private float rating;

    public Item() {
    }

    public Item(int id, String name, String author, String range, String oop, float rating) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.range = range;
        this.oop = oop;
        this.rating = rating;
    }

    public Item(String name, String author, String range, String oop, float rating) {
        this.name = name;
        this.author = author;
        this.range = range;
        this.oop = oop;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getOop() {
        return oop;
    }

    public void setOop(String oop) {
        this.oop = oop;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
