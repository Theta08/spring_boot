package com.bit.domain;
//게시판 Entity

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter 
@ToString(exclude = "replies")
@Entity
@Table(name="tbl_freeboards")
public class FreeBoard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bno;
	private String title;
	private String writer;
	private String content;
	
	@CreationTimestamp //게시물 작성시간
	Timestamp regdate;
	@UpdateTimestamp //게시물 최종 수정시간
	Timestamp updatedate;
	

	@OneToMany(mappedBy = "board" ,cascade = CascadeType.ALL)
	private List<FreeBoardReply> replies;
}
