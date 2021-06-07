package com.bit.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
/*
 * 사용자 정의 Query - 동적으로 JPQL 처리
1.사용자 정의 인터페이스
(CustomWebBoard.java)
2.엔티티의 Repository 인터페이스 설계
(CustomCrudRepository.java)
3.엔티티를 구현하는 클래스 설계
4.Junit 테스트
 * */
//사용자 정의 인터페이스
public interface CustomWebBoard {

	public Page<Object[]> getCustomPage(String type, String keyword,
			Pageable page);
}

