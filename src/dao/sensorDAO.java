package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class sensorDAO {
	/**
	 * Androidから送信されたポイントをDBへ登録するためのメソッド
	 *
	 * @param user_id　ユーザーの主キー
	 * @param item_no いわゆる項番（何回目のポイントなのかということ）
	 * @param cdivision_point 受け取った値
	 */
	public void insertPoint(int user_id,int point_number,int cdivision_point) {


		StringBuilder sb = new StringBuilder(100);
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			sb.append("INSERT INTO ");
			sb.append("point");
			sb.append("(");
			sb.append("user_id,");
			sb.append("point_no,");
			sb.append("division_point");
			sb.append(")");
			sb.append("VALUES");
			sb.append("(");
			sb.append("?,");
			sb.append("?,");
			sb.append("?");
			sb.append(");");

			String sql = sb.toString();

			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(
										"jdbc:mysql://localhost:3306/aiscreann?charcterEncoding=utf8",
										"root","");

			st = con.prepareStatement(sql);

			st.setInt(1, user_id);
			st.setInt(2, point_number);
			st.setInt(3, cdivision_point);



			st.executeUpdate();

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO:handle exception
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO:handle exception
				}
			}
		}
	}


}
