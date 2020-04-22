package com.example.projectdve.Model;

public class Book {
    private int id;
    private String bookName;
    private String authorName;
    private double bookPrice;
    private String date;
    private String time;


    public Book() {
    }

    public Book(String bookName, String authorName, double bookPrice) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookPrice = bookPrice;
    }

    public Book(String bookName, String authorName, double bookPrice, String date, String time) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookPrice = bookPrice;
        this.date = date;
        this.time = time;
    }

    public Book(int id, String bookName, String authorName, double bookPrice, String date, String time) {
        this.id = id;
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookPrice = bookPrice;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
