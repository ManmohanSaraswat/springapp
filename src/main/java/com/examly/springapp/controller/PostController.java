package com.examly.springapp.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.examly.springapp.model.PostModel;
import com.examly.springapp.response.ResponseFile;
import com.examly.springapp.service.PostService;
@CrossOrigin
@RestController
public class PostController{
	@Value("${uploadDir}")
	private String uploadFolder;
	@Autowired
	private PostService postService;

	private final Logger log = LoggerFactory.getLogger(this.getClass());


	@RequestMapping(value="/save", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> createPost(
			HttpServletRequest request
			,@RequestParam("file") MultipartFile file) {
		try {
			String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
			log.info("uploadDirectory:: " + uploadDirectory);
			String fileName = file.getOriginalFilename();
			String filePath = Paths.get(uploadDirectory, fileName).toString();
			log.info("FileName: " + file.getOriginalFilename());
			if (fileName == null || fileName.contains("..")) {
				return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName, HttpStatus.BAD_REQUEST);
			}
			try {
				File dir = new File(uploadDirectory);
				if (!dir.exists()) {
					log.info("Folder Created");
					dir.mkdirs();
				}
				// Save the file locally
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				stream.write(file.getBytes());
				stream.close();
			} catch (Exception e) {
				log.info("in catch");
				e.printStackTrace();
			}
			postService.store(file);
			return new ResponseEntity<>("Product Saved With File - " + fileName, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception: " + e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/files/{id}")
	  public ResponseEntity<byte[]> getFile(@PathVariable String id) {
	    PostModel fileDB = postService.getFile(id);

	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getImageName() + "\"")
	        .body(fileDB.getImage());
	  }
	

	@GetMapping("/post/postdetails")
	String showProductDetails(@RequestParam("id") String imageId) {
		try {
			PostModel post = postService.getFile(imageId);
				if (post != null) {
					return post.toString();
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imageId;	
	}
	@GetMapping("/show")
	  public ResponseEntity<List<ResponseFile>> getListFiles() {
	    List<ResponseFile> files = postService.getAllFiles().map(dbFile -> {
	      String fileDownloadUri = ServletUriComponentsBuilder
	          .fromCurrentContextPath()
	          .path("/files/")
	          .path(dbFile.getImageId())
	          .toUriString();

	      return new ResponseFile(
	          dbFile.getImageName(),
	          fileDownloadUri, 
	          dbFile.getImageTag(),
	          dbFile.getImageId());
	    }).collect(Collectors.toList());

	    return ResponseEntity.status(HttpStatus.OK).body(files);
	  }
}