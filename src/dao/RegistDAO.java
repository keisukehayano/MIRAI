package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistDAO {

	public void regist(String strName,String strGender) {

		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try{
			//Mysqlのドライバーと接続する
			Class.forName("com.mysql.jdbc.Driver");

			//Connectionにデータベース名、ユーザー名、パスワードを代入することで
			//使用するデータベースを特定できる
			con = DriverManager.getConnection(
										"jdbc:mysql://localhost:3306/aiscreann?charcterEncoding=utf8",
										"root","");
			//Statmentに使用するデータベース情報を代入する
			StringBuilder sb = new StringBuilder(100);
			sb.append("INSERT INTO user (");
			sb.append("name,");
			sb.append("gender)");
			sb.append("VALUES(?,?);");
			String sql = sb.toString();

			st = con.prepareStatement(sql);
				st.setString(1, strName);
				st.setString(2, strGender);


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


	/************************************
	 *								    *
	 *	アクティビティの登録処理 ここ追加する	*
	 *								    *
	 ************************************/

	public void registActivity(String ActivityName,int user_id) {

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
			sb.append("UPDATE user ");
			sb.append("SET activity = ? ");
			sb.append("WHERE user_id = ?;");
			String sql = sb.toString();

			System.out.println("\nアクティビティ名＝＝＞"+ ActivityName);

			st = con.prepareStatement(sql);
				st.setString(1, ActivityName);
				st.setInt(2, user_id);

			//更新系SQLを実行する
			st.executeUpdate();

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
 * **************  SAVE PICTURE  ********************************
 */



	public void savePicture(String pictureData, int userId) {

		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try{
			//Mysqlのドライバーと接続する
			Class.forName("com.mysql.jdbc.Driver");

			//Connectionにデータベース名、ユーザー名、パスワードを代入することで
			//使用するデータベースを特定できる
			con = DriverManager.getConnection(
										"jdbc:mysql://localhost:3306/aiscreann?charcterEncoding=utf8",
										"root","");
			//Statmentに使用するデータベース情報を代入する
/*			StringBuilder sb = new StringBuilder(100);
			sb.append("UPDATE `user` SET ");
			sb.append("`picture`='?'");
			sb.append("WHERE `user_id` = ? ");
			sb.append(";");
*/
			String sql = "UPDATE `user` SET `picture`='"+ pictureData +"' WHERE `user_id` = "+ userId +" ;";
			System.out.println("picture 1");

			st = con.prepareStatement(sql);


			//更新系SQLを実行する
			st.executeUpdate();


			System.out.print(st.toString());
			System.out.println("picture OK");

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

/*
 * **************  GET USER NAME  ********************************
 */


    public String getUserName(int userId) {
        String userName = "";
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
            sb.append("SELECT `name` ");
            sb.append("FROM `user` ");
            sb.append("WHERE `user_id` = ?");
            sb.append(";");
            String sql = sb.toString();

            st = con.prepareStatement(sql);
                st.setInt(1, userId);
            //参照系SQLを実行する
            rs = st.executeQuery();
            System.out.print("ユーザー名検索");
            rs.next();
            userName = rs.getString("name");
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
        return userName;
        }
}