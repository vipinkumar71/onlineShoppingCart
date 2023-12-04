package onlineShoppingCart.shoppingCart.services;

import onlineShoppingCart.shoppingCart.customException.ElementNotFountException;
import onlineShoppingCart.shoppingCart.dto.ProductUpdateDTO;
import onlineShoppingCart.shoppingCart.dto.ProductsDTO;
import onlineShoppingCart.shoppingCart.dto.ProductsRespDTO;
import onlineShoppingCart.shoppingCart.entities.Categories;
import onlineShoppingCart.shoppingCart.entities.Products;
import onlineShoppingCart.shoppingCart.entities.projections.ProductsProjection;
import onlineShoppingCart.shoppingCart.repository.CategoryRepository;
import onlineShoppingCart.shoppingCart.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
        @Autowired
        private ProductRepository productRepository;

        @Autowired
        private CategoryService categoryService;

        @Autowired
        private CategoryRepository categoryRepository;

        @Autowired
        private CartItemsService cartItemsService;

        @Autowired
        private ModelMapper mapper;

        @Override
        public ProductsRespDTO saveProduct(ProductsDTO product) {
            Long CatId = product.getCategoryId();
            Optional<Categories> cat = categoryRepository.findById(product.getCategoryId());

            Categories result = cat.orElseThrow(() -> new ElementNotFountException("Category", "404", "Not Found"));

            Products newProduct = new Products(product.getName(), product.getPrice(), product.getDescription(),
                    product.getStock(), result, product.getExpDate());
            Products prod = productRepository.save(newProduct);
            return mapper.map(prod, ProductsRespDTO.class);
        }

        @Override
        public List<ProductsProjection> getProductsByCategory(Long categoryId) {
            Optional<Categories> cat = categoryRepository.findById(categoryId);
            Categories result = cat.orElseThrow(() -> new ElementNotFountException("Category", "404", "Not Found"));
            Set<Products> tempList = result.getProductList();
            List<ProductsProjection> productList = new ArrayList<ProductsProjection>();
            tempList.forEach(
                    x -> productList.add(new ProductsProjection(x.getId(), x.getName(), x.getPrice(), x.getDescription(),
                            x.getStock(), x.getCategoryId().getCategoryName(), x.getImagePath(), x.getExpDate())));
            return productList;
        }

        @Override // Deleting Product from Product Table and from Cart-Item table Only
        public void deleteProduct(Long productId) {
            Products product = productRepository.findById(productId)
                    .orElseThrow(() -> new ElementNotFountException("Product", "404", "Not Found"));
            cartItemsService.deleteByProductId(product);
            productRepository.deleteById(productId);

        }

        @Override
        public List<ProductsProjection> findAllProducts() {
            List<Products> tempList = productRepository.findAll();
            if (tempList.size() == 0) {
                throw new ElementNotFountException("Products", "404", "List is Empty");

            }
            List<ProductsProjection> list = new ArrayList<ProductsProjection>();
            tempList.forEach(x -> {
                list.add(new ProductsProjection(x.getId(), x.getName(), x.getPrice(), x.getDescription(), x.getStock(),
                        x.getCategoryId().getCategoryName(), x.getImagePath(), x.getExpDate()));
            });
            return list;
        }

        @Override
        public void updateProduct(ProductUpdateDTO product, Long productId) {
            Products retProduct = productRepository.findById(productId)
                    .orElseThrow(() -> new ElementNotFountException("Product", "404", "Not Found"));
            retProduct.setName(product.getName());
            retProduct.setDescription(product.getDescription());
            retProduct.setStock(product.getStock());
            retProduct.setPrice(product.getPrice());
            retProduct.setExpDate(product.getExpDate());
            System.out.println("In Update #");
        }

        @Override
        public Products getProductById(Long productId) {
            Products retProduct = productRepository.findById(productId)
                    .orElseThrow(() -> new ElementNotFountException("Product", "404", "Not Found"));
            return retProduct;
        }
}
