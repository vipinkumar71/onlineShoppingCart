package onlineShoppingCart.shoppingCart.services;


import onlineShoppingCart.shoppingCart.dto.CategoryDTO;
import onlineShoppingCart.shoppingCart.entities.Categories;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Categories saveCategory(CategoryDTO category);

    Optional<Categories> getCategory(Long CatId);

    List<Categories> getAllCategories();

    void deleteCategory(Long categoryId);
}
