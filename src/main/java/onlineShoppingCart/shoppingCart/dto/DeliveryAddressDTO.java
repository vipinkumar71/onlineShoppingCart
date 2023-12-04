package onlineShoppingCart.shoppingCart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAddressDTO {
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zipCode;
}

