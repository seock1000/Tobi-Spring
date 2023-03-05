package user.dao;

import user.domain.User;

import java.sql.*;

public abstract class UserDao {
    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection c = getConnection();

        PreparedStatement ps = c.prepareStatement(
                "insert into user(id, name, password) values(?,?,?)"
        );
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = getConnection();

        PreparedStatement ps = c.prepareStatement(
                "select * from user where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        return user;
    }

    /**
     * DB 연결 관심사의 분리
    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost/tobi_spring", "root", "0512");
        return c;
    }
     */

    /**
     * 상속을 통한 Dao의 확장
     * NUserDao, DUserDao와 같이 원하는 대로 커넥션을 생성 가능
     */
    public abstract Connection getConnection() throws ClassNotFoundException, SQLException;

}



