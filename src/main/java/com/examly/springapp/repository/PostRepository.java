package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.PostModel;

@Repository
public interface PostRepository extends JpaRepository<PostModel, String> {

}
