package user.dao;

import org.springframework.dao.EmptyResultDataAccessException;

import user.domain.User;

import java.sql.*;

public class UserDao {

    final private ConnectionMaker connectionMaker; // 싱글톤 패턴에서 읽기 전용값이므로 final

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker; // 관계 설정 관심사 분리
    }
    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = connectionMaker.makeConnection();

            ps = c.prepareStatement(
                    "insert into User(id, name, password) values(?,?,?)"
            );
            ps.setString(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if(ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) { }
            }
            if(c != null) {
                try {
                    c.close();
                } catch (SQLException e) { }
            }
        }
    }

    public User get(String id) throws ClassNotFoundException, SQLException {

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;

        try {
            c = connectionMaker.makeConnection();

            ps = c.prepareStatement(
                    "select * from User where id = ?");
            ps.setString(1, id);

            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            if(ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) { }
            }
            if(c != null) {
                try {
                    c.close();
                } catch (SQLException e) { }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) { }
            }
        }
        if(user == null) throw new EmptyResultDataAccessException(1);

        return user;
    }

    public void deleteAll() throws ClassNotFoundException, SQLException{
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = connectionMaker.makeConnection();

            ps = c.prepareStatement(
                    "delete from User");

            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if(ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) { }
            }
            if(c != null) {
                try {
                    c.close();
                } catch (SQLException e) { }
            }
        }
    }

    public Integer getCount() throws ClassNotFoundException, SQLException{
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = connectionMaker.makeConnection();

            ps = c.prepareStatement("select count(*) from User");

            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);

        } catch (SQLException e) {
            throw e;
        } finally {
            if(ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) { }
            }
            if(c != null) {
                try {
                    c.close();
                } catch (SQLException e) { }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) { }
            }
        }

    }
}



