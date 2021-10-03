package com.examly.springapp.service;

import com.examly.springapp.model.LoginModel;
import com.examly.springapp.model.PostModel;
import com.examly.springapp.repository.PostRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PostService implements UserDetailsService{

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
@Override
public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
	Optional<PostModel> check = postRepo.findById(id);
	if(check != null) {
		return new User(check.get().getImageId(), check.get().getImageName(), new ArrayList<>());
	}
	return null;
}
}