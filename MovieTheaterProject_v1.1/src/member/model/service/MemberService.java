package member.model.service;

import java.sql.Connection;

import common.JDBCTemplate;
import member.model.dao.MemberDao;
import member.model.vo.Member;

public class MemberService {
	public Member selectOneMember(String memberId) {
		//커넥션 생성
		Connection conn = JDBCTemplate.getConnection();
		Member m = new MemberDao().selectOneMember(conn,memberId);
		JDBCTemplate.close(conn);
		return m;
	}

	public Member selectOneMember(String memberId, String memberPw) {
		//커넥션 생성
		Connection conn = JDBCTemplate.getConnection();
		Member m = new MemberDao().selectOneMember(conn, memberId, memberPw);
		JDBCTemplate.close(conn);
		return m;
	}

	public int updateMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().updateMember(conn,m);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

}
