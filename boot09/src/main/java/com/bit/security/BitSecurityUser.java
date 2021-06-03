package com.bit.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.bit.domain.Member;
import com.bit.domain.MemberRole;

import lombok.Getter;

@Getter
public class BitSecurityUser extends User {

	private static final String ROLE_PREFIX = "ROLE_";

	private Member member;
	
	private String memberStr;

	public BitSecurityUser(Member member) {

		super(member.getUid(), member.getUpw(), makeGrantedAuthority(member.getRoles()));

		this.member = member;

		this.memberStr = member.getUid();
	}

	private static List<GrantedAuthority> makeGrantedAuthority(List<MemberRole> roles) {

		List<GrantedAuthority> list = new ArrayList<>();

		roles.forEach(role -> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRoleName())));

		return list;
	}

}
