package com.PineGap.Project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {

	// Selects from user's role name from database
	@Query("SELECT r FROM Role r WHERE r.name = ?1")
	public Role findByName(String name);
}
