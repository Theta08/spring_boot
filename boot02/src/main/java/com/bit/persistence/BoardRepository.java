package com.bit.persistence;

import org.springframework.data.repository.CrudRepository;

import com.bit.domain.Board;

//인터페이스 상속 가능.but 다중상속 가능 
//class는 다중상속 불가능
public interface BoardRepository extends CrudRepository<Board, Long>{

}
