package com.cos.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.blog.service.AuthService;
import com.cos.blog.web.auth.dto.AuthJoinReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AuthController { // 인증을 위한 컨트롤러
	
	private final AuthService authService;

	// 주소 : /user /post(인증이 안되어있을 때) /loginForm(인증과는 상관 없음)
	@GetMapping("/loginForm")
	public String loginForm() { // 로그인 화면 이동
		return "auth/loginForm";
	}

	@GetMapping("/joinForm")
	public String joinForm() { // 회원가입 화면 이동
		return "auth/joinForm";
	}

	@PostMapping("/join") // 회원가입
	public String join(AuthJoinReqDto authJoinReqDto) {
		authService.회원가입(authJoinReqDto.toEntity());
		return "redirect:/loginForm"; 
	}
}
