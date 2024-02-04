package com.openclassrooms.api.repository;

import com.openclassrooms.api.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    boolean existsByName(String username);
    List<User> findByName(String name);
    User findByEmail(String email);

}
