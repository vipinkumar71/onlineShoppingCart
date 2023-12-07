package onlineShoppingCart.shoppingCart.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Valid
@Entity
@Table(name = "users")
public class Users extends BaseEntity{
    @Column(name="first_name" ,length = 25)
    @NotBlank(message = "Fist Name should Not be Blank")
    private String firstName;

    @Column(name="last_name" ,length = 25)
    @NotBlank(message = "Last Name should Not be Blank")
    private String lastName;

    @Column(name="email" ,length = 50,unique = true,nullable = false)
    @Email(message = "Email should be Proper")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name="password" ,length = 300, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name="role" ,length = 15)
    private Role role;

    @Column(length=10,nullable = false)
    @Pattern(regexp="(^[0-9]{10}$)",message = "Invalid Mobile Number !")
    private String mobileNumber;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private Carts cart;

    public Users() {
        super();
    }


    public Users(String firstName, String lastName, String email, String password, Role role, String mobileNumber) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.mobileNumber = mobileNumber;
    }

    public Users(String firstName, String lastName, String email) {
        super();
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public void setRole(Role role) {
        this.role = role;
    }


    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }


    public void setCart(Carts cart) {
        this.cart = cart;
    }


    @Override
    public String toString() {
        return "Users [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password="
                + password + ", role=" + role + ", mobileNumber=" + mobileNumber + ", getId()=" + getId() + "]";
    }
}
