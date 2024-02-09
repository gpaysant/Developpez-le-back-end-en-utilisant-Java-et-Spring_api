package com.openclassrooms.api.repository;

import com.openclassrooms.api.models.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
}
