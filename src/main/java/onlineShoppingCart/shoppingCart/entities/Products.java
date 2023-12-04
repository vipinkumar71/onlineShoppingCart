package onlineShoppingCart.shoppingCart.entities;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Getter
@Valid
@Entity
@Table(name = "products")
public class Products extends BaseEntity{
    @Column(name = "name", length = 100, nullable = false)
    @NotBlank(message = "Name Should Not Be Empty")
    private String name;

    @Column(nullable = false)
    @PositiveOrZero(message = "Price should be Non-Negative")
    private Double price;

    private String description;

    @PositiveOrZero(message = "stock should be Non-Negative")
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories categoryId;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "exp_date")
    @Future(message = "Date must be in Future")
    private LocalDate expDate;

    public Products() {
        super();
        System.out.println("in ctor " + getClass().getName());
    }

    public Products(String name, Double price, String description, Integer stock, Categories categoryId,
                    LocalDate expDate) {
        super();
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
        this.categoryId = categoryId;
        this.expDate = expDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategoryId(Categories categoryId) {
        this.categoryId = categoryId;
    }

    public void setExpDate(LocalDate expDate) {
        this.expDate = expDate;
    }
}
