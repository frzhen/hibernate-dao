package guru.ysy.hibernatedao.dao;

import guru.ysy.hibernatedao.domain.Book;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 * Created by zhenrui on 2021/11/28 16:58
 */
@Component
public class BookDaoImpl implements BookDao {

    private EntityManagerFactory emf;

    public BookDaoImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Book getById(Long id) {
        EntityManager em = getEntityManager();
        Book book = em.find(Book.class, id);
        em.close();
        return book;
    }

    @Override
    public Book findByBookByTitle(String title) {
        EntityManager em = getEntityManager();
        TypedQuery<Book> query = em.createQuery(
                "SELECT b FROM Book b WHERE b.title = :title",
                Book.class);
        query.setParameter("title", title);
        Book book = query.getSingleResult();
        em.close();
        return book;
    }

    @Override
    public Book findByIsbn(String isbn) {
        EntityManager em = getEntityManager();
        TypedQuery<Book> query = em.createQuery(
                "SELECT b FROM Book b WHERE b.isbn = :isbn",
                Book.class);
        query.setParameter("isbn", isbn);
        Book book = query.getSingleResult();
        em.close();
        return book;
    }

    @Override
    public Book saveNewBook(Book book) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(book);
        em.flush();
        em.getTransaction().commit();
        Book savedBook = em.find(Book.class, book.getId());
        em.close();
        return savedBook;
    }

    @Override
    public Book updateBook(Book book) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(book);
        em.flush();
        em.getTransaction().commit();
        Book updatedBook = em.find(Book.class, book.getId());
        em.close();
        return updatedBook;
    }

    @Override
    public void deleteBookById(Long id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Book book = em.find(Book.class, id);
        em.remove(book);
        em.flush();
        em.getTransaction().commit();
        em.close();
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
