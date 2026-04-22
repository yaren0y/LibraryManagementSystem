package org.example.factory;

import org.example.model.Book;

public interface BookFactory {
    Book createBook(String title, String author, int publicationYear, String isbn, String description);
}
