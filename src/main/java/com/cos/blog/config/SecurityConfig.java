package com.cos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration // 설정, 메모리에 띄움 ioc에 등록
@EnableWebSecurity // 이제 커스터마이징한 시큐리티가 실행된다.
public class SecurityConfig extends WebSecurityConfigurerAdapter{ // 어댑터는 함수를 걸러줌, 강제성을 없애준다.
	
	//ioc에 등록만하면 AuthenticationManager가 Bcrypt로 자동 검증해줌;
	@Bean // 비밀번호 암호화 ioc에 등록
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.csrf().disable(); 
		http.authorizeRequests()
//			.antMatchers("/user","/post").authenticated() // authenticated 두개만 인증을 하겠다.
		    .antMatchers("/user","/post").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") // 둘중에 하나의 권한만 있으면 페이지 이동가능 // ROLE_ 강제성이있음 ROLE체크시
		    .antMatchers("/admin").access("hasRole('ROLE_ADMIN')")
			.anyRequest().permitAll() // 나머지는 허용해주는 것, 열리는 것 
			.and() // 여기서 끝
			.formLogin() // x-www-form-urlencoded , json으로 던지면 안된다. 결국 폼태그를 만들어야한다.
			.loginPage("/loginForm") // user, post 호출 시 로그인 페이지로 리다이렉션됨 
		    .loginProcessingUrl("/login") // /login이 들어오면 시큐리티가 낚아챈다 get방식은 안됨 post방식만 낚아챔.
		    .defaultSuccessUrl("/");
	}
}
