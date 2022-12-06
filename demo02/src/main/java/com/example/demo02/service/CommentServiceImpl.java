package com.example.demo02.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo02.dao.BoardMapper;
import com.example.demo02.dao.CommentMapper;
import com.example.demo02.dto.CommentDTO;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentMapper cmapper;
	@Autowired
	private BoardMapper bmapper;

	@Override // 댓글 추가 + 댓글 개수 증가
	@Transactional // 하나로 묶는 트랜잭션 애노테이션. 추가설정 필요없음.
	public void insert(CommentDTO comment) {
		cmapper.insert(comment);
		bmapper.updateReplyCnt(comment.getBnum(), 1);
		//@param 안쓰는방법
		//HashMap<String, Integer> hm = new HashMap<>();
		//hm.put("bnum", comment.getBnum());
		//hm.put("amount", 1);
		//bmapper.updateReplyCnt(hm);

	}

	@Override // 댓글 전체보기
	public List<CommentDTO> getList(int num) {
		return cmapper.getList(num);
	}

	@Override // 댓글 삭제하기 + 댓글 개수 감소
	@Transactional
	public void delete(int cnum) {
		CommentDTO comment = cmapper.findByNum(cnum); // cnum 넘겨서 전체 행 불러오고,
		bmapper.updateReplyCnt(comment.getBnum(), -1); // 불러온 행의 bnum 댓글수컬럼에서 -1
		cmapper.delete(cnum); // 삭제먼저하면 cnum 이 사라지므로 안됨. 삭제는 마지막에.
	}

}
