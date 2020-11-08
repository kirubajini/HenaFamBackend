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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Henafam.model.Poultry;
import com.Henafam.service.PoultryService;



@CrossOrigin("*")
@RestController
@RequestMapping ("/poultry")
public class PoultryController {
	
	@Autowired
	PoultryService poultryService;
	

	@GetMapping
	public ResponseEntity< List<Poultry>> getAllPoultry() {
		 return poultryService.getAllPoultry ();
		
	}
	@PostMapping
	public ResponseEntity <Poultry> createPoultry (@RequestBody Poultry poultry){
	   return poultryService.createpoultry(poultry);

     }
	@GetMapping("/{id}")
	  public  ResponseEntity <Poultry> getPoultryById(@PathVariable String id) {
		 return poultryService.getPoultryById(id);
	 }
	 @PutMapping("/{id}")
	 public ResponseEntity<Poultry> updatePoultryById(@RequestBody Poultry poultry, @PathVariable String id) {
		return poultryService.updatePoultryById(id,poultry);
		 
	 }
	
		
	 @DeleteMapping("/{id}")
	 public ResponseEntity<HttpStatus>  deletePoultry(@PathVariable String id) {
		 return poultryService.PoultryById(id);
	 }
	 
	 @GetMapping("/page")
	    public ResponseEntity<Map<String, Object>> getAllPoultryInPage(
	    		@RequestParam(name = "pageNo", defaultValue = "0") int pageNo, 
	    		@RequestParam(name = "pageSize", defaultValue = "2") int pageSize, 
	    		@RequestParam(name = "sortBy", defaultValue = "id") String sortBy) {
			return poultryService.getAllPoultryInPage(pageNo, pageSize, sortBy);
		}
	
//		 @GetMapping(params = "name")
//		public ResponseEntity<List<Poultry>> searchPoultry(@RequestParam String name){
//		 return poultryService.searchPoultry(name);
//		}
//	 
	 
	 @GetMapping(value = "/page/serachedPages")
		public ResponseEntity<Map<String,Object>> getSerchedPoultry(
				@RequestParam(name = "searched",defaultValue = "null") String searched,
				@RequestParam(name = "pageNo",defaultValue = "0") int pageNo,
				@RequestParam(name = "pageSize",defaultValue = "5") int pageSize
				){
			return poultryService.searchedPoultry(searched,pageNo,pageSize);
		}
         

	 
	 
	 @GetMapping(value= "/page/fillter" )
		public ResponseEntity<Map<String, Object>>getPoultrytByPriceBB(
//				@RequestParam(name = "maxPrice", defaultValue = "1000") Double maxPrice, 
				@RequestParam(name = "minPrice", defaultValue = "30") Double minPrice, 
				@RequestParam(name = "ratting", defaultValue = "0") Double ratting,
				@RequestParam(name = "pageNo",defaultValue = "0") int pageNo,
				@RequestParam(name = "pageSize",defaultValue = "5") int pageSize){
			return poultryService.getPoultryByPriceBB(minPrice,ratting,pageNo,pageSize);
		}

	 

	 
	
}

