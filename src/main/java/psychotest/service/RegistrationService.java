package psychotest.service;

import psychotest.entity.RegistrationEntity;
import psychotest.entity.UserEntity;

import java.util.List;

public interface RegistrationService {
    boolean saveUser(List<RegistrationEntity> user);
    boolean saveAll(UserEntity userEntity);
}
