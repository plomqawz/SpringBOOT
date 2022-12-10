package com.example.demo06.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo06.config.auth.PrincipalUser;
import com.example.demo06.model.Board;
import com.example.demo06.service.BoardService;

@RequestMapping("/board/*")
@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping("insert")
	@PreAuthorize("isAuthenticated()") // 인서트 탭 > 로그인 창 > 로그인 후 인서트 창
	public String insert() {
		return "/board/insert";
	}

	@PostMapping("insert")
	public String insert(Board board, @AuthenticationPrincipal PrincipalUser principal) { // board 에는 user 있으니 받아오기 위함.
		boardService.insert(board, principal.getUser());
		return "redirect:list";
	}

	// 게시글 전체보기(기존)
//	@GetMapping("list")
//	public String list(Model model) {
//		model.addAttribute("boards", boardService.findAll());
//		model.addAttribute("count", boardService.count());
//		return "/board/list";
//	}

	// 게시글 전체보기 : 페이징 ===> 페이징 정보 + data(게시글 정보)
//	@GetMapping("list")
//	public String list(Model model,
//			@PageableDefault(size=5, sort="num", direction=Direction.DESC)Pageable pageable) {
//		
//		boardService.findAll(pageable);
//		Page<Board> lists = boardService.findAll(pageable);
//		System.out.println("lists : "+lists);
//		model.addAttribute("count", boardService.count());
//		model.addAttribute("boards", lists);
//		return "/board/list";
//	}

	// 게시글 전체보기 : 페이징, 검색
	@GetMapping("list")
	public String list(Model model,
			@PageableDefault(size = 5, sort = "num", direction = Direction.DESC) Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String field,
			@RequestParam(required = false, defaultValue = "") String word) {

		Page<Board> lists = boardService.findAll(field, word, pageable);
		Long count = boardService.count(field, word);
		model.addAttribute("count", count);
		model.addAttribute("boards", lists);
		return "/board/list";
	}

	@GetMapping("view/{num}")
	public String view(@PathVariable Long num, Model model) {
		model.addAttribute("board", boardService.findById(num));
		return "/board/view";
	}

	@DeleteMapping("delete/{num}")
	@ResponseBody
	public String delete(@PathVariable Long num) {
		boardService.delete(num);
		return "success";
	}

	@GetMapping("update/{num}")
	public String update(@PathVariable Long num, Model model) {
		model.addAttribute("board", boardService.findById(num));
		return "/board/update";
	}

	@PutMapping("update")
	@ResponseBody
	public String update(@RequestBody Board board) {
		boardService.update(board);
		return "success";
	}
}
