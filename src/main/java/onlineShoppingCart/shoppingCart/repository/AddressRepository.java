package onlineShoppingCart.shoppingCart.repository;
import onlineShoppingCart.shoppingCart.entities.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<DeliveryAddress, Long> {

}
