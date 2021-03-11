package com.cos.blog.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.blog.config.auth.PrincipalDetails;
import com.cos.blog.domain.post.Post;
import com.cos.blog.service.PostService;
import com.cos.blog.web.post.dto.PostSaveReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PostController {

	private final PostService postService;
	
	@GetMapping("/") //메인페이지
	public String findAll(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size=3) Pageable pageable) { // id로 정렬, 내림차순, 5개씩,  Pageable 도메인꺼임
	    
		Page<Post> posts = postService.전체찾기(pageable);
			
		model.addAttribute("posts",posts); // 모델을 제공해줘서 모델에 담으면 된다. 그러면 값을 담고 리스트 페이지로 간다.//모델에 JSP 에서 리퀘스트디스패쳐에 담고 forward한거랑 같다 
		return "post/list";
	}
	
	@GetMapping("/post/saveForm")
	public String saveForm() {
		return "post/saveForm";
		
	}
	
	@PostMapping("/post")
	public String save(PostSaveReqDto postSaveReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		Post post = postSaveReqDto.toEntity(); // 영속화 아님
		post.setUser(principalDetails.getUser()); // 유저 정보 넣기
		Post postEntity = postService.글쓰기(post);
		
		if(postEntity == null) {
			return "post/saveForm";
		}else {
			return "redirect:/";
		}
	}
	
//	@PostMapping("post")
//	public String save(PostSaveReqDto postSaveReqDto) {
//		Post postEntity = postService.글저장(postSaveReqDto.toEntity());
//		
//		if (postEntity == null) {
//			return "post/saveForm";
//		}
//		
//		return "redirect:/post";
//	}
}
