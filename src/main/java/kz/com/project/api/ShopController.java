package kz.com.project.api;

import kz.com.project.Dto.CatDTO;
import kz.com.project.Dto.OrderDTO;
import kz.com.project.service.CatService;
import kz.com.project.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/shop")
@PreAuthorize("hasAuthority('USER')")
public class ShopController {

    private final CatService catService;
    private final OrderService orderService;

    public ShopController(CatService catService, OrderService orderService) {
        this.catService = catService;
        this.orderService = orderService;
    }

    @GetMapping("/cats")
    public ResponseEntity<List<CatDTO>> getCats() {
        return ResponseEntity.ok(catService.getAllCats());
    }

    @PostMapping("/buy/{catId}")
    public ResponseEntity<OrderDTO> buyCat(@PathVariable Long catId, Principal principal) {
        // principal.getName() → email
        return ResponseEntity.ok(orderService.createOrder(
                // пайдаланушыны табу
                null, // Мұнда сіз UserRepository арқылы userId табасыз
                catId
        ));
    }
}

