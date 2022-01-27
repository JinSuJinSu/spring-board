package com.poscoict.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.board.repository.BoardRepository;
import com.poscoict.board.vo.BoardVo;


@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	// 글 리스트(찾기 결과)
	public List<BoardVo> getContentsList(){
		List<BoardVo> list = boardRepository.findAll(); // 전체 개새글 가져오기
		return list;		
	}
	
	// 글쓰기
	public boolean addContents(BoardVo vo) {
		boolean result=false;
		if(vo.getTitle()!=null && !vo.getTitle().equals("") && vo.getContent()!=null && !vo.getContent().equals("")) {
			result=boardRepository.insertBoard(vo);
		}
		return result;		
	}
//	
//	// 답글 달기
//	public boolean replyContents(BoardVo vo) {
//		boolean result=false;
//		if(vo.getTitle()!=null && !vo.getTitle().equals("") && vo.getContent()!=null && !vo.getContent().equals("")) {
//			boolean updateResult=false;
//			boolean insertResult=false;
//			updateResult = boardRepository.replyUpdate(vo.getOrderNo(), vo.getGroupNo());
//			insertResult = boardRepository.replyInsert(vo);	
//			result=true;
//		}
//		return result;
//	}
//	
//	// 글보기(조회수 기능 포함)
//	public BoardVo getContents(Long no, HttpSession session){
//		BoardVo vo = boardRepository.findOne(no);
//		boolean result=false;
//		// session 처리
//		if(session.getAttribute("read") == null) {
//			session.setAttribute("read", new long[1]);
//		}
//		long[] readList = (long[])session.getAttribute("read");
//			if(readList[0]!=no) {
//				vo.setHit(vo.getHit()+1);; // 게시물을 볼때마다 조회수를 1개 증가시킨다.
//				result = boardRepository.readUpdate(vo); // 증가시킨 조회수를 update해서 db 데이터를 수정해준다.
//				readList[0]=no;
//				session.setAttribute("read", readList);
//			}
//		return vo;
//	}
//	
//	// 글보기(조회수 기능 없음)
//	public BoardVo getContents(Long no){
//		BoardVo vo = boardRepository.findOne(no);
//		return vo;
//	}	
//	
//	// 글 수정
//	public boolean updateContents(BoardVo vo) {
//		boolean result=false;
//		if(vo.getTitle()!=null && !vo.getTitle().equals("") && vo.getContent()!=null && !vo.getContent().equals("")) {
//			result = boardRepository.update(vo);	
//		}
//		return result;
//	}
//	
//	// 글 삭제
//	public boolean deleteContents(Long no) {
//		BoardVo vo = boardRepository.findOne(no);
//		int cnt = boardRepository.replyCheck(vo.getGroupNo());
//		boolean result=false;
//		if(cnt>1) {
//			// 댓글이 달려 있는 글일 경우
//			result = boardRepository.deleteUpdate(vo.getNo());
//		}
//		else {
//			// 댓글이 달려 있지 않은 글일 경우
//			result = boardRepository.delete(no);			
//		}
//		return result;
//	}
//	




}
