package onlineShoppingCart.shoppingCart.repository;

import onlineShoppingCart.shoppingCart.entities.Orders;
import onlineShoppingCart.shoppingCart.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByUserOrdered(Users referenceById);

    String deleteByUserOrdered(Users user);

}
