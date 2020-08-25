package psychotest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psychotest.entity.UserEntity;
import psychotest.repository.UsersDAO;

import java.util.List;

@Service
public class UserListServiceImpl implements UserListService{
    private final UsersDAO repository;

    @Autowired
    public UserListServiceImpl(UsersDAO repository) {
        this.repository = repository;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return repository.getAllUsers();
    }
}
