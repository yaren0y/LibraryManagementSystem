package org.example.search;

import org.example.model.Book;
import java.util.Comparator;
import java.util.List;

public class DescendingSortStrategy implements SortStrategy {
    @Override
    public void sort(List<Book> books) {
        // Kitapları başlıklarına göre azalan sırada sıralar (reversed)
        books.sort(Comparator.comparing(Book::getTitle).reversed());
    }
}