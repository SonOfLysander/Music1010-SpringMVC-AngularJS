package io.paulbaker.music1010.repositories;

import io.paulbaker.music1010.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by paul on 10/14/15.
 */
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
