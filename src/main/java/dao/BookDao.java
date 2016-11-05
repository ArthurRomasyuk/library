package dao;

import model.BookEntity;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;


public class BookDao {
    private final static Logger logger = Logger.getLogger(BookDao.class.getName());

    EntityManager entityManager = Persistence.createEntityManagerFactory("NewPersistenceUnit").createEntityManager();

    public List<BookEntity> getBooks() {
        long start = System.currentTimeMillis();
        List resultList = entityManager.createQuery("select book from BookEntity book").getResultList();
        logger.info ("getBooks Query lasted for " + (System.currentTimeMillis() - start));
        return resultList;
    }

    public void addBook(BookEntity bookEntity) {
        long start = System.currentTimeMillis();
        entityManager.getTransaction().begin();
        entityManager.merge(bookEntity);
        entityManager.getTransaction().commit();
        logger.info ("saving book lasted for " + (System.currentTimeMillis() - start));
    }

    public void removeBook(BookEntity bookEntity) {
        long start = System.currentTimeMillis();
        entityManager.getTransaction().begin();
        entityManager.remove(bookEntity);
        entityManager.getTransaction().commit();
        logger.info ("removing book lasted for " + (System.currentTimeMillis() - start));

    }

    public void editBook(BookEntity bookEntity, String newName, int id) {
        long start = System.currentTimeMillis();
        bookEntity = entityManager.find(BookEntity.class, id);
        entityManager.getTransaction().begin();
        bookEntity.setName(newName);
        entityManager.getTransaction().commit();
        logger.info ("editing book lasted for " + (System.currentTimeMillis() - start));
    }
}
