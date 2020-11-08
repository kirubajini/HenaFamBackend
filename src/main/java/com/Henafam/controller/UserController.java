package com.Henafam.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Henafam.model.ERole;
import com.Henafam.model.Role;
import com.Henafam.model.User;
import com.Henafam.repository.RoleRepository;
import com.Henafam.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	RoleRepository roleRepository;

	@GetMapping
	public ResponseEntity<List<User>> getAllUsers(){
	return userService.getAllUsers();

	}

	@GetMapping("/{id}")
	public  ResponseEntity <User> getUserById(@PathVariable String id) {
	return userService.getUserById(id);
	}


	@PutMapping("/{id}")
	public ResponseEntity <User> updateTutorial(@RequestBody User user, @PathVariable String id){
	System.out.println("hiiiiii");
	Set<String> strRoles = user.getUpdateroles();
	Set<Role> roles = new HashSet<>();

	if (strRoles == null) {
	Role userRole = roleRepository.findByName(ERole.ROLE_USER)
	.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	roles.add(userRole);
	} else {
	strRoles.forEach(role -> {
	switch (role) {
	case "admin":
	Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
	.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	roles.add(adminRole);

	break;
	case "user":
	Role userRole = roleRepository.findByName(ERole.ROLE_USER)
	.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	roles.add(userRole);

	break;
	default:
	Role userRoles = roleRepository.findByName(ERole.ROLE_USER)
	.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	roles.add(userRoles);
	}
	});
	}

	user.setRoles(roles);
	

	return userService.updateUserById(user, id);
	}



	@DeleteMapping("/{id}")
	public ResponseEntity<User>  deleteUser(@PathVariable String id) {
	return userService.deleteUserById(id);
	}
	@GetMapping("/page")
	  public ResponseEntity<Map<String, Object>> getAllUserInPage(
	   @RequestParam(name = "pageNo", defaultValue = "0") int pageNo,
	   @RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
	   @RequestParam(name = "sortBy", defaultValue = "id") String sortBy) {
	return userService.getAllUserInPage(pageNo, pageSize, sortBy);
	}

	 
	 @GetMapping(value = "/page/serachedPages")
		public ResponseEntity<Map<String,Object>> searchUser(
				@RequestParam(name = "searched",defaultValue = "null") String searched,
				@RequestParam(name = "pageNo",defaultValue = "0") int pageNo,
				@RequestParam(name = "pageSize",defaultValue = "5") int pageSize
				){
			return userService.searchUser(searched,pageNo,pageSize);
		}
        
      @PutMapping("/email/{id}")
      public ResponseEntity<User>updateEmail(@PathVariable String id,
    		  @RequestParam(name="email") String email
    		  ){
    	  return userService.updateEmail(id,email);
      }

	@PutMapping("/name/{id}")
	public ResponseEntity<User> updateFullname(@PathVariable String id,
			@RequestParam(name = "name") String  name
			){
		return userService.updateFullname(id,name);
	}
	
	@PutMapping("/phonenumber/{id}")
	public ResponseEntity<User> updatePhoneNumber(@PathVariable String id,
			@RequestParam(name = "phonenumber") String  phonenumber
			){
		return userService.updatePhoneNumber(id,phonenumber);
	}
}
	


