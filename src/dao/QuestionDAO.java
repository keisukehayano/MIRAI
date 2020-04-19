package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class QuestionDAO {
    public String QuestionAnswer(int user_id, int question_no) {
        String answer = "";
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
            sb.append("SELECT answer FROM user ");
            sb.append("INNER JOIN question ");
            sb.append("ON user.user_id = question.user_id ");
            sb.append("WHERE user.user_id = ? ");
            sb.append("AND question.question_no = ?;");
            String sql = sb.toString();
            st = con.prepareStatement(sql);
                st.setInt(1, user_id);
                st.setInt(2, question_no);
            //参照系SQLを実行する
            rs = st.executeQuery();
            System.out.print("ユーザーID検索");
            rs.next();
            answer = rs.getString("answer");
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
        return answer;
        }
}
