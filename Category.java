package com.example.swarangigaurkar.learnersapp;

public class Category {
    public static final int C =1;
    public static final int JAVA =2;
    public static final int ANDROID =3;
    public static final int CPP =4;

    private int id;
    private String name;

    Category(){}

    public Category(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return getName();
    }
}
