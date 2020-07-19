package psychotest.repository.reg_login;

import psychotest.entity.UserEntity;

public interface UserDao {
    public UserEntity findUserAccount(String userName);
}
