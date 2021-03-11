package com.cos.blog.domain.post;

import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.cos.blog.domain.user.RoleType;
import com.cos.blog.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // 시퀀스, auto_increment
	
	@Column(nullable = false, length = 100) // nullable = false 무조건 값이 있어야함
	private String title;
	
	@Lob //대용량 데이터
	private String content;
	
	@ColumnDefault("0") // default값 0이 들어간다.
	private int count; //조회수 
	
	@ManyToOne //post가 many
	@JoinColumn(name = "userId") //컬럼명 적기
	private User user;
	
	@CreationTimestamp
	private Timestamp createDate;
}
