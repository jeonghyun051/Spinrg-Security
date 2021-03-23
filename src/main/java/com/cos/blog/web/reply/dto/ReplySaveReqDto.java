package com.cos.blog.web.reply.dto;


import com.cos.blog.domain.post.Post;
import com.cos.blog.domain.reply.Reply;
import com.cos.blog.domain.user.User;

import lombok.Data;

@Data
public class ReplySaveReqDto {
	private String content; 
	private Integer userId;
	private Integer postId;
	
	public Reply toEntity() {
		return Reply.builder()
					.content(content)
					.post(Post.builder().id(postId).build())
					.user(User.builder().id(userId).build())
					.build();
	}
}