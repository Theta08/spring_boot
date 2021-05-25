package com.bit.domain;
//답변 Entity

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Indexed;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter 
@Entity

@ToString(exclude = "board")
@Table(name="tbl_free_replies")
public class FreeBoardReply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rno;
	private String reply;
	private String replyer;
	
	@CreationTimestamp
	Timestamp replydate;
	@UpdateTimestamp
	Timestamp updatedate;
	
	@ManyToOne
	private FreeBoard board;
}
