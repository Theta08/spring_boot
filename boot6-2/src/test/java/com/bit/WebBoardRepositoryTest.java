package com.bit;
//back data 입력

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bit.domain.WebBoard;
import com.bit.persistence.WebBoardRepository;

import lombok.extern.java.Log;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Log
@Commit
//수정
public class WebBoardRepositoryTest {

	@Autowired
	WebBoardRepository repo;
	
	
	@Test
	public void insertBoardDummies() {
		IntStream.range(0, 300).forEach(i->{
			WebBoard board=new WebBoard();
			board.setTitle("Sample Board Title"+i);
			board.setContent("Content Sample..."+i+" of Board");
			board.setWriter("user0"+(i%10));
			repo.save(board);
		});
	}
	
	@Test
	public void testList1() {
		Pageable pageable= PageRequest.of(10,10,Direction.DESC,"bno");
		Page<WebBoard> result=repo.findAll(repo.makePredicate(null, null), pageable);
		log.info("Page:"+result.getPageable());
		log.info("---------------");
		log.info("PageNumber:"+result.getPageable().getPageNumber());
		log.info("TotalPage:"+result.getTotalPages());
		log.info(""+result.getPageable());
		result.getContent().forEach(board->log.info(""+board));
	}
	
	@Test
	public void testList2() {
		Pageable pageable= PageRequest.of(10,10,Direction.DESC,"bno");
		Page<WebBoard> result=repo.findAll(repo.makePredicate("T","10"), pageable);
		log.info("PAGE:"+result.getPageable());
		log.info("-------------------");
		result.getContent().forEach(board->log.info(""+board));
	}
}
