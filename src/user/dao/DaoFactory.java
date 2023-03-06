package user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * UserDao 생성 책임을 맡은 팩토리 클래스
 * 스프링이 제어권을 가지고 직접 만들고 관계를 부여하는 오브젝트 : 빈(Bean)
 * 빈의 생성, 관계설정 등의 제어를 담당하는 IoC 오브젝트 : 빈 팩토리
 */
@Configuration
public class DaoFactory {
    @Bean
    public UserDao userDao() {
        ConnectionMaker connectionMaker = connectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        return userDao;
    }

    @Bean
    public AccountDao accountDao(){
        ConnectionMaker connectionMaker = connectionMaker();
        AccountDao accountDao = new AccountDao(connectionMaker);
        return accountDao;
    }

    @Bean
    public MessageDao messageDao() {
        ConnectionMaker connectionMaker = connectionMaker();
        MessageDao messageDao = new MessageDao(connectionMaker);
        return messageDao;
    }

    // ConnectionMaker 타입 생성코드 중복제거
    private ConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }
}
