package onlineShoppingCart.shoppingCart.entities;

import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
@Getter
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    public BaseEntity() {
        super();
    }

    public BaseEntity(Long id) {
        super();
        this.id = id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}

