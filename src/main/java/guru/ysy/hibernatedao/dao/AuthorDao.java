package guru.ysy.hibernatedao.dao;

import guru.ysy.hibernatedao.domain.Author;

/**
 * Created by zhenrui on 2021/11/28 11:52
 */
public interface AuthorDao {
    Author getById(Long id);

    Author findAuthorByName(String firstName, String lastName);

    Author saveNewAuthor(Author author);

    Author updateAuthor(Author author);

    void deleteAuthorById(Long id);
}
