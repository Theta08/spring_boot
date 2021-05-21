package com.bit;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bit.domain.Board;
import com.bit.persistence.BoardRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class Boot03ApplicationTests {

	@Autowired
	private BoardRepository repo;
	
	@Test
	public void testCreate() {
		System.out.println("테이블 생성 확인");
	}
	
	//더비 data 200개 입력
	@Test
	public void testInsert200() {
		for (int i = 0; i < 200; i++) {
			Board board=new Board();
			board.setTitle("제목.."+i);
			board.setContent("내용.."+i+"체우기");
			board.setWriter("user0"+(i%10));
			
			repo.save(board);
		}
	}
	
	@Test
	public void testByTitle() {
		repo.findByTitle("제목..155").forEach(board -> System.out.println(board));	
	}
	
	@Test
	public void testByWriter() {
		Collection<Board> results=repo.findByWriter("user00");
		results.forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testByWriterContaining() {
		List<Board> result =repo.findByWriterContaining("05");
		result.forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testByTitleContaining() {
		List<Board> result=repo.findByTitleContaining("7");
		result.forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testByContentEndingWith() {
		List<Board> result=repo.findByContentEndingWith("9체우기");
		result.forEach(board-> System.out.println(board));
	}
	
	@Test
	public void testTitleOrContain() {
		List<Board> result=repo.findByTitleContainingOrContentContaining("77", "55");
		result.forEach(board-> System.out.println(board));
	}
	
	@Test
	public void testTitleAndBno() {
		List<Board> result=repo.findByTitleContainingAndBnoGreaterThan("5", 90L);
		result.forEach(board-> System.out.println(board));
	}
	
	@Test
	public void testBnoOrderBy() {
		List<Board> result=repo.findByBnoGreaterThanOrderByBnoDesc(90L);
		result.forEach(board-> System.out.println(board));
	}
	
	@Test
	public void OrderByPaging() {
		Pageable paing=PageRequest.of(1, 10);
		List<Board> result=repo.findByBnoGreaterThanOrderByBnoDesc(0L,paing);
		result.forEach(board-> System.out.println(board));
	}

}
