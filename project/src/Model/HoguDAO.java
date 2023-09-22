package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HoguDAO {

	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;

	private void getConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@project-db-cgi.smhrd.com:1524:xe";
			String username = "cgi_23IS_CLOUD1_mini_3";
			String password = "smhrd3";
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// db 자원반납 메소드
	private void getClose() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			System.out.println("자원반납 문제 발생");
		}
	}

	// 회원가입 메소드
	public int join(HoguDTO dto) {
		int row = 0;

		try {
			getConn();
			String spl = "INSERT INTO CASINO(ID, PW, NICKNAME, MONEY) VALUES(?, ?, ?, ?)";

			psmt = conn.prepareStatement(spl);

			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPw());
			psmt.setString(3, dto.getNickname());
			psmt.setInt(4, dto.getMoney());
			
			row = psmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("회원가입 데이터를 DB에 넣으면서 오류가 발생했습니다.");
			// e.printStackTrace();
		} finally {
			getClose();
		}

		return row;

	}

	// 로그인 메소드
	public HoguDTO login(HoguDTO dto) {
		HoguDTO result = null;

		try {
			getConn();

			String sql = "SELECT * FROM CASINO WHERE ID = ? AND PW = ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPw());
			rs = psmt.executeQuery();

			if (rs.next()) {
				result = new HoguDTO();
				result.setId(rs.getString("id"));
				result.setPw(rs.getString("pw"));
				result.setNickname(rs.getString("nickname"));
				result.setMoney(rs.getInt("money"));

			}
		} catch (SQLException e) {
			result = null;
			e.printStackTrace();
		} finally {
			getClose();
		}
		return result;
	}

	// 회원탈퇴 메소드
	public int delete(HoguDTO dto) {
		int row = 0;

		try {

			getConn();

			String sql = "DELETE FROM CASINO WHERE ID = ? AND PW = ?";
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPw());

			row = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose();

		}
		return row;

	}

	// 사용자 배당금 업데이트 메소드
	public void updateMoney(HoguDTO dto) {
		int row = 0;
		try {
			getConn();

			String sql = "UPDATE CASINO SET MONEY = ?  WHERE ID = ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, dto.getMoney());
			psmt.setString(2, dto.getId());
			row = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose();
		}

	}

	// 랭킹 조회 메소드
	public ArrayList<HoguDTO> selectRank() {
		int row = 0;
		ArrayList<HoguDTO> sList = new ArrayList<HoguDTO>();

		HoguDTO result = new HoguDTO();

		try {
			getConn();
			String sql = "SELECT * FROM CASINO ORDER BY MONEY DESC, NICKNAME";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				result = new HoguDTO();
				result.setId(rs.getString("id"));
				result.setPw(rs.getString("pw"));
				result.setNickname(rs.getString("nickname"));
				result.setMoney(rs.getInt("money"));
				sList.add(result);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		return sList;

	}

	// 닉네임 조회 메소드
	public HoguDTO search(String search) {
		HoguDTO result = null;
		try {
			getConn();

			String sql = "SELECT * FROM CASINO WHERE NICKNAME LIKE ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, '%' + search + '%');
			rs = psmt.executeQuery();
			while (rs.next()) {
				result = new HoguDTO();
				result.setId(rs.getString("id"));
				result.setPw(rs.getString("pw"));
				result.setNickname(rs.getString("nickname"));
				result.setMoney(rs.getInt("money"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose();
		}

		return result;
	}

}
