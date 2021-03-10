package com.cos.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

	@GetMapping({"","/"})
	public String home() {
		return "index";
	}
	
	@GetMapping("/user")
	public @ResponseBody String hello() { // @Controller + @ResponseBody = @RestController
		return "User";
	}
}
