package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Acount {

	public int signIn() {
		Connection conn = null;
		PreparedStatement sttm = null;
		try {
			String sql = "Select * from Account where";
			conn = ConnectDB.getDBConnect();
			sttm = conn.prepareStatement(sql);
			if(sttm.executeUpdate() > 0) {
				System.out.println("Dang nhap thanh cong");
				return 1;
			}
			else {
				System.out.println("Dang nhap that bai");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		} finally {
			try {
				sttm.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return -1;
	}
	
}
