package psychotest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import psychotest.entity.RegistrationEntity;
import psychotest.entity.UserEntity;
import psychotest.repository.RoleRepo;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    private final RoleRepo roleRepo;

    @Autowired
    public UserService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    public boolean saveUser(List<RegistrationEntity> user) {
        System.out.println("++++++++++++++++++++++++++++++ " + user.get(0).getId());
       // EntityUser userFromDB = roleRepo.findByIdUser(user.get(0).getId()).get(0);

     //   if (userFromDB != null) {
     //       return false;
     //   }

        user.get(0).setPassword(bCryptPasswordEncoder.encode(user.get(0).getPassword()));
   //     roleRepo.saveAllUser(user);
        return true;
    }

    public boolean saveAll(UserEntity userEntity) {
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        roleRepo.saveAll(userEntity);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}