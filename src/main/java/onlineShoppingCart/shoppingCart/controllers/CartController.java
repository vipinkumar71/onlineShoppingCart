package onlineShoppingCart.shoppingCart.controllers;

import onlineShoppingCart.shoppingCart.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> emptyTheCart(@PathVariable Long userId) {
        cartService.emptyTheCart(userId);

        return new ResponseEntity<String>("Cart is Empty", HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getMyCart(@PathVariable Long userId) {
        return new ResponseEntity<>(cartService.getMyCart(userId), HttpStatus.CREATED);
    }

}
