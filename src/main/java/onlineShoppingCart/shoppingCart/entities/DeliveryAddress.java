package onlineShoppingCart.shoppingCart.entities;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Valid
@Entity
@Table(name="delivery_address")
public class DeliveryAddress extends BaseEntity{
    @Column(length = 50,nullable = false)
    @NotBlank(message = "Address cannot be Blank")
    private String adressLine1;

    @Column(length = 50,nullable = false)
    private String adressLine2;

    @Column(length = 50,nullable = false)
    @NotBlank(message = "City cannot be Blank")
    private String city;

    @Column(length = 50,nullable = false)
    @NotBlank(message = "State cannot be Blank")
    private String state;

    @Column(length = 50,nullable = false)
    @Size(min=6,max=8)
    private String zipCode;

    public DeliveryAddress() {
        super();
    }

    public DeliveryAddress(String adressLine1, String adressLine2, String city, String state, String zipCode) {
        super();
        this.adressLine1 = adressLine1;
        this.adressLine2 = adressLine2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public void setAdressLine1(String adressLine1) {
        this.adressLine1 = adressLine1;
    }

    public void setAdressLine2(String adressLine2) {
        this.adressLine2 = adressLine2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
