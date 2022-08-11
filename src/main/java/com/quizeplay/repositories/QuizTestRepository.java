package com.quizeplay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quizeplay.entities.QuizTest;

@Repository
public interface QuizTestRepository extends JpaRepository<QuizTest, Long> {
	
	boolean existsByAskedQuestionAndUserid(String question, String userid);
	
	long countByCorrects(String correct);
	
	long countByUserid(String userid);
	
	@Query(value="SELECT count(*) FROM QUIZ_TEST  where userid=:userid and correct_orwrong='1'",nativeQuery = true)
	long countBycorrectAnswers(@Param("userid") String userid);

}
