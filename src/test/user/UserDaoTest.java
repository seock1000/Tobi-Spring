package test.user;

import org.junit.After;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import user.dao.DaoFactory;
import user.dao.UserDao;
import user.domain.User;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserDaoTest {

    @After
    public void resetUser() throws ClassNotFoundException, SQLException{
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

        UserDao dao = context.getBean("userDao", UserDao.class);

        dao.deleteAll();
    }

    /**
     * addAndGet 테스트 보완
     * 여러 유저에 대해서도 작동하는지
     */
    @Test
    public void addAndGet() throws ClassNotFoundException, SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

        UserDao dao = context.getBean("userDao", UserDao.class);

        User user1 = new User("ronnie", "염석천", "springno1");
        User user2 = new User("wonu", "김원우", "springno2");

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        User getUser1 = dao.get(user1.getId());
        assertThat(getUser1.getName(), is(user1.getName()));
        assertThat(getUser1.getPassword(), is(user1.getPassword()));

        User getUser2 = dao.get(user2.getId());
        assertThat(getUser2.getName(), is(user2.getName()));
        assertThat(getUser2.getPassword(), is(user2.getPassword()));
    }

    /**
     * getCount() 메서드 테스트
     */
    @Test
    public void count() throws ClassNotFoundException, SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

        UserDao dao = context.getBean("userDao", UserDao.class);
        User user1 = new User("ronnie", "염석천", "springno1");
        User user2 = new User("wonu", "김원우", "springno2");
        User user3 = new User("gunuk", "함건욱", "springno3");

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        assertThat(dao.getCount(), is(1));

        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        dao.add(user3);
        assertThat(dao.getCount(), is(3));
    }
}
