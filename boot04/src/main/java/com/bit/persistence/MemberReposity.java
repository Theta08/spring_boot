package com.bit.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bit.domain.Member;

public interface MemberReposity extends CrudRepository<Member, String>{

	@Query("SELECT m.uid, count(p) FROM Member m LEFT OUTER JOIN Profile p "+
			" ON m.uid = p.member WHERE m.uid = ?1 GROUP BY m")
	public List<Object[]> getMemberWithProfileCount(String uid);
	
	@Query("SELECT m, p FROM Member m LEFT OUTER JOIN Profile p " + 
			" ON m.uid = p.member WHERE m.uid = ?1 AND p.current = true")
	/*
	 * SELECT m.*, p.* FROM tbl_Members m LEFT OUTER JOIN tbl_Profile p 
		ON m.uid = p.member_uid WHERE m.uid = 'user1' AND p.current = true
	 * */
	public List<Object[]> getMemberWithProfile(String uid);
}
