package com.poscoict.board.controller;


import java.util.Map;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.poscoict.board.service.BoardService;
import com.poscoict.board.vo.BoardVo;
import com.poscoict.web.util.WebUtil;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	// 게시판 글 전체 보기
	@RequestMapping({"","/list"})
	public String viewList(Model model, HttpSession session,
			@RequestParam(value="page", required=true, defaultValue="1") int page,
			@RequestParam(value="kwd", required=true, defaultValue="title") String kwd,
			@RequestParam(value="value", required=true, defaultValue="") String value,
			@RequestParam(value="arrow", required=true, defaultValue="") String arrow)
	{
		
		if((int[])session.getAttribute("read")!=null) {
			session.removeAttribute("read"); // 조회가 끝나면 해당 세션을 제거해준다.
		}
		Map<String, Object> map = boardService.getContentsList(value, kwd ,page, arrow); // 검색 값이 비어 있을 경우는 전체 조회가 된다.
		model.addAttribute("map", map);
		return "board/list";	
	}
	
	// 게시판 글 추가
	@RequestMapping(value="/write", method = RequestMethod.POST)
	public String write(BoardVo vo) {
		boolean result = boardService.addContents(vo);
		return "redirect:/board";
	}
	
	// 게시판 글 조회
	@RequestMapping(value = "/view/{boardNo}", method = RequestMethod.GET)
	public String view(@PathVariable("boardNo") int boardNo, Model model, HttpSession session,
			@RequestParam(value="page", required=true, defaultValue="1") int page,
			@RequestParam(value="kwd", required=true, defaultValue="title") String kwd,
			@RequestParam(value="value", required=true, defaultValue="") String value){
		boolean result=false;
		BoardVo vo = boardService.getContents(boardNo, session);
		model.addAttribute("readvo", vo);
		return "board/view";
	}
	
	// 게시판 글 삭제
	@RequestMapping(value = "/delete/{boardNo}", method = RequestMethod.GET)
	public String view(@PathVariable("boardNo") int boardNo,
			@RequestParam(value="page", required=true, defaultValue="1") int page,
			@RequestParam(value="kwd", required=true, defaultValue="title") String kwd,
			@RequestParam(value="value", required=true, defaultValue="") String value){
		boolean result=boardService.deleteContents(boardNo);
		return "redirect:/board?page=" + page + "&kwd=" + kwd + "&value=" + WebUtil.encodeURL(value, "UTF-8").replace("%27", "");
	}
	
	// 게시판 글 수정
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(BoardVo vo,
			@RequestParam(value="page", required=true, defaultValue="1") int page,
			@RequestParam(value="kwd", required=true, defaultValue="title") String kwd,
			@RequestParam(value="value", required=true, defaultValue="") String value){
		boolean result=boardService.updateContents(vo);
		return "redirect:/board?page=" + page + "&kwd=" + kwd + "&value=" + WebUtil.encodeURL(value, "UTF-8").replace("%27", "");
	}
	

	

}
