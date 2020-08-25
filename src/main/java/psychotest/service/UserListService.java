package psychotest.service;

import psychotest.entity.UserEntity;

import java.util.List;

public interface UserListService {
    List<UserEntity> getAllUsers();
}
