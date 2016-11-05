package service;

import dao.BookDao;
import model.BookEntity;
import model.SortComparator;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BookService {
    private final static Logger logger = Logger.getLogger(BookService.class.getName());
    private BookDao bookDao = new BookDao();
    private Scanner scanner = new Scanner(System.in);
    private List<BookEntity> books = bookDao.getBooks();

    public void libraryManager(String yourCommands[]) {
        logger.info("Start of libraryManager");
        String allbooks = "all books";
        String add = "add ";
        String remove = "remove ";
        String edit = "edit ";
        String help = "help";



        if (yourCommands[0].equals(add)) {
            addBookService(yourCommands[1], yourCommands[2]);
        } else if (yourCommands[0].equals(allbooks)) {
            allBookService();
        } else if (yourCommands[0].equals(remove)) {
            removeBookService(yourCommands[2]);
        } else if (yourCommands[0].equals(edit)) {
            editBookService(yourCommands[2]);
        } else if (yourCommands[0].equals(help)) {
            System.out.println(allbooks);
            System.out.println(add + "-Author \"Name\"");
            System.out.println(remove + "-Author \"Name\"");
            System.out.println(edit + "-Author \"Name\"");
            System.out.println("stop");
        }
        logger.info("Finish of libraryManager");
    }

    public void addBookService(String author, String name) {
        logger.info("Start of adding book");
        BookEntity bookEntity = new BookEntity(author, name);
        bookDao.addBook(bookEntity);
        System.out.println("book " + author + " \"" + name + "\" was added ");
        logger.info("Finish of adding book");

    }

    public void allBookService() {
        logger.info("Start of getting all books");
        List<BookEntity> books = bookDao.getBooks();
        Collections.sort(books, new SortComparator());
        System.out.println("Our books:");
        for (BookEntity bookEntity : books) {
            System.out.println(bookEntity);
        }
        logger.info("Finish of getting all books");
    }

    public void removeBookService(String name) {
        logger.info("Start of removing book");
        int idBookService = findBookService(name);
        for (BookEntity book : books) {
            if (book.getId() == idBookService) {
                bookDao.removeBook(book);
            }
        }
        System.out.println("book \"" + name + "\" was removed ");
        logger.info("Finish of removing book");
    }

    public void editBookService(String name) {
        logger.info("Start of editing book");
        System.out.println("Type new book name:");
        String newName = scanner.next();
        int idBookService = findBookService(name);
        for (BookEntity book : books) {
            if (book.getId() == idBookService) {
                bookDao.editBook(book, newName, idBookService);
            }
        }
        System.out.println("book \"" + name + "\" was edited ");
        logger.info("Finish of editing book");

    }

    public int findBookService(String name) {
        logger.info("Start of finding book");
        List<BookEntity> books = bookDao.getBooks();
        int numberOfMatches = 0;
        int idBook = -1;
        for (BookEntity book : books) {
            if (book.getName().equals(name)) {
                System.out.println(book);
                ++numberOfMatches;
                idBook = book.getId();
            }
        }
        if (numberOfMatches > 1) {
            System.out.println("We have few books with such name please choose one by typing a ID of book");
            logger.info("Finish of finding book");
            return scanner.nextInt();
        } else {
            logger.info("Finish of finding book");
            return idBook;
        }
    }
}
