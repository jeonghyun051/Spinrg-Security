package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.cos.blog.domain.user.User;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails, OAuth2User {

	private User user;
	private Map<String, Object> attributes; // OAuth 제공자로 부터 받은 회원 정보, 모든 정보를 받기 위해 Map으로 만듬
	private boolean oAuth;
	
	public PrincipalDetails(User user) {
		this.user = user;
	}
	
	public PrincipalDetails(User user, Map<String, Object> attributes) {
		this.user = user;
		this.attributes = attributes;
		this.oAuth = true;
	}
	
	@Override
	public Map<String, Object> getAttributes() {
		
		return attributes;
	}

	@Override
	public String getName() {
		
		return "ㅁㄹ";
	}
	
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() { // 휴먼계정
		return true; // 디비에 업데이트 날짜가 있으면 user.getUpdateDate(); 등의 로직을 짜서 리턴
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
        //해당 User의 권한을 리턴하는 곳 getRole을 바로 return 할수 없음
		Collection<GrantedAuthority> collectors = new ArrayList<>(); // 자바 유틸꺼임, collectors에 권한을 넣어줘야함
		collectors.add(()-> "ROLE_"+user.getRole().toString()); // collectors 에 리턴되서 들어간다.
		//컬렉터에 GrantedAuthority타입을 넣어줘야한다.
		
		// 위랑 같음
//		collectors.add(new GrantedAuthority() {
//			
//			@Override
//			public String getAuthority() {
//				
//				return user.getRole().toString(); //collectors 에 리턴되서 들어간다
//			}
//		});
		
		return collectors;
	}
}