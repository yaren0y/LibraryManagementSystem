package org.example.decorator;

import org.example.model.Book;
import java.util.ArrayList;
import java.util.List;

public class TagDecorator extends BookDecorator {
    private String tag;

    public TagDecorator(Book decoratedBook, String tag) {
        super(decoratedBook);
        this.tag = tag;
    }

    @Override
    public List<String> getTags() {
        List<String> tags = new ArrayList<>(super.getTags());
        if (tags.size() < 3 && !tags.contains(tag)) {
            tags.add(tag);
        }
        return tags;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Tags: " + getTags());
    }
}
