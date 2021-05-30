package com.example.consumer.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.consumer.domain.model.Acquirer;
import com.example.consumer.domain.model.Invoice;

@Repository
public interface AcquirerRepository extends CrudRepository<Acquirer,Integer>{

	Optional<Acquirer> findByDocument(String document);

}
