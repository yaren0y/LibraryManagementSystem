package org.example.manager;

import org.example.model.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryManager {
    private static LibraryManager instance;
    private List<Book> books;

    // Kitapların durumlarını ISBN üzerinden takip edeceğimiz Map'ler
    private Map<String, Boolean> bookAvailability; // true = Kütüphanede, false = Ödünç alınmış
    private Map<String, Integer> borrowCounts;     // Ödünç alınma sayısı

    private LibraryManager() {
        this.books = new ArrayList<>();
        this.bookAvailability = new HashMap<>();
        this.borrowCounts = new HashMap<>();
    }

    public static LibraryManager getInstance() {
        if (instance == null) {
            instance = new LibraryManager();
        }
        return instance;
    }

    public void addBook(Book book) {
        books.add(book);
        // Kitap sisteme ilk eklendiğinde müsaittir (true) ve ödünç alınma sayısı 0'dır
        bookAvailability.put(book.getIsbn(), true);
        borrowCounts.put(book.getIsbn(), 0);
    }

    public void replaceBook(Book oldBook, Book newBook) {
        int index = books.indexOf(oldBook);
        if (index != -1) {
            books.set(index, newBook);
            
            // If ISBN has changed, update the maps
            if (!oldBook.getIsbn().equals(newBook.getIsbn())) {
                Boolean availability = bookAvailability.remove(oldBook.getIsbn());
                Integer bCount = borrowCounts.remove(oldBook.getIsbn());
                
                bookAvailability.put(newBook.getIsbn(), availability != null ? availability : true);
                borrowCounts.put(newBook.getIsbn(), bCount != null ? bCount : 0);
            }
        }
    }

    public List<Book> getAllBooks() {
        return books;
    }

    // --- ÖDÜNÇ ALMA MODÜLÜ METODLARI ---

    // Kitabı ödünç alma işlemi
    public boolean borrowBook(String isbn) {
        // Eğer kitap sistemde kayıtlıysa ve müsaittirse (true)
        if (bookAvailability.containsKey(isbn) && bookAvailability.get(isbn)) {
            bookAvailability.put(isbn, false); // Artık müsait değil
            borrowCounts.put(isbn, borrowCounts.get(isbn) + 1); // Sayacı 1 artır
            System.out.println(" The book with the ISBN " + isbn + " has been borrowed successfully.");
            return true;
        }
        System.out.println("Error: this book is not available in the system.");
        return false;
    }

    // Kitabı iade etme işlemi
    public boolean returnBook(String isbn) {
        // Eğer kitap sistemde kayıtlıysa ve şu an ödünç alınmışsa (false)
        if (bookAvailability.containsKey(isbn) && !bookAvailability.get(isbn)) {
            bookAvailability.put(isbn, true); // Tekrar müsait yap
            System.out.println("The book with the ISBN " + isbn + " has been returned successfully.");
            return true;
        }
        System.out.println("Error: You do not have this book in your system.");
        return false;
    }

    // Bir kitabın kaç kere ödünç alındığını gösteren metod
    public int getBorrowCount(String isbn) {
        return borrowCounts.getOrDefault(isbn, 0);
    }

    // Bir kitabın müsaitlik durumunu gösteren metod
    public boolean isAvailable(String isbn) {
        return bookAvailability.getOrDefault(isbn, false);
    }
}