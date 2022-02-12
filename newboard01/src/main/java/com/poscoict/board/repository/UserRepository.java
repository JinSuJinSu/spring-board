package com.poscoict.board.repository;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.poscoict.board.vo.UserVo;

@Repository
public class UserRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	// 로그인시 아이디와 비밀번호가 일치하는지 확인
	public UserVo selectUser(String id, String password) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("password", password);
		UserVo vo = sqlSession.selectOne("user.selectUser",map);
		return vo;	
	}
	
	// 회원가입시 아이디 중복 체크
	public UserVo findUser(String id) {
		UserVo vo = sqlSession.selectOne("user.findUser",id);
		return vo;
	}
	
	// 회원가입시 사용자 정보 추가
	public boolean insertUser(UserVo vo) {
		int cnt = sqlSession.insert("user.insertUser",vo);
		return cnt==1;
		
	}
//	
//	// 회원가입이 성공하면 테이블에 유저 정보 추가
//	public boolean insert(UserVo uo) {
//		boolean result = false;
//		Connection conn = JDBC.getConnection();
//		try (PreparedStatement pstmt = conn.prepareStatement("insert into userinfo (user_id, name, password, phone, email) values (?, ?,?,?,?)")) { 
//			// 동적파라미터인 ? 를 줄수 있는 개수는 정해지지 않는다. 무한대	
//			pstmt.setString(1, uo.getUserID());
//			pstmt.setString(2, uo.getName());
//			pstmt.setString(3, uo.getPassword());
//			pstmt.setString(4, uo.getPhone());
//			pstmt.setString(5, uo.getEmail());
//			pstmt.executeUpdate();	 //업데이트 필요한 값을 서블릿이 보내준다.	
//			result = true;  // 업데이트에 성공하면 TRUE 리턴 한다.
//		} catch (SQLException se) {
//			System.out.println(se.getMessage());
//		}
//		JDBC.close(conn);
//		return result;
//	}
//	

	
//	// 회원 탈퇴 시 유저 삭제
//	public boolean userDelete(String user) {
//		boolean result = false;
//		Connection conn = JDBC.getConnection();
//		try (PreparedStatement pstmt = conn.prepareStatement("delete from userinfo where user_id=?")) { 
//			pstmt.setString(1, user);
//			pstmt.executeUpdate();	 //업데이트 필요한 값을 서블릿이 보내준다.	
//			result = true;  // 업데이트에 성공하면 TRUE 리턴 한다.
//		} catch (SQLException se) {
//			System.out.println(se.getMessage());
//		}
//		JDBC.close(conn);
//		return result;
//	}	
}



	


