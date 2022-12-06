package com.example.demo02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo02.dto.BoardDTO;
import com.example.demo02.service.BoardService;

@Controller
public class HomeController {
	
	@Autowired
	private BoardService bservice;
	
	// 서버 시작 시 home.jsp
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	// insert 클릭 시
	@GetMapping("insert")
	public String insert() {
		return "insert";
	}
	
	// 게시글 쓰기 클릭 시
	@PostMapping("insert")
	public String insert(BoardDTO board) {
		bservice.insert(board);
		return "redirect:list";
	}
	
	// list 클릭 시
	@GetMapping("list")
	public String list(Model model) {
		model.addAttribute("boards", bservice.list());
		model.addAttribute("count", bservice.getCount());
		return "list";
	}
	
	// 상세보기
	@GetMapping("view/{num}")
	public String view(@PathVariable int num, Model model) {
		model.addAttribute("board", bservice.findByNum(num));
		return "view";
	}
	
	// 수정 폼
	@GetMapping("update/{num}")
	public String update(@PathVariable int num, Model model) {
		model.addAttribute("board", bservice.findByNum(num));
		return "update";
	}
	
	// 수정 버튼
	@PutMapping("update")
	@ResponseBody
	public String update(@RequestBody BoardDTO board) {
		bservice.update(board);
		return "success";
	}
	
	// 삭제 버튼
	@DeleteMapping("delete/{num}")
	@ResponseBody
	public int delete(@PathVariable int num) {
		bservice.delete(num);
		return num;
	}
}
