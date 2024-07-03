package com.example.demo.question;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface QuestionRepository extends JpaRepository<Question, Integer> {
	Question findBySubject(String subject);
	Question findBySubjectAndContent(String subject, String content);	
	List<Question> findBySubjectLike(String subject);	
	//Pageable 객체를 입력받아 Page<Question>타입 객체를 리턴하는 findAll메서드 생성
	Page<Question> findAll(Pageable pageable);

}

