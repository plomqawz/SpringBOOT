package com.example.demo06.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo06.model.FileBoard;
import com.example.demo06.service.FileService;

@Controller
@RequestMapping("/file2/")
public class FileController2 {

	@Autowired
	private FileService fileService;
	
	@GetMapping("insert")
	public String insert() {
		return "/file2/fileboardInsert";
	}
	
	@PostMapping("fileInsert")
	public String insert(FileBoard fboard) {
		fileService.fildInsert(fboard);
		return "redirect:list";
	}
	
	@GetMapping("list")
	public String list(Model model) {
		model.addAttribute("fboards", fileService.fileList());
		model.addAttribute("count", fileService.count());
		return "/file2/fileboardList";
	}
}
