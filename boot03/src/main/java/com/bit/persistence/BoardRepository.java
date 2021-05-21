package com.bit.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bit.domain.Board;

public interface BoardRepository extends CrudRepository<Board, Long>{

	//title 조건으로 검색
	public List<Board> findBoardByTitle(String title);
	
}
