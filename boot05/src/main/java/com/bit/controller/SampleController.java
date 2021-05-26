package com.bit.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bit.domain.MemberVO;

@Controller
public class SampleController {

	@GetMapping("/sample1")//sampel1.html 호출
	public void sample1(Model model) {
		model.addAttribute("greeting", "반갑습니다.");
	}
	
	@GetMapping("/sample2")
	public void sample2(Model model) {
		MemberVO vo= new MemberVO(123, "u00", "p00", "홍길동", new Timestamp(System.currentTimeMillis()));
		model.addAttribute("vo",vo);
	}
	@GetMapping("/sample3")
	public void sample3(Model model) {
		List<MemberVO> list=new ArrayList<MemberVO>();
		for(int i=0;i<10;i++) {
			list.add(new MemberVO(123, "u0"+i, "p0"+i, "홍길동"+i, new Timestamp(System.currentTimeMillis())));
		}
		
		model.addAttribute("list",list);
	}
	@GetMapping("/sample4")
	public void sample4(Model model) {
		List<MemberVO> list=new ArrayList<MemberVO>();
		for(int i=0;i<10;i++) {
			list.add(new MemberVO(123, "u000"+i%3, "p000"+i%3, "홍길동"+i, new Timestamp(System.currentTimeMillis())));
		}
		
		model.addAttribute("list",list);
	}
	
	@GetMapping("/sample5")
	public void sample5(Model model){
		String reuslt="SCUCESS";
		model.addAttribute("reuslt",reuslt);
	}
	
	@GetMapping("/sample6")
	public void sample6(Model model) {
		List<MemberVO> list=new ArrayList<MemberVO>();
		for(int i=0;i<10;i++) {
			list.add(new MemberVO(123, "u000"+i%3, "p000"+i%3, "홍길동"+i, new Timestamp(System.currentTimeMillis())));
		}
		model.addAttribute("list",list);
		String reuslt="SCUCESS";
		model.addAttribute("reuslt",reuslt);
	}
	
	@GetMapping("/sample7")
	public void sample7(Model model) {
		model.addAttribute("now",new Date());
		model.addAttribute("price",1234567);
		model.addAttribute("title","This ins a just sample");
		model.addAttribute("options",Arrays.asList("AAA","BBB","CcC","dDd"));
	}
	
	@GetMapping("/sample8")
	public void sample8(Model model) {
		
	}
	@GetMapping("/sample9")
	public void sample9(Model model) {
		
	}
	
}