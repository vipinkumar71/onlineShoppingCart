package onlineShoppingCart.shoppingCart.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class AuthRequest {
    @NotBlank(message = "Email can't be blank or null")
    private String email;

    @NotBlank(message = "password can't be blank or null")
    private String password;
}

