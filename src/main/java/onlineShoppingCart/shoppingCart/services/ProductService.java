package onlineShoppingCart.shoppingCart.services;

import onlineShoppingCart.shoppingCart.dto.ProductUpdateDTO;
import onlineShoppingCart.shoppingCart.dto.ProductsDTO;
import onlineShoppingCart.shoppingCart.dto.ProductsRespDTO;
import onlineShoppingCart.shoppingCart.entities.Products;
import onlineShoppingCart.shoppingCart.entities.projections.ProductsProjection;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {

    ProductsRespDTO saveProduct(ProductsDTO product);

    List<ProductsProjection> getProductsByCategory(Long categoryId);

    void deleteProduct(Long productId);

    List<ProductsProjection> findAllProducts();

    void updateProduct(ProductUpdateDTO product, Long productId);


    Products getProductById(Long productId);




}