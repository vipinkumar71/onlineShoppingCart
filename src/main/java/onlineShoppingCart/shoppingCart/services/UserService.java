package onlineShoppingCart.shoppingCart.services;

import onlineShoppingCart.shoppingCart.dto.UsersDTO;
import onlineShoppingCart.shoppingCart.dto.UsersRespDTO;
import onlineShoppingCart.shoppingCart.entities.Users;

import javax.validation.Valid;
import java.util.List;

public interface UserService {
    UsersRespDTO addUserDetails(UsersDTO user);

    String deleteUserDetails(Long userId);

    Users updateUserDetails(@Valid UsersDTO user);

    List<Users> getAllUsers();


}

