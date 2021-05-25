package com.bit.persistence;


import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

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
	
	//JPQL(java Persistence Query Language)
	@Query("SELECT b FROM Board b WHERE b.title like %?1% and b.bno > 0 ORDER BY b.bno desc")
	public List<Board> findByTitle2(String title);
//-----------------오류-----------	
	@Query("SELECT board.bno, board.title, board.writer, board.regdate "
			+ " FROM Board board WHERE board.title like %?1% and board.bno > 0 order by board.bno desc")
	public List<Object[]> findByTitle3(String title);
			
	@Query("SELECT board FROM Board board WHERE "
			+ "board.bno > 0 ORDER BY board.bno DESC")
	public List<Board> findByPage(Pageable pageable);
			
	//@Query : DB 종속적 <- 남용X <- JPA
	@Query("SELECT b FROM Board b WHERE "
			+ "b.bno > 0 and b.content LIKE %:content% "
			+ "ORDER BY b.bno DESC")
	public List<Board> findByContent(@Param("content") String content);
}
