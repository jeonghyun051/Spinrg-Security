package com.cos.blog.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.domain.user.User;
import com.cos.blog.domain.user.UserRepository;
import com.cos.blog.web.user.dto.UserUpdateReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional
	public User 회원수정(int id, UserUpdateReqDto userUpdateReqDto) {
		User userEntity = userRepository.findById(id).get(); // 1차 캐시 사용자가 리스펀스 응답이 끝나면 사라진다. 사라지기 직전에 영속성컨텍스트에 있는 오브젝트들이 1차 캐시메모리에 있는지 확인한다.
		String encPassword = bCryptPasswordEncoder.encode(userUpdateReqDto.getPassword());
		
		userEntity.setPassword(encPassword);
		userEntity.setEmail(userUpdateReqDto.getEmail());
		return userEntity;
	} // 더티체킹
}