package com.example.demo06.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo06.config.auth.PrincipalUser;
import com.example.demo06.model.User;
import com.example.demo06.repository.UserRepository;
import com.example.demo06.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor // == @Autowired
public class HomeController {
	
	private final UserService userService;
	private final UserRepository userRepository;

	@GetMapping("/")
	public String home() {
		return "redirect:/board/list";
	}
	
	@GetMapping("/login")
	public String login() {
		return "/user/login";
	} 
	
	// 회원가입 폼
	@GetMapping("register")
	public String register() {
		return "/user/join";
	}
	
	// 회원가입 버튼
	@PostMapping("register")
	@ResponseBody
	public String register(@RequestBody User user) {
		// 기존 username 있으면 fail 리턴
		if(userRepository.findByUsername(user.getUsername())!=null) {
			return "fail";
		}
		userService.register(user);
		return "success";
	}
	
	// 회원정보수정 폼
	@GetMapping("userupdate/{id}")
	public String memberModify(@PathVariable Long id, Model model) {
		model.addAttribute("member", userService.findById(id));
		return "/user/update";
	}
	
	// 회원정보수정 버튼
	@PutMapping("userupdate")
	public String userupdate() {
		return "";
	}
	
	
}
