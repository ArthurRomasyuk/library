package model;

import java.util.Comparator;

public class SortComparator implements Comparator<BookEntity> {
    public int compare(BookEntity book1, BookEntity book2) {
        return book1.getName().compareTo(book2.getName());
    }
}
