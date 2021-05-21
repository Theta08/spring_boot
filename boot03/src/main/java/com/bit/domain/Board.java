package com.bit.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
//@Table 선언x 클래스명은 테이블 생성
@Table(name="tbl_boards")
public class Board {

	@Id
	//디폴트, 특정DB에 맞게끔 자동증가 시퀀스 증가?
	//@GeneratedValue(strategy = GenerationType.AUTO) 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long bno;
	private String title;
	private String writer;
	private String content;
	
	@CreationTimestamp //게시물 작성시간
	private Timestamp regdate;
	@UpdateTimestamp //게시물 최종 수정시간
	private Timestamp updatedate;
}
