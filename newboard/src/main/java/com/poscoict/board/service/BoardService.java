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
	public Map<String, Object> getContentsList(String value, String kwd, int currentPage, String arrow){
		List<BoardVo> totalList = boardRepository.findAll(value,kwd); // 전체 개새글 가져오기
		List<BoardVo> list = boardRepository.limitFind(value,kwd,currentPage); // 현제 패이지 개시글
		
		int startPage = 1 + 5*((currentPage-1)/5); // 시작페이지는 1, 6, 11 이런식으로 되고 페이징 시작점이 5개씩이다.
		int endPage = startPage + 4; // 화살표 양옆으로 5개씩 페이지가 존재하므로 첫페이지가 1이면 마지막 페이지는 5가 된다.
		if (endPage >= Math.ceil((double) totalList.size() / 5)) {
			endPage = (int) Math.ceil((double) totalList.size() / 5); // 예들 들어 게시글의 개수가 36개일 때 최대 페이지 번호는 8이고 그보다 크면 범위를 벗어나므로 최대페이지 번호로 고정시킨다.
		}
		if(arrow.equals("arrow")) {
			currentPage=startPage; // 화살표를 클릭했을 때 1,6,11로 시작하므로 현재 페이지를 시작 페이지와 같게 해준다.
		}
		
		Map<String, Object> map = new HashMap<>();
	
		map.put("list", list);
		map.put("totalList", totalList);
		map.put("startPage", startPage);
		map.put("currentPage", currentPage);
		map.put("endPage", endPage);
		map.put("value", value);
		map.put("kwd", kwd);
		return map;
				
		}
	
	// 글쓰기
	public boolean addContents(BoardVo vo) {
		boolean result=false;
		if(vo.getTitle()!=null && !vo.getTitle().equals("") && vo.getContent()!=null && !vo.getContent().equals("")) {
			result=boardRepository.insertBoard(vo);
		}
		return result;		
	}
	
	// 글 보기(조회수 기능 포함)
	public BoardVo getContents(int boardNo, HttpSession session){
		BoardVo vo = boardRepository.findOne(boardNo);
		boolean result=false;
		// session 처리
		if(session.getAttribute("read") == null) {
			session.setAttribute("read", new int[1]);
		}
		int[] readList = (int[])session.getAttribute("read");
			if(readList[0]!=boardNo) {
				vo.setReadCount(vo.getReadCount()+1);; // 게시물을 볼때마다 조회수를 1개 증가시킨다.
				result = boardRepository.readUpdate(vo); // 증가시킨 조회수를 update해서 db 데이터를 수정해준다.
				readList[0]=boardNo;
				session.setAttribute("read", readList);
			}
		return vo;
	}
	// 글 삭제
	public boolean deleteContents(int boardNo) {
		BoardVo vo = boardRepository.findOne(boardNo);		
		boolean result=false; // 댓글이 달려 있지 않은 글일 경우
		result = boardRepository.delete(boardNo);			
		return result;
	}
	
	// 글 수정
	public boolean updateContents(BoardVo vo) {
		boolean result=false;
		if(vo.getTitle()!=null && !vo.getTitle().equals("") && vo.getContent()!=null && !vo.getContent().equals("")) {
			result = boardRepository.update(vo);	
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

//	

//	


}
