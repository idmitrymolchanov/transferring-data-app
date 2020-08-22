package psychotest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psychotest.entity.TableNameEntity;
import psychotest.entity.UserEntity;
import psychotest.repository.UserListRepository;

import java.util.List;

@Service
public class UserListService {
    private final UserListRepository repository;

    @Autowired
    public UserListService(UserListRepository repository) {
        this.repository = repository;
    }

    public List<UserEntity> getAllUsers() {
        return repository.getAllUsers();
    }
}
