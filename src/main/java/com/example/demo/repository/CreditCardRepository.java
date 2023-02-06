package com.example.demo.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CreditCard;


@Repository
public interface CreditCardRepository extends CrudRepository<CreditCard, Long> {

}
