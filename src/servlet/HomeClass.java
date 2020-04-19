package servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HomeClass {

	public void update(String flag) {

		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try{
			//Mysqlのドライバーと接続する
			Class.forName("com.mysql.jdbc.Driver");

			//Connectionにデータベース名、ユーザー名、パスワードを代入することで
			//使用するデータベースを特定できる
			con = DriverManager.getConnection(
										"jdbc:mysql://localhost:3306/aiscreann?charcterEncoding=utf8&serverTimezone=JST",
										"root","");
			//Statmentに使用するデータベース情報を代入する
/*			StringBuilder sb = new StringBuilder(100);
			sb.append("UPDATE is_login SET");
			sb.append("");
			sb.append(";");
*/			String sql = "UPDATE is_login SET is_login =" + flag + " WHERE id = 1;";

			st = con.prepareStatement(sql);
			//st.setString(1, flag);


			//更新系SQLを実行する
			st.executeUpdate();


			System.out.print(st.toString());
			System.out.println("OK");

		} catch (ClassNotFoundException e) {

		} catch (SQLException e) {

		} finally {
			if (rs != null) {
				try{
					rs.close();
					} catch (SQLException e){
						//TODO: handle exception
					}
				}
				if (st !=null) {
					try {
						st.close();
					} catch (SQLException e) {
						//TODO: handle exception
					}
				}
			}

	}

	/*
	 * ***************************************************************
	 */


	public String select() {
	       	String result = "";
	        Connection con = null;
	        PreparedStatement st = null;
	        ResultSet rs = null;
	        try{
	            //Mysqlのドライバーと接続する
	            Class.forName("com.mysql.jdbc.Driver");
	            //Connectionにデータベース名、ユーザー名、パスワードを代入することで
	            //使用するデータベースを特定できる
	            con = DriverManager.getConnection(
	                                        "jdbc:mysql://localhost:3306/aiscreann?charcterEncoding=utf8&serverTimezone=JST",
	                                        "root","");
	            //Statmentに使用するデータベース情報を代入する
	            StringBuilder sb = new StringBuilder(100);
	            sb.append("SELECT is_login ");
	            sb.append("FROM");
	            sb.append(" is_login ;");
	            String sql = sb.toString();
	            st = con.prepareStatement(sql);
	            //参照系SQLを実行する
	            rs = st.executeQuery();
	            rs.next();
	            result = rs.getString("is_login");
	        } catch (ClassNotFoundException e) {
	        } catch (SQLException e) {
	        } finally {
	            if (rs != null) {
	                try{
	                    rs.close();
	                    } catch (SQLException e){
	                        //TODO: handle exception
	                    }
	                }
	                if (st !=null) {
	                    try {
	                        st.close();
	                    } catch (SQLException e) {
	                        //TODO: handle exception
	                    }
	                }
	            }
	        return result;
	        }

}
