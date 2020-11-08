package com.Henafam.repository;

import org.springframework.stereotype.Repository;

import com.Henafam.model.ERole;
import com.Henafam.model.Role;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;



@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
	Optional<Role> findByName(ERole name);

}
