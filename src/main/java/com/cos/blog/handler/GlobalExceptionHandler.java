package com.cos.blog.handler;

import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.web.dto.CMRespDto;

@RestController // 데이터를 리턴할 수 있음
@ControllerAdvice // 모든 인셉션을 낚아챔
public class GlobalExceptionHandler {

	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public CMRespDto<?> dataIntegrityViolation(Exception e) {
		return new CMRespDto<>(-1, e.getMessage(), null);
	}

	// 그 중 IllegalArgumentException이 발생하면 해당 함수가 실행됨
	@ExceptionHandler(value = IllegalArgumentException.class)
	public String illegalArgument(Exception e) {
		return "<h1>"+e.getMessage()+"<h1>";
	}

	@ExceptionHandler(value = EmptyResultDataAccessException.class) // delete id찾기 실패시 인셉션
	public CMRespDto<?> emptyResultDataAccess(Exception e) {
		return new CMRespDto<>(-1, e.getMessage(), null);
	}

	@ExceptionHandler(value = NoSuchElementException.class) //
	public CMRespDto<?> NoSuchElementException(Exception e) {
		return new CMRespDto<>(-1, e.getMessage(), null);
	}

	/*
	 * @ExceptionHandler(value = MyAuthenticationException.class) public
	 * CMRespDto<?> myAuthentication(Exception e){ return new CMRespDto<>(-1,
	 * "로그인 후 사용해주세요",null);
	 * 
	 * }
	 */

}
