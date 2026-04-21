package org.example.model;

import java.util.List;

public interface Book {
    String getTitle();
    String getAuthor();
    int getPublicationYear();
    String getIsbn();
    String getDescription();

    // Arkadaşının Decorator ile dolduracağı dinamik alanlar
    List<String> getCategories();
    List<String> getTags();

    void displayInfo();
}