package org.example.command;

import org.example.manager.LibraryManager;
import org.example.model.Book;

public class ModifyBookCommand implements Command {
    private LibraryManager libraryManager;
    private Book oldBook;
    private Book newBook;

    public ModifyBookCommand(LibraryManager libraryManager, Book oldBook, Book newBook) {
        this.libraryManager = libraryManager;
        this.oldBook = oldBook;
        this.newBook = newBook;
    }

    @Override
    public void execute() {
        libraryManager.replaceBook(oldBook, newBook);
        System.out.println("Book modification executed.");
    }

    @Override
    public void undo() {
        libraryManager.replaceBook(newBook, oldBook);
        System.out.println("Book modification undone.");
    }
}
