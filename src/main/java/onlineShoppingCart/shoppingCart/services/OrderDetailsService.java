package onlineShoppingCart.shoppingCart.services;

import onlineShoppingCart.shoppingCart.entities.OrderDetails;
import org.springframework.stereotype.Service;

public interface OrderDetailsService {

    OrderDetails saveOrderDetails(OrderDetails orderDetails);
}

