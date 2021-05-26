package com.bit.persistence;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bit.domain.FreeBoard;

public interface FreeBordReposity extends CrudRepository<FreeBoard,Long>{
	

	public List<FreeBoard> findByBnoGreaterThan(Long bno,Pageable pageable);
	
	@Query("SELECT b.bno, b.title, count(r) " +
			" FROM FreeBoard b LEFT OUTER JOIN b.replies r " + 
			" WHERE b.bno > 0 group by b ")
	public List<Object[]> getPage(Pageable pageable);
}
