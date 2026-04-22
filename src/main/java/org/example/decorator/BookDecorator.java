package org.example.decorator;

import org.example.model.Book;
import java.util.List;

public abstract class BookDecorator implements Book {
    protected Book decoratedBook;

    public BookDecorator(Book decoratedBook) {
        this.decoratedBook = decoratedBook;
    }

    @Override
    public String getTitle() { return decoratedBook.getTitle(); }

    @Override
    public String getAuthor() { return decoratedBook.getAuthor(); }

    @Override
    public int getPublicationYear() { return decoratedBook.getPublicationYear(); }

    @Override
    public String getIsbn() { return decoratedBook.getIsbn(); }

    @Override
    public String getDescription() { return decoratedBook.getDescription(); }

    @Override
    public List<String> getCategories() { return decoratedBook.getCategories(); }

    @Override
    public List<String> getTags() { return decoratedBook.getTags(); }

    @Override
    public void displayInfo() { decoratedBook.displayInfo(); }
}
