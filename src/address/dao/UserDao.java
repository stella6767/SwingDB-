package address.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import address.DBConn;
import address.model.User;

public class UserDao {
	
	static int id = 0;
	
	public static void  삭제하기(int id) {
		// stream에 연결된 객체
		Connection conn = DBConn.getConnection();

		// 버퍼에 담을 메시지
		String query = "delete from users where id = ?";

		try {
			// 버퍼 연결해서 쿼리 전송
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id); //?에 값 넣기
			// commit
			pstmt.executeUpdate();
			System.out.println("DELETE가 완료되었습니다");
		} catch (SQLException e) {
			System.out.println("Query 전송 시 오류가 발생하였습니다.");
		}
	}

	
	public static void 수정하기(int id, String name, String phone, String address, String relation) {
		// stream�� ����� ��ü
		Connection conn = DBConn.getConnection();

		// ���ۿ� ���� �޽���
		String query = "update users set name = ?, phone = ?, address = ?, relation = ? where id = ?";

		try {
			// ���ۿ����ؼ� ���� ����
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			pstmt.setString(3, address);
			pstmt.setString(4, relation);
			pstmt.setInt(5, id);
			
			// commit
			pstmt.executeUpdate();
			System.out.println("UPDATE가 완료");
		} catch (SQLException e) {
			System.out.println("Query 전송 시 오류."+e.getMessage());
		}
	}
	
	public static ArrayList<User> 찾기() {
		// 컬렉션 만들기
		ArrayList<User> list = new ArrayList<User>();
		
		// stream에 연결된 객체
		Connection conn = DBConn.getConnection();

		// 버퍼에 담을 메시지
		String query = "select * from users";

		try {
			// 
			PreparedStatement pstmt = conn.prepareStatement(query);
			// ��� �ޱ�
			ResultSet rs = pstmt.executeQuery();
			System.out.println("SELECT가 완료.");
			
			while(rs.next()) { // �˻� ����� �Ѱ��� �ƴϸ�  while�� �����Ѵ�.
				int id = rs.getInt("id"); // Į����
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				String relation = rs.getString("relation");
				
				User user = new User(id, name, phone, address, relation);
				list.add(user);
			}	
			return list;
			
		} catch (SQLException e) {
			System.out.println("Query 전송 시 오류."+e.getMessage());
		}
		return null;
	}
	
	
	public static ArrayList<User> 검색(String relation) {
		// �÷��� �����
		ArrayList<User> list = new ArrayList<User>();
		
		// stream�� ����� ��ü
		Connection conn = DBConn.getConnection();

		// ���ۿ� ���� �޽���
		String query = "select * from users where relation = ?";

		try {
			// ���ۿ����ؼ� ���� ����
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, relation);
			
			// ��� �ޱ�
			ResultSet rs = pstmt.executeQuery();
			System.out.println("SELECT가 완료.");
			
			while(rs.next()) { // �˻� ����� �Ѱ��� �ƴϸ�  while�� �����Ѵ�.
				int id = rs.getInt("id"); // Į����
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				String relation2 = rs.getString("relation");
				
				User user = new User(id, name, phone, address, relation2);
				list.add(user);
			}	
			return list;
			
		} catch (SQLException e) {
			System.out.println("Query 전송 시 오류"+e.getMessage());
		}
		return null;
	}
	
	public static User 찾기(int id) {
		// stream�� ����� ��ü
		Connection conn = DBConn.getConnection();

		// ���ۿ� ���� �޽���
		String query = "select id, name, phone, address, relation from users where id = ?";

		try {
			// ���ۿ����ؼ� ���� ����
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			// ��� �ޱ�
			ResultSet rs = pstmt.executeQuery();
			System.out.println("SELECT가 완료");
			
			if(rs.next()) { // �˻� ����� �Ѱ��� �ƴϸ�  while�� �����Ѵ�.
				int id2 = rs.getInt("id"); // Į����
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				String relation = rs.getString("relation");
				
				return new User(id2, name, phone, address, relation);
			}			
			
		} catch (SQLException e) {
			System.out.println("Query 전송시 오류"+e.getMessage());
		}
		return null;
	}
	
	public static void 추가하기(String name, String phone, String address, String relation) {
		// stream�� ����� ��ü
		Connection conn = DBConn.getConnection();
		
		id++;
		// ���ۿ� ���� �޽���
		String query = "INSERT INTO users(id,name, phone, address, relation) VALUES(id, ?, ?, ?, ?)";

		try {
			// ���ۿ����ؼ� ���� ����
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			pstmt.setString(3, address);
			pstmt.setString(4, relation);
			// commit
			pstmt.executeUpdate();
			System.out.println("INSERT가 완료");
		} catch (SQLException e) {
			System.out.println("Query 전송 시 오류");
		}
	}
}
