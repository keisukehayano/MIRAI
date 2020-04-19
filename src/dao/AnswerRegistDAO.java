package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class AnswerRegistDAO {

	private int userId;

	public String ajax(){
		return "AJAX";
	}


    public int UserIdSearch() {

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
            sb.append("SELECT user_id FROM `user` ORDER BY user_id DESC LIMIT 1;");
            String sql = sb.toString();
            st = con.prepareStatement(sql);
            //参照系SQLを実行する
            rs = st.executeQuery();
            System.out.print("ユーザーID検索");
            rs.next();
            this.userId = rs.getInt("user_id");
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
        return this.userId;
        }

      public void AnswerRegist(int user_id,int questionNo, String answer) {


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
            sb.append("INSERT INTO question (");
            sb.append("question_no,");
            sb.append("user_id,");
            sb.append("answer)");
            sb.append("VALUES(?, ?, ?);");
            String sql = sb.toString();
            st = con.prepareStatement(sql);
                st.setInt(1, questionNo);
                st.setInt(2, user_id);
                st.setString(3, answer);
            //更新系SQLを実行する
            st.executeUpdate();
            System.out.print(st.toString());
            System.out.println("答え登録処理");
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

}
