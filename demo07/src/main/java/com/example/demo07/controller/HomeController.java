package com.example.demo07.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo07.model.User;
import com.example.demo07.repository.UserRepository;
import com.example.demo07.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;

	// 홈 페이지
	@GetMapping("/")
	public String home() {
		return "home";
	}

	// 회원가입 폼
	@GetMapping("join")
	public String register() {
		return "/user/join";
	}

	// 회원가입 버튼
	@PostMapping("join")
	@ResponseBody
	public String join(@RequestBody User user) {
		// 기존 username 있다면 fail 리턴.
		if (userRepository.findByUsername(user.getUsername()) != null) {
			return "fail";
		}
		userService.register(user);
		return "success";
	}

	// 로그인 폼
	@GetMapping("/login")
	public String login() {
		return "/user/login";
	}

	// 회원목록
	@GetMapping("list")
	@PreAuthorize("isAuthenticated()")
	public String list(Model model,
			@PageableDefault(size = 5, sort = "id", direction = Direction.DESC) Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String field,
			@RequestParam(required = false, defaultValue = "") String word) {

		Page<User> users = userService.findAll(field, word, pageable);
		Long count = userService.count(field, word);
		model.addAttribute("count", count);
		model.addAttribute("users", users);
		return "/user/list";
	}

	// 회원정보수정 폼
	@GetMapping("userupdate/{id}")
	public String memberModify(@PathVariable Long id, Model model) {
		model.addAttribute("member", userService.findById(id));
		return "/user/update";
	}

	// 회원정보수정 버튼
	@PutMapping("userupdate")
	@ResponseBody
	public String userupdate(@RequestBody User user, HttpSession session) {
		if (userRepository.findByUsername(user.getUsername()) != null) {
			return "fail";
		}
		userService.userupdate(user);
		session.invalidate();
		return "success";
	}
	
	// 회원탈퇴 버튼
	@DeleteMapping("userdelete/{id}")
	@ResponseBody
	public String userdelete(@PathVariable Long id, HttpSession session) {
		userService.userdelete(id);
		session.invalidate();
		return "success";
	}
}
