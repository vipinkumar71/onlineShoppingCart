package onlineShoppingCart.shoppingCart.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Getter
@Valid
@Entity
@Table(name="categories")
public class Categories extends BaseEntity{
    @Column(length = 50,nullable = false)
    @NotBlank(message = "categoryName Cannot be blank")
    private String categoryName;

    @Column(length = 1000,nullable = false)
    private String description;

    @Column(length = 1000,nullable = false)
    @OneToMany(mappedBy = "categoryId", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Products> productList = new HashSet<Products>();



    public Categories() {
        super();
    }


    public Categories(String categoryName, String description) {
        super();
        this.categoryName = categoryName;
        this.description = description;
    }
    @Override
    public String toString() {
        return "Categories [category_Name=" + categoryName + ", description=" + description + ", getId()=" + getId()+ "]";
    }
}
