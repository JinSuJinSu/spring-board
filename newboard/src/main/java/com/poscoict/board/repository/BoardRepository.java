package com.poscoict.board.repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.board.vo.BoardVo;


@Repository
public class BoardRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	// 모든 글의 게시판 번호를 조회한다.(검색 포함)
	public List<BoardVo> findAll(String value, String kwd){
		Map<String, Object> map = new HashMap<>();
		map.put("kwd", kwd);
		map.put("value", value);
		List<BoardVo> list=sqlSession.selectList("board.findAll", map);
		return list;			
	}
	
	// 특정 범위의 글을 페이징 처리해서보여줌(검색 포함)
	public List<BoardVo> limitFind(String value, String kwd, int currentPage){
		Map<String, Object> map = new HashMap<>();
		map.put("kwd", kwd);
		map.put("value", value);
		map.put("page", (currentPage-1)*10);
		List<BoardVo> list=sqlSession.selectList("board.limitFind", map);
		return list;			
	}
	
	// 유저가 작성한 글을 추가해준다.
	public boolean insertBoard(BoardVo vo) {
		int cnt = sqlSession.insert("board.insertBoard", vo);
		return cnt==1;
	}
	
	// 특정 번호를 가지고 있는 글을 조회한다.
	public BoardVo findOne(int boardNo) {
		BoardVo vo = sqlSession.selectOne("board.findOne", boardNo);
		return vo;
	}
	
	// 조회수 증가시키기는데 필요한 메소드
	public boolean readUpdate(BoardVo vo) {
		int cnt=sqlSession.update("board.readUpdate", vo);
		return cnt==1;
	}
	
	// 글을 삭제해버린다.
	public boolean delete(int boardNo) {
		int cnt=sqlSession.delete("board.delete", boardNo);
		return cnt==1;
	}
	
	// 글 수정하기
	public boolean update(BoardVo vo) {
		int cnt=sqlSession.update("board.update", vo);
		return cnt==1;
	}

	
//	// 답변수 증가시키기는데 필요한 메소드
//	public boolean replyUpdate(BoardVo vo) {
//		boolean result = false;
//		Connection conn = JDBC.getConnection();
//		try (PreparedStatement pstmt = conn.prepareStatement("update board "
//				+ "set reply_count = ? where board_no = ?")) {
//			pstmt.setInt(1, vo.getReplyCount());
//			pstmt.setInt(2, vo.getBoardNO());
//			pstmt.executeUpdate();		
//			result = true;
//		} catch (SQLException se) {
//			System.out.println(se.getMessage());
//		}
//		JDBC.close(conn);
//		return result;
//	}
//	
//	// 페이징 처리를 위한 메소드
//	public List<BoardVo> selectPage(int startPage, int endPage) {
//		Connection conn = JDBC.getConnection();
//		List<BoardVo> list = new ArrayList<BoardVo>();
//		try(Statement stmt = conn.createStatement();){
//			ResultSet rs = stmt.executeQuery("select board_no, user_id, title, "
//					+ "read_count, reply_count, date_format(write_date, '%Y-%m-%d %H:%i:%s') write_date from board "
//					  +"order by board_no desc limit " + startPage + "," + endPage);
//			while(rs.next()) {
//				BoardVo temp = new BoardVo(rs.getInt("board_no"),rs.getString("user_id"),
//						rs.getString("title"), rs.getInt("read_count"), rs.getInt("reply_count"),
//						rs.getString("write_date"));
//				
//				list.add(temp);
//			}
//			}
//			 catch (SQLException se) {
//				System.out.println(se.getMessage());
//			}
//			JDBC.close(conn);		
//		return list;
//	}

		
	}



	


