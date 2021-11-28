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
        return getEntityManager().find(Book.class, id);
    }

    @Override
    public Book findByBookByTitle(String title) {
        TypedQuery<Book> query = getEntityManager().createQuery(
                "SELECT b FROM Book b WHERE b.title = :title",
                Book.class);
        query.setParameter("title", title);
        return query.getSingleResult();
    }

    @Override
    public Book findByIsbn(String isbn) {
        TypedQuery<Book> query = getEntityManager().createQuery(
                "SELECT b FROM Book b WHERE b.isbn = :isbn",
                Book.class);
        query.setParameter("isbn", isbn);
        return query.getSingleResult();
    }

    @Override
    public Book saveNewBook(Book book) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(book);
        em.flush();
        em.getTransaction().commit();
        return em.find(Book.class, book.getId());
    }

    @Override
    public Book updateBook(Book book) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(book);
        em.flush();
        em.getTransaction().commit();
        return em.find(Book.class, book.getId());
    }

    @Override
    public void deleteBookById(Long id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Book book = em.find(Book.class, id);
        em.remove(book);
        em.flush();
        em.getTransaction().commit();

    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
