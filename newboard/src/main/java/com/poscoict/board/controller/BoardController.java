package com.poscoict.board.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.poscoict.board.service.BoardService;
import com.poscoict.board.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	// 게시판 글 전체 보기
	@RequestMapping({"","/list"})
	public String viewList(Model model) {
		List<BoardVo> list = boardService.getContentsList(); // 검색 값이 비어 있을 경우는 전체 조회가 된다.
		model.addAttribute("list", list);
		return "board/list";
		
	}
	
	// 게시판 글 추가
	@RequestMapping({"/write"})
	public String write(BoardVo vo) {
		boolean result = boardService.addContents(vo);
		return "redirect:/";
	}

	

}
