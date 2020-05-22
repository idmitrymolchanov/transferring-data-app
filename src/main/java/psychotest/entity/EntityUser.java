package psychotest.entity;

import lombok.*;

@Data
@Builder(toBuilder = true)
public class EntityUser {
    private Long id;
    private String username;
    private String password;
    private String passwordCONFIRM;
}
