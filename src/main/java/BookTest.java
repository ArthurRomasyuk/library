import dao.BookDao;
import model.BookEntity;
import model.SortComparator;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BookTest {
    public static void main(String[] args) {
        Boolean run = true;
        while (run) {
            Scanner scanner = new Scanner(System.in);
            String msgFromConsole = scanner.nextLine();
            String yourCommands[] = msgFromConsole.split("-|\"");
            for (String t : yourCommands) {
                System.out.println(t);
            }
            BookDao bookDao = new BookDao();
            String allbooks = "allbooks";
            String add = "add ";
            String remove = "remove ";
            String edit = "edit ";
            String stop = "stop ";
            if (yourCommands[0].equals(add)) {
                BookEntity bookEntity = new BookEntity(yourCommands[1], yourCommands[2]);
                bookDao.addBook(bookEntity);
            } else if (yourCommands[0].equals(allbooks)) {
                List<BookEntity> books = bookDao.getBooks();
                Collections.sort(books, new SortComparator());
                System.out.println(books);
            } else if (yourCommands[0].equals(remove)) {
                List<BookEntity> books = bookDao.getBooks();
                for (BookEntity book : books) {
                    if (book.getName().equals(yourCommands[2])) {
                        System.out.println(book);
                    }
                }
                System.out.println("We have few books with such name please choose one by typing a ID of book");
                int id = scanner.nextInt();
                for (BookEntity book : books) {
                    if (book.getId() == id) {
                        bookDao.removeBook(book);
                    }
                }

            } else if (yourCommands[0].equals(edit)) {
                List<BookEntity> books = bookDao.getBooks();
                for (BookEntity book : books) {
                    if (book.getName().equals(yourCommands[2])) {
                        System.out.println(book);
                    }
                }
                System.out.println("We have few books with such name please choose one by typing a ID of book");
                int id = scanner.nextInt();
                System.out.println("Type new book name:");
                String newName = scanner.next();
                for (BookEntity book : books) {
                    if (book.getId() == id) {
                        bookDao.editBook(book, newName, id);
                    }
                }
            } else if (yourCommands[0].equals(stop)) {
                run = false;
            }
        }
    }
}
