package psychotest.repository.reg_login;

import java.util.List;

public interface RoleDao {
    public List<String> getRoleNames(Long userId);

}
