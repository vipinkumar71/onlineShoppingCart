package onlineShoppingCart.shoppingCart.repository;


import onlineShoppingCart.shoppingCart.entities.OrderDetails;
import onlineShoppingCart.shoppingCart.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository  extends JpaRepository<OrderDetails, Long> {
    void deleteByOrderId(Orders Id);
}
