package com.cos.blog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.domain.reply.Reply;
import com.cos.blog.domain.reply.ReplyRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReplyService {

	private final ReplyRepository replyRepository;
	//앞단에서 버튼 보이게 안보이게만 막지말고 내부적으로 꼭 막을것, 주소로 뚫릴 수 있음
	
	@Transactional
	public int 삭제하기(int id, int userId) {
		System.out.println("서비스에서 댓글아디랑, 유저아디 들어왔냐 " + id+userId);
		Reply replyEntity = replyRepository.findById(id).get();
		if (replyEntity.getUser().getId() == userId) {
			replyRepository.deleteById(id);
			return 1;
		} else {
			return -1;
		}
	}
	
	@Transactional
	public Reply 저장하기(Reply reply) {
		System.out.println("서비스에서 리플라이" + reply);
		return replyRepository.save(reply);
	}
}

