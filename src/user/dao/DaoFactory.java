package user.dao;

/**
 * UserDao 생성 책임을 맡은 팩토리 클래스
 */
public class DaoFactory {
    public UserDao userDao() {
        ConnectionMaker connectionMaker = connectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        return userDao;
    }

    public AccountDao accountDao(){
        ConnectionMaker connectionMaker = connectionMaker();
        AccountDao accountDao = new AccountDao(connectionMaker);
        return accountDao;
    }

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
