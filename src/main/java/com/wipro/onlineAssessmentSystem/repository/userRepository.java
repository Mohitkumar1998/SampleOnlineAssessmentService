package com.wipro.onlineAssessmentSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wipro.onlineAssessmentSystem.entity.*;

public interface userRepository extends JpaRepository<user, String> {
	
}
