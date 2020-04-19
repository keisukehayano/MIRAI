package servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ActivityDAO {

	public String userName;
	public String userGender;
	public String userPicture;


	public void getUserData(int Id) {
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

	            String sql = "SELECT `name`, `gender`, `picture` FROM `user` WHERE `user_id` = "+ Id +";";

	            st = con.prepareStatement(sql);
	            //参照系SQLを実行する
	            rs = st.executeQuery();
	            rs.next();
	            this.userName = rs.getString("name");
	            this.userGender = rs.getString("gender");
	            this.userPicture = rs.getString("picture");
	        } catch (ClassNotFoundException e) {
	            System.out.println(e);
	        } catch (SQLException e) {
	            System.out.println(e);
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


}
