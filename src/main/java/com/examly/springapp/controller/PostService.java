package com.examly.springapp.controller;

import com.examly.springapp.model.PostModel;
import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PostService {

  @Autowired
  private PostRepository postRepo;

  public PostModel store(MultipartFile file) throws IOException {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    PostModel FileDB = new PostModel(fileName, file.getContentType(), file.getBytes());

    return postRepo.save(FileDB);
  }

  public PostModel getFile(String id) {
    return postRepo.findById(id).get();
  }
  
  public Stream<PostModel> getAllFiles() {
    return postRepo.findAll().stream();
  }
}