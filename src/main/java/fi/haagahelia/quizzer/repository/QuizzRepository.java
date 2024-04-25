package fi.haagahelia.quizzer.repository;

import org.springframework.data.repository.CrudRepository;

import fi.haagahelia.quizzer.model.Status;
import fi.haagahelia.quizzer.model.Quizz;

import java.util.List;

public interface QuizzRepository extends CrudRepository<Quizz, Long> {
    List<Quizz> findByStatus(Status status);

    List<Quizz> findAll();
}