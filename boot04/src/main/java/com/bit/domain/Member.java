package com.bit.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity @Table(name="tbl_members")
@EqualsAndHashCode(of="uid")
public class Member {

	@Id //PK 설정
	private String uid;
	private String upw;
	private String uname;
}
