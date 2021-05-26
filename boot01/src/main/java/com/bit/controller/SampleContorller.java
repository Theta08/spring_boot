package com.bit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bit.domain.SampleVO;

@RestController		//json으로 만듬?
public class SampleContorller {

	// 따로 html 만들필요x 자동적으로 웹피이지가 생성
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello my World 한글 테스트";
	}
	
	@GetMapping("/sample")
	public SampleVO maakeSample() {
		SampleVO vo=new SampleVO();
		vo.setVal1("변수1");
		vo.setVal2("변수2");
		vo.setVal3("변수3");
		
		System.out.println(vo);
		return vo;
	}
	
	//http://localhost:8080/hello  에서 실행
	
}
