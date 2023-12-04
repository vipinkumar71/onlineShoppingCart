package onlineShoppingCart.shoppingCart.services;

import onlineShoppingCart.shoppingCart.dto.CartItemDTO;
import onlineShoppingCart.shoppingCart.entities.CartItems;
import onlineShoppingCart.shoppingCart.entities.Carts;
import onlineShoppingCart.shoppingCart.entities.Products;

import java.util.Set;

public interface CartItemsService {

    CartItems addToCart(CartItemDTO cartItem);

    Set<CartItems> getCartItems(Long CartId);

    void DeleteCartItemsFromCart(Carts cartId);

    void deleteByProductId(Products product);

    void deleteItem(Long cartItemId);

    void updateItem(Long cartItemId);
}
