package com.quiz.quizapp.Dao;

import com.quiz.quizapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;
import java.util.List;

@Repository
@EnableJpaRepositories
public interface questionrepo extends JpaRepository<Question, Integer> {
 List<Question> findBycategory(String category);
 @Query(value ="SELECT * FROM question q where q.category=:category ORDER BY RANDOM() LIMIT :num",nativeQuery = true)
 List<Question> findRandomQuestions(String category,int num);


}
