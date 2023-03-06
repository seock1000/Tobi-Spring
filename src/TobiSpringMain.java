import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import user.dao.DConnectionMaker;
import user.dao.DaoFactory;
import user.dao.UserDao;
import user.domain.User;

import java.sql.SQLException;

public class TobiSpringMain {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class); // DaoFactory를 설정정보로 사용하는 ApplicationContext
        UserDao dao = context.getBean(UserDao.class); // ApplicationContext를 사용하여 userDao의 오브젝트를 가져옴

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
