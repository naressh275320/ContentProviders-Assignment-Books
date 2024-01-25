package com.example.contentprovider;

public class books {
    String id;
    String name;
    String author;
    String year;

    int image;

    public books(String id, String name, String author, String year, int image) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.image=image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
