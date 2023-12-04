package onlineShoppingCart.shoppingCart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import onlineShoppingCart.shoppingCart.entities.Role;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersRespDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private String mobileNumber;
}
