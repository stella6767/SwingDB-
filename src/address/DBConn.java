package address;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {
	
	public static Connection getConnection() {
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/sys?serverTimezone=Asia/Seoul";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //드라이버 인스턴스화
			conn = DriverManager.getConnection(url, "root", "stella@6767"); //인스턴스를 통해 dbms 커넥션
			
			System.out.println("DB 연결 성공");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
