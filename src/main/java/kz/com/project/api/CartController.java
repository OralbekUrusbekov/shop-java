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

    @PostMapping("/add/{catId}")
    public ResponseEntity<?> add(@PathVariable Long catId, Principal principal) {
        cartService.addToCart(principal.getName(), catId);
        return ResponseEntity.ok("Added");
    }

    @GetMapping
    public ResponseEntity<CartDTO> cart(Principal principal) {
        return ResponseEntity.ok(cartService.getCart(principal.getName()));
    }

    @PostMapping("/checkout")
    public ResponseEntity<OrderDTO> checkout(Principal principal) {
        return ResponseEntity.ok(cartService.checkout(principal.getName()));
    }
}


