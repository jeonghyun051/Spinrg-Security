package com.cos.blog.web;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.blog.config.auth.PrincipalDetails;
import com.cos.blog.domain.post.Post;
import com.cos.blog.service.PostService;
import com.cos.blog.web.dto.CMRespDto;
import com.cos.blog.web.post.dto.PostSaveReqDto;
import com.cos.blog.web.post.dto.PostSearchReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PostController {

	private final PostService postService;
	
	//게시물검색
	@GetMapping("/post/search")
	public String search(PostSearchReqDto postSearchReqDto ,Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size=3) Pageable pageable) {

		Page<Post> posts = postService.검색하기(postSearchReqDto,pageable);
		model.addAttribute("posts",posts);
		model.addAttribute("title",postSearchReqDto.getTitle());
		
		System.out.println("뽑은 검색리스트" + posts);
		return "post/searchList"; // ViewResolver가 작동해서 jsp파일을 찾아줌.
	}
	
	@GetMapping("/") //메인페이지
	public String findAll(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size=3) Pageable pageable, @AuthenticationPrincipal PrincipalDetails principalDetails) { // id로 정렬, 내림차순, 5개씩,  Pageable 도메인꺼임
	    
		System.out.println("누구로 로그인 됐을까");
		System.out.println(principalDetails.getUsername());
		
		Page<Post> posts = postService.전체찾기(pageable);
		
		System.out.println("전체뽑은 리스트 "+posts);
		model.addAttribute("posts",posts); // 모델을 제공해줘서 모델에 담으면 된다. 그러면 값을 담고 리스트 페이지로 간다.//모델에 JSP 에서 리퀘스트디스패쳐에 담고 forward한거랑 같다 
		return "post/list";
	}
	
	@GetMapping("/post/saveForm")
	public String saveForm() {
		return "post/saveForm";
		
	}
	
	//수정하기 화면 들어가기
	@GetMapping("/post/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) {
		Post postEntity = postService.상세보기(id);
		model.addAttribute("post",postEntity); //지금 여기서는 댓글이 없음 LAZY이기 때문에
	
		return "post/updateForm";	
	}
	
	@PutMapping("/post/{id}")
	public @ResponseBody CMRespDto<?> updateById(@PathVariable int id,@RequestBody PostSaveReqDto postSaveReqDto){
		postService.수정하기(id, postSaveReqDto);
		return new CMRespDto<>(1,"성공",null);
	}
	
	@DeleteMapping("/post/{id}")
	public @ResponseBody CMRespDto<?> deleteById(@PathVariable int id){
		
		postService.삭제하기(id);
		return new  CMRespDto<>(1,"성공",null);
	}
	
	@GetMapping("/post/{id}")
	public String detail(@PathVariable int id, Model model) {
		
		Post postEntity = postService.상세보기(id);
		
		model.addAttribute("post",postEntity);
		
		return "post/detail"; // ViewResolver가 작동해서 jsp파일을 찾아줌.
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
