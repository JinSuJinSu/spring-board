//package com.poscoict.board.repository;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.poscoict.board.vo.ReplyVo;
//
//
//public class ReplyRepository {
//	
//	// 특정 게시글에 답변을 달았을 때 데이터베이스에 추가
//	public boolean insert(ReplyVo vo) {
//		boolean result = false;
//		Connection conn = JDBC.getConnection();
//		try (PreparedStatement pstmt = conn
//				.prepareStatement("insert into reply (board_no, replyer, reply_content, reply_date) "
//						+ "values(?, ?, ?, now())")) {
//				pstmt.setInt(1, vo.getBoardNo());
//				pstmt.setString(2, vo.getReplyer());
//				pstmt.setString(3, vo.getReplyContent());
//				pstmt.executeUpdate();
//				result = true;
//		} catch (SQLException se) {
//			System.out.println(se.getMessage());
//		}
//		JDBC.close(conn);
//		return result;
//		
//	}
//	// 메소드 인자 값에 맞는 reply_no를 가진 ReplyVo객체 가져오기
//	public ReplyVo selectOne(int number) {
//		Connection conn = JDBC.getConnection();
//		ReplyVo vo=null;
//		try (Statement stmt = conn.createStatement()) {
//			ResultSet rs = stmt.executeQuery("select reply_no, board_no, replyer, reply_content, "
//					+ "date_format(reply_date, '%Y-%m-%d %H:%i:%s') reply_date from reply "
//					+ "where reply_no=" + number);
//			if (rs.next()) {
//				vo = new ReplyVo();
//				vo.setReplyNo(rs.getInt("reply_no"));
//				vo.setBoardNo(rs.getInt("board_no"));
//				vo.setReplyer(rs.getString("replyer"));
//				vo.setReplyContent(rs.getString("reply_content"));
//				vo.setReplyDate(rs.getString("reply_date"));	
//
//			}
//		} catch (SQLException se) {
//			System.out.println(se.getMessage());
//		}
//		JDBC.close(conn);
//		return vo;
//	}
//	
//	// 특정 게시판 번호와 일치하는 답변 리스트 가져오기
//	public List<ReplyVo> selectReply(int number) {
//		Connection conn = JDBC.getConnection();
//		List<ReplyVo> list = new ArrayList<ReplyVo>();
//		try(Statement stmt = conn.createStatement();){
//			ResultSet rs = stmt.executeQuery("select reply_no, board_no, replyer, reply_content, "
//					+ "date_format(reply_date, '%Y-%m-%d %H:%i:%s') reply_date from reply "
//					+ "where board_no=" + number + " order by reply_no desc");
//			while(rs.next()) {
//				ReplyVo temp = new ReplyVo(rs.getInt("reply_no"), rs.getInt("board_no"),rs.getString("replyer"),
//						rs.getString("reply_content"), rs.getString("reply_date"));
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
//	
//	// 댓글 삭제
//	public boolean replyDelete(int number) {
//		boolean result = false;
//		Connection conn = JDBC.getConnection();
//		try (PreparedStatement pstmt = conn.prepareStatement("delete from reply where reply_no = ?")) {
//			pstmt.setInt(1, number);
//			pstmt.executeUpdate();
//			result = true;
//		} catch (SQLException se) {
//			System.out.println(se.getMessage());
//		}
//		JDBC.close(conn);
//		return result;
//	}
//	
//	// 댓글 수정
//	public boolean replyUpdate(ReplyVo vo) {
//		boolean result = false;
//		Connection conn = JDBC.getConnection();
//		try (PreparedStatement pstmt = conn.prepareStatement("update reply "
//				+ "set reply_content = ? where reply_no = ?")) {
//			pstmt.setString(1, vo.getReplyContent());
//			pstmt.setInt(2, vo.getReplyNo());
//			pstmt.executeUpdate();		
//			result = true;
//		} catch (SQLException se) {
//			System.out.println(se.getMessage());
//		}
//		JDBC.close(conn);
//		return result;
//	}
//	
//
//}
