package com.example.demo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class HelloLombok2 {
	//객체를 호출 해서 사용 스프링부트가 getters setters 를 자동 생성해준다.
	private final String hello;
	private final int lombok;
	
	public static void main(String[] args) {
		HelloLombok2 helloLombok = new HelloLombok2("헬로", 5);	//@RequiredArgsConstructor(어노테이션)을 넣어서 가능 
		System.out.println(helloLombok.getHello());	
		System.out.println(helloLombok.getLombok()); 
	}
}
