package com.spring.memo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity // 클래스를 엔티티로 선언
@SequenceGenerator(name = "mem_seq_gen", sequenceName = "mem_seq", allocationSize = 1)
public class Memo {
	
	//@SequenceGenerator(name = "mem_seq_gen", sequenceName = "mem_seq", allocationSize = 1)
	//name : 임의로 이름 설정(필수), sequenceName : .nextval찍을 때 쓰는 그 이름, allocationSize : 증가폭

	// GenerationType : 1) AUTO : JPA 구현체가 자동으로 구현, 2) IDENTITY : 기본키 생성을 데이터베이스에 위임
	//					3) SEQUENCE : SequenceGenerator 등록 후 사용
	//					4) TABLE : 키 생성용 테이블 사용 @TableGenerator 사용
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mem_seq_gen")
	@Id // pk 지정
	private Long mno;

	@Column(length = 200, nullable = false) // 컬럼 옵션 부여
	private String memoText; // 이거 자동으로 스네이크 케이스로 바뀜

}
