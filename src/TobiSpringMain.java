import user.dao.DConnectionMaker;
import user.dao.UserDao;
import user.domain.User;

import java.sql.SQLException;

public class TobiSpringMain {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        UserDao dao = new UserDao(new DConnectionMaker()); // 클라이언트가 DB 커넥션 책임

        User user = new User();
        user.setId("whiteship");
        user.setName("염석천");
        user.setPassword("married");

        dao.add(user);

        System.out.println(user.getId() + "등록 성공");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());

        System.out.println(user2.getId() + " 조회 성공");
    }
}
