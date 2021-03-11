package com.cos.blog.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.blog.config.auth.PrincipalDetails;

@Controller
public class UserController {

//	@GetMapping({"","/"})
//	public String home() {
//		return "index";
//	}
	
	@GetMapping("/user") // @Controller + @ResponseBody = @RestController
	public @ResponseBody String findAll(@AuthenticationPrincipal PrincipalDetails principalDetails) {  //principalDetails 을 뽑아준다. 세션확일할때 사용할것
		
		System.out.println(principalDetails.getUsername());
		System.out.println(principalDetails.getUser());
//	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // 
//	    PrincipalDetails principalDetails = (PrincipalDetails) authentication.getDetails(); // 이것은 userdetails이다 
//	    System.out.println(principalDetails.getUser());
		return "User";
	}
}
