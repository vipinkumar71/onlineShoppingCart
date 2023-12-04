package onlineShoppingCart.shoppingCart.repository;

import onlineShoppingCart.shoppingCart.entities.Carts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Carts, Long> {
}
