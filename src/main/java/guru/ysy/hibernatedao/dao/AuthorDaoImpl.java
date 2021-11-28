package guru.ysy.hibernatedao.dao;

import guru.ysy.hibernatedao.domain.Author;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 * Created by zhenrui on 2021/11/28 11:52
 */
@Component
public class AuthorDaoImpl implements AuthorDao {

    private final EntityManagerFactory emf;

    public AuthorDaoImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Author getById(Long id) {
        return getEntityManager().find(Author.class, id);
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        TypedQuery<Author> query = getEntityManager().createQuery(
                "SELECT a FROM Author a WHERE a.firstName = :first_name AND a.lastName = :last_name",
                Author.class);
        query.setParameter("first_name", firstName);
        query.setParameter("last_name", lastName);

        return query.getSingleResult();
    }

    @Override
    public Author saveNewAuthor(Author author) {
        EntityManager em = getEntityManager();
        //Typical process in hibernate
        em.getTransaction().begin();
//        alternative1(used together with alternative2):
//        em.joinTransaction();
        em.persist(author);
        em.flush();
        em.getTransaction().commit();
//        alternative2:
//        em.clear();
        return em.find(Author.class, author.getId());
//        alternative:
//        return author;
    }

    @Override
    public Author updateAuthor(Author author) {
        EntityManager em = getEntityManager();
//        alternative1(used together with alternative2):
//        em.joinTransaction();
        em.getTransaction().begin();
        em.merge(author);
        em.flush();
        em.getTransaction().commit();
//        alternative2:
//        em.clear();

        return em.find(Author.class,author.getId());
//        alternative:
//        return author;
    }

    @Override
    public void deleteAuthorById(Long id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Author author = em.find(Author.class, id);
        em.remove(author);
        em.flush();
        em.getTransaction().commit();
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
