package onlineShoppingCart.shoppingCart.repository;


import onlineShoppingCart.shoppingCart.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long> {
}
