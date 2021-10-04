package com.examly.springapp.repository;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.UserModel;


@Repository
public interface UserRepository extends CrudRepository<UserModel, String>{
	public UserModel findByEmail(String Email);
	
}
