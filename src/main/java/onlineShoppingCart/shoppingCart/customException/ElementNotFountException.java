package onlineShoppingCart.shoppingCart.customException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("serial")
public class ElementNotFountException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String elementName;
    private String errorCode;
    private String errorMessage;
}
