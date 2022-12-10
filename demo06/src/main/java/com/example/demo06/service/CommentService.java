package com.example.demo06.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo06.model.Board;
import com.example.demo06.model.Comment;
import com.example.demo06.repository.BoardRepository;
import com.example.demo06.repository.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private BoardRepository boardRepository;
	
	//댓글 추가
	@Transactional //작성메서드(insert)는 flush 자동 아니라서 애노테이션 추가해야함.
	public void insert(Comment comment) {
		
		// replyCnt + 1
		Board b = boardRepository.findById(comment.getBoard().getNum()).get();
		b.setReplycnt(b.getReplycnt()+1);
		
		//commentRepository.save(comment); // 기존메서드 사용.
		//SQL
		commentRepository.insert(comment.getContent(),
				comment.getBoard().getNum(),
				comment.getUser().getId());
		
	}
	
	//댓글 리스트
	public List<Comment> list(Long bnum){
		return commentRepository.findByBnum(bnum);
	}
	
	//댓글 삭제
	@Transactional
	public void delete(Long cnum) {
		// replyCnt -1
		Optional<Comment> c = commentRepository.findById(cnum);
		Board b = c.get().getBoard();
		b.setReplycnt(b.getReplycnt()-1);
		
		commentRepository.deleteById(cnum);
	}
}
