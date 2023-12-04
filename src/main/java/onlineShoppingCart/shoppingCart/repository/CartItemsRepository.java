package onlineShoppingCart.shoppingCart.repository;

import onlineShoppingCart.shoppingCart.entities.CartItems;
import onlineShoppingCart.shoppingCart.entities.Carts;
import onlineShoppingCart.shoppingCart.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CartItemsRepository extends JpaRepository<CartItems,Long> {

        Long deleteByCartId(Carts cartId);

        Long deleteByProductId(Products product);

        List<CartItems> findByProductId(Products product);

        CartItems findByProductIdAndCartId(Products product, Carts cart);
}
