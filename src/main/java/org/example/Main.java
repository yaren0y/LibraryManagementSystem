package org.example;

import org.example.command.CommandInvoker;
import org.example.command.ModifyBookCommand;
import org.example.decorator.CategoryDecorator;
import org.example.decorator.TagDecorator;
import org.example.factory.BookFactory;
import org.example.factory.ConcreteBookFactory;
import org.example.manager.LibraryManager;
import org.example.model.Book;
import org.example.search.AscendingSortStrategy;
import org.example.search.BookSearchService;
import org.example.search.DescendingSortStrategy;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final LibraryManager libraryManager = LibraryManager.getInstance();
    private static final BookFactory bookFactory = new ConcreteBookFactory();
    private static final BookSearchService searchService = new BookSearchService(new AscendingSortStrategy());
    private static final CommandInvoker commandInvoker = new CommandInvoker();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Create Book");
            System.out.println("2. Search Book");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Modify Book");
            System.out.println("6. Undo Last Modification");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1": createBook(); break;
                case "2": searchBook(); break;
                case "3": borrowBook(); break;
                case "4": returnBook(); break;
                case "5": modifyBook(); break;
                case "6": commandInvoker.undoLastCommand(); break;
                case "7": running = false; System.out.println("Exiting..."); break;
                default: System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void createBook() {
        try {
            System.out.print("Enter Title: ");
            String title = scanner.nextLine();
            System.out.print("Enter Author: ");
            String author = scanner.nextLine();
            System.out.print("Enter Publication Year: ");
            int year = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter ISBN: ");
            String isbn = scanner.nextLine();
            System.out.print("Enter Description: ");
            String desc = scanner.nextLine();

            Book book = bookFactory.createBook(title, author, year, isbn, desc);
            book = addCategoriesAndTags(book);

            libraryManager.addBook(book);
            System.out.println("Book created and saved successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid year format.");
        }
    }

    private static Book addCategoriesAndTags(Book book) {
        for (int i = 1; i <= 3; i++) {
            System.out.print("Enter Category " + i + " (or press Enter to skip): ");
            String category = scanner.nextLine();
            if (category.trim().isEmpty()) break;
            book = new CategoryDecorator(book, category);
        }

        for (int i = 1; i <= 3; i++) {
            System.out.print("Enter Tag " + i + " (or press Enter to skip): ");
            String tag = scanner.nextLine();
            if (tag.trim().isEmpty()) break;
            book = new TagDecorator(book, tag);
        }
        return book;
    }

    private static void searchBook() {
        System.out.print("Enter keyword to search: ");
        String keyword = scanner.nextLine();

        System.out.println("Sort by title: 1. Ascending 2. Descending");
        String sortChoice = scanner.nextLine();
        if (sortChoice.equals("2")) {
            searchService.setSortStrategy(new DescendingSortStrategy());
        } else {
            searchService.setSortStrategy(new AscendingSortStrategy());
        }

        List<Book> results = searchService.search(libraryManager.getAllBooks(), keyword);
        if (results.isEmpty()) {
            System.out.println("No books found.");
        } else {
            for (Book b : results) {
                b.displayInfo();
            }
        }
    }

    private static void borrowBook() {
        System.out.print("Enter ISBN to borrow: ");
        String isbn = scanner.nextLine();
        libraryManager.borrowBook(isbn);
    }

    private static void returnBook() {
        System.out.print("Enter ISBN to return: ");
        String isbn = scanner.nextLine();
        libraryManager.returnBook(isbn);
    }

    private static void modifyBook() {
        System.out.print("Enter ISBN of the book to modify: ");
        String isbn = scanner.nextLine();

        Book oldBook = null;
        for (Book b : libraryManager.getAllBooks()) {
            if (b.getIsbn().equals(isbn)) {
                oldBook = b;
                break;
            }
        }

        if (oldBook == null) {
            System.out.println("Book not found!");
            return;
        }

        System.out.println("Modifying book. Enter new details (press Enter to keep old value):");
        
        System.out.print("Enter new Title [" + oldBook.getTitle() + "]: ");
        String title = scanner.nextLine();
        if (title.trim().isEmpty()) title = oldBook.getTitle();

        System.out.print("Enter new Author [" + oldBook.getAuthor() + "]: ");
        String author = scanner.nextLine();
        if (author.trim().isEmpty()) author = oldBook.getAuthor();

        System.out.print("Enter new Publication Year [" + oldBook.getPublicationYear() + "]: ");
        String yearStr = scanner.nextLine();
        int year = oldBook.getPublicationYear();
        if (!yearStr.trim().isEmpty()) {
            try {
                year = Integer.parseInt(yearStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid year, keeping old value.");
            }
        }

        System.out.print("Enter new ISBN [" + oldBook.getIsbn() + "]: ");
        String newIsbn = scanner.nextLine();
        if (newIsbn.trim().isEmpty()) newIsbn = oldBook.getIsbn();

        System.out.print("Enter new Description [" + oldBook.getDescription() + "]: ");
        String desc = scanner.nextLine();
        if (desc.trim().isEmpty()) desc = oldBook.getDescription();

        Book newBook = bookFactory.createBook(title, author, year, newIsbn, desc);
        
        System.out.println("Re-assigning categories and tags. Old ones are discarded.");
        newBook = addCategoriesAndTags(newBook);

        ModifyBookCommand command = new ModifyBookCommand(libraryManager, oldBook, newBook);
        commandInvoker.executeCommand(command);
    }
}