package onlineShoppingCart.shoppingCart.services;

import onlineShoppingCart.shoppingCart.dto.CartItemRespDTO;
import onlineShoppingCart.shoppingCart.entities.Carts;
import onlineShoppingCart.shoppingCart.entities.Users;

import java.util.List;

public interface CartService {

    Carts addCart(Users user);

    void emptyTheCart(Long cartId);

    Carts getMyCart(Long cartId);

    List<CartItemRespDTO> getMyCartItems(Long cartId);

}

