package psychotest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import psychotest.entity.RegistrationEntity;
import psychotest.entity.UserEntity;
import psychotest.repository.reg_login.RegistrationRepository;

import java.util.List;

@Service
public class RegistrationServiceImpl implements UserDetailsService, RegistrationService {

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    private final RegistrationRepository registrationRepository;

    @Autowired
    public RegistrationServiceImpl(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Override
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

    @Override
    public boolean saveAll(UserEntity userEntity) {
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        registrationRepository.saveAll(userEntity);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}