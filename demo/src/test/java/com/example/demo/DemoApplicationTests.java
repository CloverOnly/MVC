package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.question.QuestionService;




@SpringBootTest
class DemoApplicationTests {
	
	@Autowired
	private QuestionService questionService;
	
	
	@Test
	void conteOptional() {
		
	}

}
