package org.example.search;

import org.example.model.Book;
import java.util.List;

public interface SortStrategy {
    // Tüm stratejiler bu metodu ezmek (override) zorunda
    void sort(List<Book> books);
}