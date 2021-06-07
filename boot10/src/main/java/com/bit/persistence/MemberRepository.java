package com.bit.persistence;

import org.springframework.data.repository.CrudRepository;

import com.bit.domain.Member;

public interface MemberRepository 
	extends CrudRepository<Member, String>{

}
