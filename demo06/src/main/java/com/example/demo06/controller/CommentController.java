package com.example.demo06.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo06.config.auth.PrincipalUser;
import com.example.demo06.model.Board;
import com.example.demo06.model.Comment;
import com.example.demo06.service.CommentService;

@RestController // 다 json 형태로 돌려줘야함.
@RequestMapping("/reply/*")
public class CommentController {

	@Autowired
	private CommentService commentService;

	// 댓글추가
	@PostMapping("insert/{num}")
	public ResponseEntity<String> insert(@PathVariable Long num, @RequestBody Comment comment, @AuthenticationPrincipal PrincipalUser principal) {
		
		//bnum 컬럼에 Board 객체 넣는 과정.
		Board b = new Board();
		b.setNum(num);
		comment.setBoard(b);
		
		//user_id 컬럼에 User 객체 넣는 과정.-1번
		//PrincipalUser p = (PrincipalUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//comment.setUser(p.getUser());
		//-2번 : 받는 인자 @AuthenticationPrincipal PrincipalUser principal 추가한 뒤.
		comment.setUser(principal.getUser());
		
		commentService.insert(comment); // 코멘트 인서트만으로는 bnum 과 user_id 컬럼이 null 이다. 스트링이 아닌 객체 넣어야함.
		return new ResponseEntity<String>("success", HttpStatus.OK);
		// return null 일때도 success 창 떴지만 ResponseEntity 사용하면 상태까지 전달가능?
	}
	
	// 댓글리스트
	@GetMapping("list/{bnum}")
	public List<Comment> list(@PathVariable Long bnum) {
		System.out.println("num : " +bnum);
		return commentService.list(bnum);
	}
	
	// 댓글삭제
	@DeleteMapping("delete/{cnum}")
	public Long delete(@PathVariable Long cnum) {
		commentService.delete(cnum);
		return cnum;
	}
}
