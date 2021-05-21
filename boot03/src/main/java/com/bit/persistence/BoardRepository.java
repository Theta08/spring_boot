package com.bit.persistence;


import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.bit.domain.Board;

public interface BoardRepository extends CrudRepository<Board, Long>{
	
	//프레임 워크 이용
	//findBy~
	//title 조건으로 검색
	//where title=?
	public List<Board> findByTitle(String title);
	
	//writer 조건으로 검색
	//where write=?
	public Collection<Board> findByWriter(String writer);
	
	//writer의 like 구문 처리
	public List<Board> findByWriterContaining(String writer);
	
	//title에 '7'이 들어가 있는 레코드 검색
	public List<Board> findByTitleContaining(String title);
	//public List<Board> findByTitleLike(String title);
	
	//content에 '9채우기'로 끝나는 레코드 검색
	public List<Board> findByContentEndingWith(String content);
	
	//or조건의 처리 ? title like '%title%' or content like '%content%' ???
	public List<Board> findByTitleContainingOrContentContaining(String title,String content);
	
	//title like '%title%' and bno>
	public List<Board> findByTitleContainingAndBnoGreaterThan(String title,Long bno);
	
	//bno> ? ORDER BY bno desc
	public List<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno);
	
	//bno>?order boy bno desc limit ?,?
	public List<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno,Pageable paging);
}
