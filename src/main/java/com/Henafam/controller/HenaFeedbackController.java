package com.Henafam.controller;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Henafam.model.HenaFeedback;
import com.Henafam.model.Poultry;
import com.Henafam.service.HenaFeedbackService;

@CrossOrigin("*")
@RestController
@RequestMapping("/feedbacks")
public class HenaFeedbackController {
	@Autowired
	HenaFeedbackService henaFeedbackService;
	
	@GetMapping
	public ResponseEntity<List<HenaFeedback>> getAllFeedback(){
		return henaFeedbackService.getAllFeedback();
	}
	
	@PostMapping
	public ResponseEntity<HenaFeedback> createFeedback(@RequestBody HenaFeedback henafeedback){
		return henaFeedbackService.createFeedback(henafeedback);
	}
	
//	@GetMapping("/{feedbackId}")
//	public ResponseEntity<HenaFeedback>getFeedbackById(@PathVariable String feedbackId){
//		return henaFeedbackService.getFeedbackById(feedbackId);
//	}
	
	@GetMapping(params="productId")
	public ResponseEntity<List<HenaFeedback>> getFeedbackByProductId(@RequestParam String productId){
		return henaFeedbackService.getFeedbackByProductId(productId);
	}
	 @DeleteMapping("/{feedbackId}")
	 public ResponseEntity<HttpStatus>  deleteFeedbackByfeedbackId(@PathVariable String feedbackId) {
		 return henaFeedbackService.deleteFeedbackByfeedbackId(feedbackId);
	 }

	 @GetMapping("/page")
	    public ResponseEntity<Map<String, Object>> getAllFeedbackInPage(
	    		@RequestParam(name = "pageNo", defaultValue = "0") int pageNo, 
	    		@RequestParam(name = "pageSize", defaultValue = "2") int pageSize, 
	    		@RequestParam(name = "sortBy", defaultValue = "id") String sortBy) {
			return  henaFeedbackService.getAllFeedbackInPage(pageNo, pageSize, sortBy);
		}
}


