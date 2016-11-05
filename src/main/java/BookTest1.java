import dao.BookDao;
import model.BookEntity;
import model.SortComparator;
import org.apache.log4j.Logger;
import service.BookService;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.jar.Pack200;

public class BookTest1 {
    private final static Logger logger = Logger.getLogger(BookTest1.class.getName());

    public static void main(String[] args) {
        Boolean run = true;
        String stop = "stop ";
        System.out.println("Format of commands: comand -Author \"Name\"");
        System.out.println("Example: add -J. Rowling \"Harry Potter\"");
        System.out.println("See all commands: help");
        while (run) {
            try {
                BookService bookService = new BookService();
                Scanner scanner = new Scanner(System.in);
                String msgFromConsole = scanner.nextLine();
                String yourCommands[] = msgFromConsole.split("-|\"");
                if (!(yourCommands[0].equals(stop))) {
                    bookService.libraryManager(yourCommands);
                } else {
                    run = false;
                }
            } catch (RuntimeException r) {
                logger.error("Wrong input", r);
            }
        }

    }
}
