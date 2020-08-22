package psychotest.service;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import psychotest.entity.UserEntity;
import psychotest.repository.reg_login.RoleDaoImpl;
import psychotest.repository.reg_login.UserDaoImpl;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDaoImpl appUserDaoImpl;
    private final RoleDaoImpl appRoleDaoImpl;

    @Autowired
    public UserDetailsServiceImpl(UserDaoImpl appUserDaoImpl, RoleDaoImpl appRoleDaoImpl) {
        this.appUserDaoImpl = appUserDaoImpl;
        this.appRoleDaoImpl = appRoleDaoImpl;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity appUser = this.appUserDaoImpl.findUserAccount(username);

        if (appUser == null) {
            log.error("User not found! " + username);
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }

        log.info("Found User: " + appUser);

        // [ROLE_USER, ROLE_ADMIN,..]
        List<String> roleNames = this.appRoleDaoImpl.getRoleNames(appUser.getId());

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }
        UserDetails userDetails = (UserDetails) new User(appUser.getUsername(), appUser.getPassword(), grantList);

        return userDetails;
    }

}