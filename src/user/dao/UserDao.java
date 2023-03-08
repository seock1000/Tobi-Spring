package user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import user.domain.User;

import java.sql.*;

public class UserDao {

    final private ConnectionMaker connectionMaker; // 싱글톤 패턴에서 읽기 전용값이므로 final

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker; // 관계 설정 관심사 분리
    }
    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement(
                "insert into User(id, name, password) values(?,?,?)"
        );
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement(
                "select * from User where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        return user;
    }

    public void deleteAll() throws ClassNotFoundException, SQLException{
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement(
                "delete from User");

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public Integer getCount() throws ClassNotFoundException, SQLException{
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement("select count(*) from User");

        ResultSet rs = ps.executeQuery();
        rs.next();
        Integer count = rs.getInt(1);

        ps.close();
        rs.close();
        c.close();

        return count;
    }
}



