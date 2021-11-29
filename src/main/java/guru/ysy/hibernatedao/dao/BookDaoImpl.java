package guru.ysy.hibernatedao.dao;

import guru.ysy.hibernatedao.domain.Book;
import guru.ysy.hibernatedao.repositories.BookRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

/**
 * Created by zhenrui on 2021/11/29 15:04
 */
@Component
public class BookDaoImpl implements BookDao {

    private final BookRepository bookRepo;

    public BookDaoImpl(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }


    @Override
    public Book getById(long id) {
        return bookRepo.getById(id);
    }

    @Override
    public Book findBookByTitle(String title) {
        return bookRepo.findBookByTitle(title)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return bookRepo.findBookByIsbn(isbn)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Book saveNewBook(Book book) {
        return bookRepo.save(book);
    }

    @Transactional
    @Override
    public Book updateBook(Book book) {
        Book foundBook = bookRepo.getById(book.getId());
        foundBook.setTitle(book.getTitle());
        foundBook.setPublisher(book.getPublisher());
        foundBook.setIsbn(book.getIsbn());
        foundBook.setAuthorId(book.getAuthorId());
        return bookRepo.save(foundBook);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepo.deleteById(id);
    }
}
