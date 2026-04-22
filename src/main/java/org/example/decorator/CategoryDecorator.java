package org.example.decorator;

import org.example.model.Book;
import java.util.ArrayList;
import java.util.List;

public class CategoryDecorator extends BookDecorator {
    private String category;

    public CategoryDecorator(Book decoratedBook, String category) {
        super(decoratedBook);
        this.category = category;
    }

    @Override
    public List<String> getCategories() {
        List<String> categories = new ArrayList<>(super.getCategories());
        if (categories.size() < 3 && !categories.contains(category)) {
            categories.add(category);
        }
        return categories;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Categories: " + getCategories());
    }
}
