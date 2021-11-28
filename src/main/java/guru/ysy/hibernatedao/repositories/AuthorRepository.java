package guru.ysy.hibernatedao.repositories;

import guru.ysy.hibernatedao.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhenrui on 2021/11/28 11:50
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
