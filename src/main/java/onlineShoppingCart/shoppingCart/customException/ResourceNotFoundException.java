package onlineShoppingCart.shoppingCart.customException;
@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
