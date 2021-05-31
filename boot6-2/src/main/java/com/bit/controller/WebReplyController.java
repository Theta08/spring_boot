package com.bit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	//웹 전송 방식:GET,POST
	/* ajax 이용
	 *댓글 추가:POST-> /replies/게시물 번호 
	 *댓글 삭제:DELETE-> /replies/게시물 번호/댓글 번호 ex)replies/303/5
	 *댓글 수정:PUT-> replies/게시물 번호
	 *댓글 리스트:GET-> replies/게시물 번호
	 */
	
	@Autowired
	private WebReplyRepository repo;
	
	//JSON 방식 확인을위해 크롬스토어에 yarc검색 실행
	// http://localhost/replies/300   post
	/*
	 * { "replyText":"댓글 샘플", "replyer":"홍길동" }
	 */
	
	//댓글 추가
	@PostMapping("/{bno}")	//게시물 번호
	public ResponseEntity<List<WebReply>> addReply(@PathVariable("bno")Long bno,
			//@RequestBody(전송타입 json ): JSON 형태 -> 객체 타입 변환
			@RequestBody WebReply reply){
		log.info("addReply............");
		log.info("bno:"+bno);
		log.info("reply"+reply);
		
		WebBoard board=new WebBoard();
		board.setBno(bno);
		reply.setBoard(board);
		repo.save(reply);	//뎃글 저장
		
		return new ResponseEntity<>(getListByBoard(board),HttpStatus.CREATED);
	}
	
	//뎃글 리스트
	//getListByBoard(WebBoard board) ->  List<WebReply  타입
	private List<WebReply> getListByBoard(WebBoard board) throws RuntimeException{
		log.info("getListByBoard.."+board);
		return repo.getRepliesOfBoard(board);
	}
}
