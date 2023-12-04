package onlineShoppingCart.shoppingCart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import onlineShoppingCart.shoppingCart.entities.Status;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdersRespDTO {
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private Status status;
    private double totalPrice;
    private double shippingPrice;
    private Long userOrdered;
    private Long address;
}
