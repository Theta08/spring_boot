package com.bit;

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

import com.bit.persistence.CustomCrudRepository;

import lombok.extern.java.Log;



@ExtendWith(SpringExtension.class)
@SpringBootTest
@Log
@Commit
public class CustomRepositoryTests {
//수정
	@Autowired
	CustomCrudRepository repo;
	
	@Test
	public void test1() {
		Pageable page = PageRequest.of(0, 10, Direction.DESC, "bno");
		String type = "t";
		String keyword = "29";
		Page<Object[]> result = repo.getCustomPage(type, keyword, page);
		log.info("" + result);
	}	
}
