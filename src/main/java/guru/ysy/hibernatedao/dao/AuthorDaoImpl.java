package guru.ysy.hibernatedao.dao;

import guru.ysy.hibernatedao.domain.Author;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.List;

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
        EntityManager em = getEntityManager();
        try {
            return em.find(Author.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Author> query = em.createNamedQuery(
                    "find_by_name",
                    Author.class);
            query.setParameter("first_name", firstName);
            query.setParameter("last_name", lastName);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public Author saveNewAuthor(Author author) {
        EntityManager em = getEntityManager();
        try {
            //Typical process in hibernate
            em.getTransaction().begin();
//          alternative1(used together with alternative2):
//            em.joinTransaction();
            em.persist(author);
            em.flush();
            em.getTransaction().commit();
//          alternative2:
//            em.clear();
            return em.find(Author.class, author.getId());
        } finally {
            em.close();
        }
    }

    @Override
    public Author updateAuthor(Author author) {
        EntityManager em = getEntityManager();
        try {
            //alternative1(used together with alternative2):
//            em.joinTransaction();
            em.getTransaction().begin();
            em.merge(author);
            em.flush();
            em.getTransaction().commit();
//          alternative2:
//            em.clear();
            return em.find(Author.class,author.getId());
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteAuthorById(Long id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Author author = em.find(Author.class, id);
            em.remove(author);
            em.flush();
            em.getTransaction().commit();
        }finally {
            em.close();
        }
    }

    @Override
    public List<Author> listAuthorByLastNameLike(String lastName) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Author> query = em.createQuery("SELECT a FROM Author a WHERE a.lastName LIKE :last_name",
                    Author.class);
            query.setParameter("last_name", lastName + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Author> findAll() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Author> query = em.createNamedQuery("author_find_all", Author.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Author findAuthorByNameCriteria(String firstName, String lastName) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Author> cQuery = cBuilder.createQuery(Author.class);

            Root<Author> root = cQuery.from(Author.class);

            ParameterExpression<String> firstNameParam = cBuilder.parameter(String.class);
            ParameterExpression<String> lastNameParam = cBuilder.parameter(String.class);

            Predicate firstNamePred = cBuilder.equal(root.get("firstName"), firstNameParam);
            Predicate lastNamePred = cBuilder.equal(root.get("lastName"), lastNameParam);

            cQuery.select(root).where(cBuilder.and(
                    firstNamePred,lastNamePred));

            TypedQuery<Author> typedQuery = em.createQuery(cQuery);
            typedQuery.setParameter(firstNameParam, firstName);
            typedQuery.setParameter(lastNameParam, lastName);

            return typedQuery.getSingleResult();
        } finally {
            em.close();
        }
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
