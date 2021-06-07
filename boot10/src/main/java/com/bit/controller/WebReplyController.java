package com.bit.controller;

import java.net.http.HttpRequest;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bit.domain.WebBoard;
import com.bit.domain.WebReply;
import com.bit.persistence.WebReplyRepository;

import lombok.extern.java.Log;

@RestController
@RequestMapping("/replies")
@Log
public class WebReplyController {
	
	//웹 전송 방식 : GET, POST
	/*
	 * 댓글 추가 : POST -> /replies/게시물 번호
	 * 댓글 삭제 : DELETE -> /replies/게시물 번호/댓글 번호 ex)replies/303/5
	 * 댓글 수정: PUT -> /replies/게시물 번호
	 * 댓글 리스트: GET -> /replies/게시물 번호
	 * */
	
	@Autowired
	private WebReplyRepository repo;
	
	//댓글 추가
	@Secured(value = {"ROLE_BASIC","ROLE_MANAGER","ROLE_ADMIN"})
	@Transactional
	@PostMapping("/{bno}") //게시물 번호
	public ResponseEntity<List<WebReply>> addReply(
			@PathVariable("bno") Long bno, 
			//@RequestBody : JSON 형태 -> 객체 타입 변환 
			@RequestBody WebReply reply){
		WebBoard board = new WebBoard();
		board.setBno(bno);
		reply.setBoard(board);
		repo.save(reply);//댓글 저장
		
		return new ResponseEntity<>(getListByBoard(board),
				HttpStatus.CREATED);
	}
	
	//댓글 리스트
	private List<WebReply> getListByBoard(WebBoard board) 
	throws RuntimeException{
		log.info("getListByBoard..." + board);
		return repo.getRepliesOfBoard(board);
	}
	
	//댓글 삭제
	@Secured(value = {"ROLE_BASIC","ROLE_MANAGER","ROLE_ADMIN"})
	@Transactional
	@DeleteMapping("/{bno}/{rno}")
	public ResponseEntity<List<WebReply>> remove(
			@PathVariable("bno") Long bno,
			@PathVariable("rno") Long rno
			){
		log.info("delete reply : " + rno);
		repo.deleteById(rno);
		WebBoard board = new WebBoard();
		board.setBno(bno);
		
		return new ResponseEntity<>(getListByBoard(board),
				HttpStatus.OK);
	}
	
	//댓글 수정 modify
	@Secured(value = {"ROLE_BASIC","ROLE_MANAGER","ROLE_ADMIN"})
	@Transactional
	@PutMapping("/{bno}")
	public ResponseEntity<List<WebReply>> modify(
			@PathVariable("bno")Long bno,
			@RequestBody WebReply reply
			){
		log.info("modify reply : "+ reply);
		//조건에 만족하는 댓글 레코드가 있다면
		repo.findById(reply.getRno()).ifPresent(orgin ->{
			orgin.setReplyText(reply.getReplyText());
			orgin.setReplyer(reply.getReplyer());
			repo.save(orgin);
		});
	
		WebBoard board = new WebBoard();
		board.setBno(bno);
		
		return new ResponseEntity<>(getListByBoard(board),
				HttpStatus.OK);
	}
	
	@GetMapping("/{bno}")
	public ResponseEntity<List<WebReply>> getReplies(
			@PathVariable("bno") Long bno){
		log.info("get all Replies.......................................");
		WebBoard board = new WebBoard();
		board.setBno(bno);
		return new ResponseEntity<>(getListByBoard(board),
				HttpStatus.OK);
	}
}























