package com.cos.blog.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetails;
import com.cos.blog.service.ReplyService;
import com.cos.blog.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ReplyController {

	private final ReplyService replyService;

	// 게시글 상세보기 (user, post, reply들)

	@DeleteMapping("/reply/{id}")
	public CMRespDto<?> deleteById(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		// 모든 컨트롤러에 삭제하기 , 수정하기는 무조건 동일인물이 로그인했는지 확인!!
		System.out.println("댓글아디랑 유저 들어왔나 "+id + principalDetails.getUser().getId());
		int result = replyService.삭제하기(id, principalDetails.getUser().getId());

		return new CMRespDto<>(result, null);

	}
}