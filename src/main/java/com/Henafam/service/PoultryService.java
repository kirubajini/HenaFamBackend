package com.Henafam.service;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Henafam.model.Poultry;
import com.Henafam.repository.PoultryRepository;







@Service
public class PoultryService {
	
	//private static final CrudRepository<Poultry, String> poultryReponsitory = null;
	@Autowired
	PoultryRepository poultryRepository;
	
	

	public ResponseEntity< List<Poultry>> getAllPoultry () {
		try {
			List<Poultry> poultry= poultryRepository.findAll();
			if (poultry.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<> (poultry,HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	
	public ResponseEntity<Poultry> createpoultry(Poultry poultry) {
	    try {
			Poultry pou = poultryRepository.insert(poultry);
			return new ResponseEntity<>(pou,HttpStatus.CREATED);
		 }catch (Exception e) {
			 return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		 }
}
	public ResponseEntity <Poultry> getPoultryById(String id) {
		Optional<Poultry> poultry =poultryRepository.findById(id);
		if (poultry.isPresent()) {
			return new ResponseEntity<>(poultry.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<Poultry> updatePoultryById(String id, Poultry poultry) {
		Optional<Poultry> oldPoultry = poultryRepository.findById(id);
		if (oldPoultry.isPresent()) {
			Poultry _poultry = oldPoultry.get();
			_poultry.setName(poultry.getName());
			_poultry.setDescribtion(poultry.getDescribtion());
			_poultry.setPrice(poultry.getPrice());
			_poultry.setUrl(poultry.getUrl());
			_poultry.setAvilable(poultry.getAvilable());
			
			return new ResponseEntity<> (poultryRepository.save(_poultry),HttpStatus.OK);
			}else {
				return new ResponseEntity<> (HttpStatus.NOT_FOUND);
			}
	}
	
	public ResponseEntity<HttpStatus>  PoultryById(String id) {
		try {
			poultryRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	public ResponseEntity<Map<String, Object>> getAllPoultryInPage(int pageNo, int pageSize, String sortBy) {
		try {
			Map<String, Object> response = new HashMap<>();
		    Sort sort = Sort.by(sortBy);
			Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		    Page<Poultry> page = poultryRepository.findAll(pageable);
		    response.put("data", page.getContent());
		    response.put("Total no of pages", page.getTotalPages());
		    response.put("TotalNoOfElements", page.getTotalElements());
		    response.put("Current page no", page.getNumber());
		    
		    return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	public ResponseEntity<List<Poultry>> searchPoultry(String name) {
//		try {
//			List<Poultry> poultry = poultryRepository.searchPoultry(name);
//			
//			if (poultry.isEmpty()) {
//				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//			}
//			return new ResponseEntity<>(poultry, HttpStatus.OK);
//		}catch (Exception e) {
//			
//			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
//		}
//	}



	public ResponseEntity<Map<String, Object>> getPoultryByPriceBB(double minPrice, double ratting,int pageNo,int pageSize) {
		Sort sort = Sort.by(Sort.Direction.DESC,"Price");
		List<Poultry> poultry = poultryRepository.findByPriceBeetween(minPrice,ratting,sort);
		Map<String, Object> response = new HashMap<>();
		PagedListHolder<Poultry> page = new PagedListHolder<Poultry>(poultry);
		page.setPageSize(pageSize); // number of items per page
		page.setPage(pageNo); 
		
		response.put("data", page.getPageList());
		response.put("Total_No_Of_Elements", page.getNrOfElements());
		response.put("TotalNoOfPages", page.getPage());
		return new ResponseEntity<>(response, HttpStatus.OK);
//		try {
//			if (poultry.isEmpty()) {
//				return new ResponseEntity<>( HttpStatus.NO_CONTENT);
//			} else {
//				return new ResponseEntity<>(poultry ,HttpStatus.OK);
//			}
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
//		}
	}

	public ResponseEntity<Map<String, Object>> searchedPoultry(String searched, int pageNo, int pageSize) {
		List<Poultry> searchedPoultry = poultryRepository.searchPoultry(searched);
		Map<String, Object> response = new HashMap<>();
		PagedListHolder<Poultry> page = new PagedListHolder<Poultry>(searchedPoultry);
		page.setPageSize(pageSize); // number of items per page
		page.setPage(pageNo); 
		
		response.put("data", page.getPageList());
		response.put("Total_No_Of_Elements", page.getNrOfElements());
		response.put("TotalNoOfPages", page.getPage());
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}






}
