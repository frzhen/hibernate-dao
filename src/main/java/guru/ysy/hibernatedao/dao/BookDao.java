package guru.ysy.hibernatedao.dao;

import guru.ysy.hibernatedao.domain.Book;
import guru.ysy.hibernatedao.repositories.BookRepository;

/**
 * Created by zhenrui on 2021/11/29 15:01
 */
public interface BookDao {

    Book getById(long id);

    Book findByBookByTitle(String title);

    Book findByIsbn(String isbn);

    Book saveNewBook(Book book);

    Book updateBook(Book book);

    void deleteBookById(Long id);

}
