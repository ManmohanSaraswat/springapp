package com.examly.springapp.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="posts")
public class PostModel {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    private String imageId;
    
    @Column(nullable = false, length = 40)
    private String imageName;
    
    @Lob
    @Column(length = Integer.MAX_VALUE, nullable = true)
    private byte[] image;
    
    @Column(nullable = true, length = 40)
    private String imageTag;
    
    public PostModel() {
    	super();
    }
    public PostModel(String imageName, String imageTag, byte[] bs) {
    	
        	this.imageName = imageName;
        	this.imageTag = imageTag;
			this.image = bs;
		
    	
    }
	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getImageTag() {
		return imageTag;
	}

	public void setImageTag(String imageTag) {
		this.imageTag = imageTag;
	}

	@Override
	public String toString() {
		return "ImageModel [imageId=" + imageId + ", imageName=" + imageName + ", image=" + image + ", imageTag="
				+ imageTag + "]";
	}
}
