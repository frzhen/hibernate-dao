package guru.ysy.hibernatedao.repositories;

import guru.ysy.hibernatedao.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by zhenrui on 2021/11/28 11:50
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findAuthorByFirstNameAndLastName(String firstName, String lastName);
}
