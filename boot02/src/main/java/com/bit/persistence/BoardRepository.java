package com.bit.persistence;

import org.springframework.data.repository.CrudRepository;

import com.bit.domain.Board;

//인터페이스도 상속이 가능하다. 그런데 다중상속이 가능.
//클래스는 다중상속 불가능
public interface BoardRepository extends CrudRepository<Board, Long> {
	
}








