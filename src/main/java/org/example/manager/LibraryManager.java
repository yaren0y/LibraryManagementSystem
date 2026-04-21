package org.example.manager;

import org.example.model.Book;

import java.util.ArrayList;
import java.util.List;

public class LibraryManager {
    // 1. Kendi instance'ını statik olarak tutar (Singleton kuralı)
    private static LibraryManager instance;

    // Tüm kitapların tutulduğu ana liste
    private List<Book> books;

    // 2. Constructor private yapılır ki dışarıdan new LibraryManager() denemesin
    private LibraryManager() {
        this.books = new ArrayList<>();
    }

    // 3. Herkesin bu tekil objeye ulaşması için global erişim noktası
    public static LibraryManager getInstance() {
        if (instance == null) {
            instance = new LibraryManager();
        }
        return instance;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getAllBooks() {
        return books;
    }
}
