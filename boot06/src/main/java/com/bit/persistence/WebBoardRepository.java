package com.bit.persistence;

import org.springframework.data.repository.CrudRepository;

import com.bit.domain.WebBoard;

public interface WebBoardRepository extends CrudRepository<WebBoard, Long>{
	
	

}
