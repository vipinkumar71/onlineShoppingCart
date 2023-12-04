package onlineShoppingCart.shoppingCart.dto;

import lombok.*;
import onlineShoppingCart.shoppingCart.entities.Role;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthResp {
    private Long id;

    private String email;

    private String firstName;
    private String lastName;

    private Role role;

    private String token;
}
