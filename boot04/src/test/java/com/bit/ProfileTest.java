package com.bit;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bit.domain.Member;
import com.bit.domain.Profile;
import com.bit.persistence.MemberReposity;
import com.bit.persistence.ProfileReposity;

import lombok.extern.java.Log;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Log
@Commit
public class ProfileTest {

	@Autowired
	MemberReposity mRepo;
	@Autowired
	ProfileReposity pRepo;
	
	@Test
	public void testInsertMember() {
		IntStream.range(1, 101).forEach(i->{
			Member member =new Member();
			member.setUid("user"+i);
			member.setUpw("pw"+i);
			member.setUname("사용자"+i);
			mRepo.save(member);
		});
	}
	
	@Test
	public void testInsertPofile() {
		Member member=new Member();
		member.setUid("user1");
		for(int i=1;i<5;i++) {
			Profile profile  =new Profile();
			profile.setFname("face"+i+".jpg");
			if(i==1)
				profile.setCurrent(true);
			profile.setMember(member);
			pRepo.save(profile);
		}
	}
	
	@Test
	public void testFetchJoin1() {
		List<Object[]> result=mRepo.getMemberWithProfileCount("user1");
		result.forEach(arr->System.out.println(Arrays.toString(arr)));
	}
	
	@Test
	public void testFetchJoin2() {
		List<Object[]> result=mRepo.getMemberWithProfile("user1");
		result.forEach(arry->System.out.println(Arrays.toString(arry)));
	}
}
