package guru.ysy.hibernatedao.dao;

import guru.ysy.hibernatedao.domain.Book;

import java.util.List;

/**
 * Created by zhenrui on 2021/11/28 16:57
 */
public interface BookDao {
    Book getById(Long id);

    Book findByBookByTitle(String title);

    Book findByIsbn(String title);

    Book saveNewBook(Book book);

    Book updateBook(Book book);

    void deleteBookById(Long id);

    List<Book> findAll();
}
