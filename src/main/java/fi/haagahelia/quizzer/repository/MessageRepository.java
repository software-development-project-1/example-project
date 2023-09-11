package fi.haagahelia.quizzer.repository;

import org.springframework.stereotype.Repository;

import fi.haagahelia.quizzer.model.Message;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
