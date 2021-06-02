package com.bit;



import java.util.Optional;

import javax.transaction.Transactional;


import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bit.domain.Member;
import com.bit.domain.MemberRole;
import com.bit.persitence.MemberRepository;

import lombok.extern.java.Log;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Log @Commit
public class MemberTests {

	@Autowired
	private MemberRepository repo;
	
	@Transactional
	@Test
	public void testInsert() {
		for (int i = 0; i <= 100; i++) {
			Member member=new Member();
			member.setUid("user"+i);
			member.setUpw("1234");
			member.setUname("사용자"+i);
			MemberRole role=new MemberRole();
			if(i<=80) {
				role.setRoleName("BASIC");
			}else if(i<=90) {
				role.setRoleName("MANAGER");
			}else {
				role.setRoleName("ADMIN");
			}
			member.setRoles(Arrays.asList(role));
			repo.save(member);
		
		}
	}
	
	@Test
	public void testRead() {
		Optional<Member> result=repo.findById("user77");
		result.ifPresent(member-> log.info("member:"+member));
	}
	
}
