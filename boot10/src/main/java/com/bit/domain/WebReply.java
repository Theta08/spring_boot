package com.bit.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "tbl_webreplies")
@EqualsAndHashCode(of = "rno")
@ToString(exclude = "board")
public class WebReply {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long rno;
		private String replyText;
		private String replyer;
		@CreationTimestamp
		private Timestamp regdate;
		@UpdateTimestamp
		private Timestamp updatedate;
		
		@JsonIgnore //json 형식의 data는 무시
		@ManyToOne
		private WebBoard board;
		
}






