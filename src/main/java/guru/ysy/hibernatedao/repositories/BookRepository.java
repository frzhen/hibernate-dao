package guru.ysy.hibernatedao.repositories;

import guru.ysy.hibernatedao.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by zhenrui on 2021/11/28 11:51
 */
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findBookByTitle(String title);

    Optional<Book> findBookByIsbn(String isbn);

}
