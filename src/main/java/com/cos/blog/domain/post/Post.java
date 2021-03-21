package com.cos.blog.domain.post;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.cos.blog.domain.reply.Reply;
import com.cos.blog.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	
	// 양방향 매핑
	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE) // 한게시물에 여러 댓글, post는 변수명이다. 리플리의 / LAZY는 처음에 리스트 뿌릴때는 안가져오고 상세보기를 할 때 가져오는게 좋다. / 게시글이 삭제되면 게시글이 관련된 댓글을 다 날린다. 네이버 페이스북등은 안날리고 대용량데이터로 가지고있다./
	@JsonIgnoreProperties({"post"})
	@OrderBy("id desc")
	private List<Reply> replys;

	
	@CreationTimestamp
	private Timestamp createDate;
	
	/*
	 * String createDate = df.format(((Post) posts).getCreateDate()) ;
	 * System.out.println("시간3" + df.format(createDate));
	 * model.addAttribute("createDate",createDate);
	 */
}
