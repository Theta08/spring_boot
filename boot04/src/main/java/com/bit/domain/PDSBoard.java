package com.bit.domain;

import lombok.Setter;
import lombok.ToString;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;

@Getter @Setter
@ToString(exclude = "files")
@Entity //테이블 만들기
@Table(name="tbl_pds")
public class PDSBoard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pid;
	private String pname;
	private String pwriter;
	
	@OneToMany (cascade = CascadeType.ALL)
	@JoinColumn(name="pdsno")
	private List<PDSFile> files;
}
