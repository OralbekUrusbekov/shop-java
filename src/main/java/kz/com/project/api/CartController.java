package kz.com.project.api;

import kz.com.project.Dto.CartDTO;
import kz.com.project.Dto.OrderDTO;
import kz.com.project.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/cart")
@PreAuthorize("hasAuthority('USER')")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    /**
     * Adds a cat (product) to the authenticated user's cart.
     * Uses Principal to identify the current user.
     */
    @PostMapping("/add/{catId}")
    public ResponseEntity<?> add(@PathVariable Long catId, Principal principal) {
        cartService.addToCart(principal.getName(), catId);
        return ResponseEntity.ok("Added");
    }

    /**
     * Returns the current authenticated user's cart.
     * Includes cart items and total price.
     */
    @GetMapping
    public ResponseEntity<CartDTO> cart(Principal principal) {
        return ResponseEntity.ok(cartService.getCart(principal.getName()));
    }

    /**
     * Creates an order from the user's cart.
     * Clears the cart after successful checkout.
     */
    @PostMapping("/checkout")
    public ResponseEntity<OrderDTO> checkout(Principal principal) {
        return ResponseEntity.ok(cartService.checkout(principal.getName()));
    }
}



