package com.examly.springapp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.examly.springapp.model.UserModel;


@Repository
public interface UserRepository extends CrudRepository<UserModel, String>{
	public Optional<UserModel> findByEmail(String Email);
	
}
