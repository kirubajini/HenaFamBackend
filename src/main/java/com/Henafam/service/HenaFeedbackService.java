package com.Henafam.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Henafam.model.HenaFeedback;
import com.Henafam.model.Poultry;
import com.Henafam.repository.HenaFeedbackRepository;


@Service
public class HenaFeedbackService {
	
	@Autowired
	HenaFeedbackRepository henaFeedbackRepository;

	public ResponseEntity<List<HenaFeedback>> getAllFeedback() {
		try {
			List<HenaFeedback> feedbacks = henaFeedbackRepository.findAll();
			if(feedbacks.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<>(feedbacks,HttpStatus.OK);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<HenaFeedback> createFeedback(HenaFeedback feedback) {

		try {
			Date date = new Date();
			feedback.setFeedbackDate(date);
			HenaFeedback upFeedback = henaFeedbackRepository.save(feedback);
			return new ResponseEntity<>(upFeedback ,HttpStatus.CREATED);
			
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

//	public ResponseEntity<HenaFeedback> getFeedbackById(String feedbackId) {
//		try {
//			Optional<HenaFeedback> feedback = henaFeedbackRepository.findById(feedbackId);
//			if(feedback.isPresent()) {
//				return new ResponseEntity<>( feedback.get() , HttpStatus.OK);
//			}else {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}
//		}catch(Exception e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

	public ResponseEntity<List<HenaFeedback>> getFeedbackByProductId(String productId) {
		try {
			List<HenaFeedback> feedback = henaFeedbackRepository.findByProductId(productId);
			if(feedback.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<>(feedback ,HttpStatus.OK);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<HttpStatus> deleteFeedbackByfeedbackId(String feedbackId) {
		try {
			henaFeedbackRepository.deleteById(feedbackId);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	public ResponseEntity<Map<String, Object>> getAllFeedbackInPage(int pageNo, int pageSize, String sortBy) {
		try {
			Map<String, Object> response = new HashMap<>();
		    Sort sort = Sort.by(sortBy);
			Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		    Page<HenaFeedback> page = henaFeedbackRepository.findAll(pageable);
		    response.put("data", page.getContent());
		    response.put("Total no of pages", page.getTotalPages());
		    response.put("TotalNoOfElements", page.getTotalElements());
		    response.put("Current page no", page.getNumber());
		    
		    return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		

	}
}

	
