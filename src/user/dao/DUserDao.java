package user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DUserDao extends UserDao {

    /**
     * 상속을 통한 Dao의 확장
     * 커넥션을 원하는 대로 생성해서 UserDao 사용 가능, UserDao가 바이너리 파일로 제공되어도 사용 가능
     */
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost/tobi_spring", "root", "0512");
        return c;
    }
}
