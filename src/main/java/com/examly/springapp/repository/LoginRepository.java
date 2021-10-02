package com.examly.springapp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.LoginModel;


@Repository
public interface LoginRepository extends CrudRepository<LoginModel, String>{
	public Optional<LoginModel> findById(String Email);
}
