package org.example.search;

import org.example.model.Book;
import java.util.Comparator;
import java.util.List;

public class AscendingSortStrategy implements SortStrategy {
    @Override
    public void sort(List<Book> books) {
        // Kitapları başlıklarına (Title) göre artan sırada sıralar
        books.sort(Comparator.comparing(Book::getTitle));
    }
}