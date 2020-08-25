package psychotest.repository;

import psychotest.entity.UserEntity;

import java.util.List;

public interface UsersDAO {
    List<UserEntity> getAllUsers();
}
