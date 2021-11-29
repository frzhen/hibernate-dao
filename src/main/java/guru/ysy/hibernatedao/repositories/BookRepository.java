package guru.ysy.hibernatedao.repositories;

import guru.ysy.hibernatedao.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhenrui on 2021/11/28 11:51
 */
public interface BookRepository extends JpaRepository<Book, Long> {

    Book findBookByTitle(String title);

    Book findBookByIsbn(String isbn);

}
