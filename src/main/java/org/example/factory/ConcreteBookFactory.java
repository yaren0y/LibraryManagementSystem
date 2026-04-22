package org.example.factory;

import org.example.model.Book;
import org.example.model.CoreBook;

public class ConcreteBookFactory implements BookFactory {
    @Override
    public Book createBook(String title, String author, int publicationYear, String isbn, String description) {
        return new CoreBook(title, author, publicationYear, isbn, description);
    }
}
