package onlineShoppingCart.shoppingCart.services;

import onlineShoppingCart.shoppingCart.entities.OrderDetails;
import onlineShoppingCart.shoppingCart.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderDetailsServiceImpl implements OrderDetailsService{
    @Autowired
    private OrderDetailsRepository detailsRepository;

    @Override
    public OrderDetails saveOrderDetails(OrderDetails orderDetails) {
        return detailsRepository.save(orderDetails);
    }
}
