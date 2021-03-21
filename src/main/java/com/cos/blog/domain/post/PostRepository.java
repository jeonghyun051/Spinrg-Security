package com.cos.blog.domain.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Integer>{
	
	Page<Post> findByTitleContaining(String title, Pageable pageable);
	
//	@Query(value="select * from post where title like %?1%",nativeQuery = true)
//	Page<Post> findTitle(String title, Pageable pageable);
	
	/*
	 * findByTitleContaining() 드디어 검색을 직접적으로 호출하는 메서드가 나왔습니다. JpaRepository에서 메서드명의
	 * By 이후는 SQL의 where 조건 절에 대응되는 것인데, 이렇게 Containing을 붙여주면 Like 검색이 됩니다. 즉, 해당
	 * 함수는 %{keyword}% 이렇게 표현이 됩니다.
	 */
}
