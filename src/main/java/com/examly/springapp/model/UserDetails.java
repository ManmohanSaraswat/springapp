package com.examly.springapp.model;

import javax.persistence.Column;

public class UserDetails {

	
	
	
	@Column(nullable = true, columnDefinition="bigint(10) DEFAULT 0")
    private Long posts;
    
    @Column(nullable = true, columnDefinition="bigint(10)")
    private Long followers;
    
    @Column(nullable = true, columnDefinition="bigint(10) DEFAULT 0")
    private Long following;
    
    @Column(nullable = true, columnDefinition="BOOLEAN DEFAULT false")
    private boolean active;
}
