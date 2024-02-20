package com.openclassrooms.api.repository;

import com.openclassrooms.api.models.Rental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends CrudRepository<Rental, Long> {


}
