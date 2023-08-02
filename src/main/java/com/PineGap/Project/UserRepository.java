package com.PineGap.Project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
	
	// Selects from user's email from database
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	public User findByEmail(String email);
}
