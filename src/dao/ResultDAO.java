package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

import sub.User;

public class ResultDAO {
	public String ResultAll() {

		JSONObject rootObj = new JSONObject();
		JSONArray list = new JSONArray();
		String Json = "";

		Connection con = null;
		Statement st = null;
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

				sb.append("SELECT u.name AS 名前,u.gender AS 性別,SUM(p.division_point) AS スコア,u.picture AS 写真 ");
				sb.append("FROM point p ");
				sb.append("INNER JOIN user u ");
				sb.append("ON u.user_id = p.user_id ");
				sb.append("GROUP BY u.name ");
				sb.append("ORDER BY スコア DESC;");

			String sql = sb.toString();

			st = con.createStatement();

			//参照系SQLを実行する
			rs = st.executeQuery(sql);

			//resultから必要な項目を取り出し
			//JSONobjectに入れる
			while(rs.next()) {
				JSONObject obj = new JSONObject();

				obj.put("name", rs.getString("名前"));
				obj.put("gender", rs.getString("性別"));
				obj.put("score", rs.getString("スコア"));
				obj.put("path", rs.getString("写真"));

				list.put(obj);

			}

			rootObj.put("list", list);

			Json = rootObj.toString();

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
		return Json;
	}

	public String DetailResult(String userId) {
		String Json = "";


		return Json;
	}


	/**
	 *
	 * @param user_id
	 * @return Userオブジェクト
	 */
	public User GetUserInfo() {
		User result = null;

		System.out.println("ユーザー情報取得クラス");
		Connection con = null;
        Statement st = null;
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
            sb.append("SELECT user.name,");
            sb.append("user.gender,");
            sb.append("user.picture,");
            sb.append("SUM(point.division_point) AS POINT,");
            sb.append("RANK() OVER (ORDER BY POINT DESC) AS RANK ");
            sb.append("FROM ");
            sb.append("user INNER JOIN point ");
            sb.append("ON user.user_id = point.user_id ");
            sb.append("GROUP BY user.user_id ");
            sb.append("ORDER BY user.user_id DESC LIMIT 1");
            sb.append(";");
            String sql = sb.toString();

            System.out.println(sql);
            st = con.createStatement();

            //参照系SQLを実行する
            rs = st.executeQuery(sql);

            if(rs.next()) {
            	result = new User();
            result.setName(rs.getString("user.name"));
            result.setGender(rs.getString("user.gender"));
            result.setPicture(rs.getString("user.picture"));
            result.setPoint(rs.getInt("POINT"));
            result.setRank(rs.getString("RANK"));
            }
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
