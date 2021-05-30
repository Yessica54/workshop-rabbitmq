package com.example.consumer.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.consumer.domain.model.Biller;

@Repository
public interface BillerRepository extends CrudRepository<Biller,Integer>{

	Optional<Biller> findByDocument(String document);

}
