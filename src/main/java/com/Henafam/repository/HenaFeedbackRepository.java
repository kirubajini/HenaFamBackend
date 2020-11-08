package com.Henafam.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.Henafam.model.HenaFeedback;


@Repository
public interface HenaFeedbackRepository extends MongoRepository<HenaFeedback, String> {

	List<HenaFeedback> findByProductId(String productId);

	Long countByProductId(String pro);


	static void deleteFeedbackByfeedbackId(String productId) {
		// TODO Auto-generated method stub
		
	}

//	List<HenaFeedback> searchFeedback(String id);




	


}