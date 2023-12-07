package onlineShoppingCart.shoppingCart.dto;


import lombok.Getter;

@Getter
public class CartItemRespDTO {
    private Integer quantity;
    private Double totalPrice;
    private Long cartId;
    private Long productId;
    private String productName;

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public CartItemRespDTO() {

    }

    public CartItemRespDTO(Integer quantity, Double totalPrice) {
        super();
        this.quantity = quantity;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public CartItemRespDTO(Integer quantity, Double totalPrice, Long cartId, Long productId, String productName) {
        super();
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.cartId = cartId;
        this.productId = productId;
        this.productName = productName;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "CartItemRespDTO [quantity=" + quantity + ", totalPrice=" + totalPrice + "]";
    }

}
