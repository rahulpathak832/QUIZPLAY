package com.quizeplay.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quizeplay.entities.MainQuiz_tbl;

/**
 * @author rahul.pathak
 *
 */

@Repository
public interface MainQuizRepository extends JpaRepository<MainQuiz_tbl, Long> {
	
	List<MainQuiz_tbl> findAll();
	
	List<MainQuizProjection> findBy();
	
}
