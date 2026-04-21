package org.example.search;

import org.example.model.Book;
import java.util.ArrayList;
import java.util.List;

public class BookSearchService {
    // Hangi stratejinin kullanılacağını tutan değişken
    private SortStrategy sortStrategy;

    // Başlangıçta varsayılan bir strateji (örneğin A-Z) atayabiliriz
    public BookSearchService(SortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    // Çalışma zamanında (runtime) sıralama şeklini değiştirmemizi sağlayan metod
    public void setSortStrategy(SortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    // Ana arama metodu (Dokümanda başlık, yazar, kategori, etiket veya ISBN istendiği için kapsamlı tutuyoruz)
    public List<Book> search(List<Book> allBooks, String keyword) {
        List<Book> results = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();

        for (Book book : allBooks) {
            // Şimdilik sadece Title, Author ve ISBN'de arıyoruz.
            // Arkadaşın Decorator'ları bitirince buraya Category ve Tag araması da eklenecek.
            if (book.getTitle().toLowerCase().contains(lowerKeyword) ||
                    book.getAuthor().toLowerCase().contains(lowerKeyword) ||
                    book.getIsbn().toLowerCase().contains(lowerKeyword)) {
                results.add(book);
            }
        }

        // Bulunan sonuçları, o an seçili olan stratejiye göre sırala!
        sortStrategy.sort(results);

        return results;
    }
}