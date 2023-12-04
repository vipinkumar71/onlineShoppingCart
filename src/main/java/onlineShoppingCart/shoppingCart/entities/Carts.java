package onlineShoppingCart.shoppingCart.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Valid
@Entity
@Table(name = "carts")
public class Carts  extends BaseEntity{
    @Column(name="created")
    private LocalDate created;

    @Column(name="updated")
    private LocalDate updated;

    @Column(name="totalItems")
    @PositiveOrZero(message = "Can't be Negative")
    private int totalItems;

    @Column(name="totalPrice")
    @PositiveOrZero(message = "Can't be Negative")
    private double totalPrice;

    @ElementCollection
    @OneToMany(mappedBy ="cartId",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<CartItems> cartItems=new HashSet<>();

    @OneToOne
    @JoinColumn(name="userId")
    @JsonIgnore
    private Users user;

    public Carts() {
        super();
    }

    public Carts(LocalDate created, LocalDate updated, int totalItems, double totalPrice, Set<CartItems> cart_items,
                 Users user) {
        super();
        this.created = created;
        this.updated = updated;
        this.totalItems = totalItems;
        this.totalPrice = totalPrice;
        this.cartItems = cart_items;
        this.user = user;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public void setUpdated(LocalDate updated) {
        this.updated = updated;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public void setCartItems(CartItems cartItem) {
        cartItems.add(cartItem);
    }

    public void emptyCartItems() {
        cartItems.clear();
    }

}
