package onlineShoppingCart.shoppingCart.repository;

import onlineShoppingCart.shoppingCart.entities.Categories;
import onlineShoppingCart.shoppingCart.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {

    public List<Products> findByCategoryId(Categories categoryId);

    Long deleteByCategoryId(Categories categoryId);
}
