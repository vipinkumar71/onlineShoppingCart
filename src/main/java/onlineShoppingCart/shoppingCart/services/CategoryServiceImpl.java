package onlineShoppingCart.shoppingCart.services;

import onlineShoppingCart.shoppingCart.dto.CategoryDTO;
import onlineShoppingCart.shoppingCart.entities.Categories;
import onlineShoppingCart.shoppingCart.repository.CategoryRepository;
import onlineShoppingCart.shoppingCart.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Categories saveCategory(CategoryDTO Category) {

        Categories cat = new Categories();
        BeanUtils.copyProperties(Category, cat);
        return categoryRepository.save(cat);
    }

    @Override
    public Optional<Categories> getCategory(Long CatId) {
        return categoryRepository.findById(CatId);
    }

    @Override
    public List<Categories> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
