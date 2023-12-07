package onlineShoppingCart.shoppingCart.dto;

import lombok.Getter;

@Getter
public class CartItemDTO {
    private Integer quantity;
    private Long userId;
    private Long productId;

    public CartItemDTO(Integer quantity, Long userId, Long productId) {
        super();
        this.quantity = quantity;
        this.userId = userId;
        this.productId = productId;
    }

    public CartItemDTO() {
        super();
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
