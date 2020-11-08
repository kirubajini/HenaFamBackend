package com.Henafam.repository;



import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.Henafam.model.Poultry;

@Repository
public interface PoultryRepository extends MongoRepository<Poultry,String>{

	
//	    List<Poultry> findByPublished(boolean published);
//		List<Poultry> findByPoultry(String Poultry);
	

	@Query(" {$or:[{'name' : {$regex: ?0, $options: 'i'}}, {'price': {$regex: ?0, $options: 'i'}} ]}")
	List<Poultry> searchPoultry(String name);

	//List<Poultry> findByPriceBetween(double minPrice, double maxPrice, Sort sort);
	@Query("{$and : [{ 'price': {$gt : ?0}}  , {'ratting': {$gte : ?1 }}] }")
	List<Poultry> findByPriceBeetween(double minPrice, double ratting, Sort sort);



   }


