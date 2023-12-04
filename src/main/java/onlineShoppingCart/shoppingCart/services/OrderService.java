package onlineShoppingCart.shoppingCart.services;

import onlineShoppingCart.shoppingCart.dto.DeliveryAddressDTO;
import onlineShoppingCart.shoppingCart.dto.OrdersRespDTO;
import onlineShoppingCart.shoppingCart.entities.Orders;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
public interface OrderService {

    OrdersRespDTO placeOrder(Long userId, DeliveryAddressDTO address);

    List<Orders> getAllOrders();

    List<Orders> getMyOrders(Long Id);

    void deleteOrder(Long orderId);

    void deleteOrders(Long userId);

    void cancelOrder(Long orderId);

    void updateOrderStatus(Long orderId, String status, LocalDate deliveryDate);

}
