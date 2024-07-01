package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller 	//아래쪽 객체를 생성하는 역할
public class HelloController1 {
	@GetMapping("/hello.do")	//검색하면 아래 코드가 실행되게 됨
							//Post 는 from 처리 방식을 사용해서 실행
	@ResponseBody	//HTML body 영역에 넣어준다.
	public String hello() {
		return "Hello Spring Boot Board";
	}
}
