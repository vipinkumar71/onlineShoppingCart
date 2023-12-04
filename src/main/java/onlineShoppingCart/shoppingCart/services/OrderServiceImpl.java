package onlineShoppingCart.shoppingCart.services;

import onlineShoppingCart.shoppingCart.customException.ElementNotFountException;
import onlineShoppingCart.shoppingCart.customException.OutOfStockException;
import onlineShoppingCart.shoppingCart.dto.DeliveryAddressDTO;
import onlineShoppingCart.shoppingCart.dto.OrdersRespDTO;
import onlineShoppingCart.shoppingCart.entities.*;
import onlineShoppingCart.shoppingCart.repository.AddressRepository;
import onlineShoppingCart.shoppingCart.repository.OrderDetailsRepository;
import onlineShoppingCart.shoppingCart.repository.OrderRepository;
import onlineShoppingCart.shoppingCart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private OrderDetailsRepository detailsRepository;

    @Autowired
    private CartService cartService;

    @Override
    public OrdersRespDTO placeOrder(Long userId, DeliveryAddressDTO address) {
        DeliveryAddress trueAddress=new DeliveryAddress(address.getAddressLine1(),address.getAddressLine2(),address.getCity(),address.getState(),address.getZipCode());
        DeliveryAddress addedAddress=addressRepository.save(trueAddress);
        Users user=userRepository.findById(userId).orElseThrow(()->new ElementNotFountException("User", "404", "Not Found1231321231231"));
        Orders order=new Orders( LocalDate.now(),LocalDate.of(2024, 12, 12), Status.PLACED, 0, 40 , user, addedAddress);
        Orders newOrder=orderRepository.save(order);
        Carts cart=user.getCart();
        Set<CartItems> cartItems=user.getCart().getCartItems();
        cartItems.forEach(x->{
            OrderDetails oDetails = new OrderDetails(x.getQuantity() , x.getTotalPrice(),newOrder,x.getProductId());
            Products product=x.getProductId();
            if(product.getStock()==0 || x.getQuantity()>product.getStock()) {
                throw new OutOfStockException("Product "+product.getName(), "403", "Out Of Stock");
            }
            product.setStock(product.getStock()-x.getQuantity());//Product Stock is Updated (Decreased)
            newOrder.setOrderDetails(detailsRepository.save(oDetails));

        });
        newOrder.setTotalPrice(cart.getTotalPrice()+newOrder.getShippingPrice());
        newOrder.setStatus(Status.PLACED);
        cartService.emptyTheCart(user.getId());
        return  new OrdersRespDTO(newOrder.getOrderDate(), newOrder.getDeliveryDate(), newOrder.getStatus(), newOrder.getTotalPrice(), newOrder.getShippingPrice(), user.getId(), addedAddress.getId());
    }

    @Override
    public List<Orders> getAllOrders() {

        List<Orders> list=orderRepository.findAll();
        list.forEach(x->{
            x.getOrderDetails().forEach(y->{y.getQuantity();});
            x.getUserOrdered().getCart().getCartItems().forEach(z->z.getQuantity());
            x.getAddress().getAdressLine1();
        });
        return list;
    }

    @Override
    public List<Orders> getMyOrders(Long Id) {
        Optional<Users> user=userRepository.findById(Id);

        List<Orders> list=orderRepository.findByUserOrdered(user.get());
        list.forEach(x->{
            x.getOrderDetails().forEach(y->{y.getQuantity();});
            x.getUserOrdered().getCart().getCartItems().forEach(z->z.getQuantity());
            x.getAddress().getAdressLine1();
        });

        return list;
    }



    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public void deleteOrders(Long userId) {
        Users user=userRepository.findById(userId).orElseThrow(()->new ElementNotFountException("User", "404", "Not Found"));
        orderRepository.deleteByUserOrdered(user);

    }

    @Override
    public void cancelOrder(Long orderId) {
        Orders order=orderRepository.findById(orderId).orElseThrow(()->new ElementNotFountException("Order", "404", "Not Found"));
        if(order.getStatus()!=Status.DELIVERED || order.getStatus()!=Status.CANCELED) {
            order.getOrderDetails().forEach(x->{
                x.getProductId().setStock(x.getProductId().getStock()+x.getQuantity());//Product Stock is Updated (Increased)
            });
            order.setStatus(Status.CANCELED);
        }
    }

    @Override
    public void updateOrderStatus(Long orderId,String status,LocalDate deliveryDate) {
        Orders order=orderRepository.findById(orderId).orElseThrow(()->new ElementNotFountException("Order", "404", "Not Found"));
        order.setStatus(Status.valueOf(status));
        order.setDeliveryDate(deliveryDate);
    }
}
