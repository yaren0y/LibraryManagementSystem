package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class CoreBook implements Book {
    private String title;
    private String author;
    private int publicationYear;
    private String isbn;
    private String description;

    public CoreBook(String title, String author, int publicationYear, String isbn, String description) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.description = description;
    }

    @Override public String getTitle() { return title; }
    @Override public String getAuthor() { return author; }
    @Override public int getPublicationYear() { return publicationYear; }
    @Override public String getIsbn() { return isbn; }
    @Override public String getDescription() { return description; }

    @Override
    public List<String> getCategories() {
        return new ArrayList<>(); // Temel kitapta kategori boştur
    }

    @Override
    public List<String> getTags() {
        return new ArrayList<>(); // Temel kitapta etiket boştur
    }

    @Override
    public void displayInfo() {
        System.out.println("Title: " + title + " | Author: " + author + " | ISBN: " + isbn);
    }
}