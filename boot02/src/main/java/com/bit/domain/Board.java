package com.bit.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//엔티티 클래스 설계
@Getter
@Setter
@ToString
@Entity
@Table(name="tbl_boards")	//테이블명, 지정x시 클래스명과 동일한 명으로 만들어짐
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//디폴트,특정 DB에 맞게끔 자동증가
	private Long bno;
	private String title;
	private String writer;
	private String content;
	
	@CreationTimestamp	//게시물 작성시간
	private Timestamp regdate;
	@CreationTimestamp	//게시물 최종시간
	private Timestamp updatedate;
	
	
}
