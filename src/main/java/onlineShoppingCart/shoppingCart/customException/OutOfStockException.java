package onlineShoppingCart.shoppingCart.customException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("serial")
@Component
public class OutOfStockException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private String elementName;
    private String errorCode;
    private String errorMessage;
}
