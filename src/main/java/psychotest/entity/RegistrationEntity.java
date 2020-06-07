package psychotest.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegistrationEntity {
    private Long id;
    private String username;
    private String password;
    private String passwordCONFIRM;
}
